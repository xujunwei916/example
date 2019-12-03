package com.example.hive.hook;

import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.hooks.ReadEntity;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.HiveOperation;
import org.apache.hadoop.security.UserGroupInformation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a pre execute hook that updates the access
 * times for all the inputs.
 * @author wusj
 */
public class UpdateInputAccessTimeHook {
    private static final String HOOK_ENABLE_PROP_NAME = "hive.exec.pre.hooks.UpdateInputAccessTimeHook.enable";
    private static final String HOOK_ENABLE_ENV_NAME = "HIVE_EXEC_PRE_HOOKS_UPDATEINPUTACCESSTIMEHOOK_ENABLE";
    private static final boolean HOOK_ENABLE_DEFAULT_VALUE = true;
    private static final String LAST_READ_TIME = "lastReadTime";
    private static final String LAST_LOCAL_READ_TIME = "lastLocalReadTime";
    private static final String LAST_READ_USER = "lastReadUser";
    private static final List<String> INCLUDE_COMMAND_TYPE;
    static {
        INCLUDE_COMMAND_TYPE = new ArrayList<>();
        INCLUDE_COMMAND_TYPE.add(HiveOperation.QUERY.getOperationName());
        INCLUDE_COMMAND_TYPE.add(HiveOperation.CREATETABLE_AS_SELECT.getOperationName());
    }
    public static String getLocalTime(long currentTime){
        return new Timestamp(currentTime).toLocalDateTime().toString().replace("T"," ");
    }
    public static class PreExec implements ExecuteWithHookContext {
        private Hive db;
        private void updateTable(Table table,UserGroupInformation ugi){
            try {
                long currentTime = System.currentTimeMillis();
                String currentTimeString = String.valueOf(currentTime);
                Table t = db.getTable(table.getDbName(),table.getTableName());

                t.setProperty(LAST_LOCAL_READ_TIME,getLocalTime(currentTime));
                t.setProperty(LAST_READ_TIME,currentTimeString);
                t.setProperty(LAST_READ_USER,ugi.getUserName());
                db.alterTable(t.getDbName() + "." + t.getTableName(), t);
            } catch (InvalidOperationException | HiveException e) {
                e.printStackTrace();
            }
        }
        /**
         * @param hookContext The hook context passed to each hooks.
         * @throws Exception
         */
        @Override
        public void run(HookContext hookContext) throws Exception {
            String enableProperty = null;
            try{
                enableProperty = System.getProperty(HOOK_ENABLE_ENV_NAME);
                if(enableProperty == null){
                    enableProperty = System.getenv(HOOK_ENABLE_ENV_NAME);
                }
            }catch (Exception ignore){ }

            if(enableProperty!=null && !Boolean.parseBoolean(enableProperty)){
                return;
            }else if (hookContext.getConf() != null && !hookContext.getConf().getBoolean(HOOK_ENABLE_PROP_NAME, HOOK_ENABLE_DEFAULT_VALUE)) {
                return;
            } else if (!INCLUDE_COMMAND_TYPE.contains(hookContext.getOperationName())) {
                return;
            }
            if (db == null) {
                try {
                    db = Hive.get(hookContext.getConf());
                } catch (HiveException e) {
                    e.printStackTrace();
                    db = null;
                    return;
                }
            }

            for(ReadEntity re: hookContext.getInputs()) {
                // Set the last query time
                ReadEntity.Type typ = re.getType();
                switch(typ) {
                    // It is possible that read and write entities contain a old version
                    // of the object, before it was modified by StatsTask.
                    // Get the latest versions of the object
                    case TABLE:
                        updateTable(re.getTable(),hookContext.getUgi());
                        break;
                    case PARTITION:
                        updateTable( re.getPartition().getTable(),hookContext.getUgi());
                        break;
                    default:
                        // ignore dummy inputs
                        break;
                }
            }
        }


    }
}
