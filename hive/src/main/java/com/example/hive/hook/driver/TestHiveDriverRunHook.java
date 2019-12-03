package com.example.hive.hook.driver;

import org.apache.hadoop.hive.ql.HiveDriverRunHook;
import org.apache.hadoop.hive.ql.HiveDriverRunHookContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHiveDriverRunHook implements HiveDriverRunHook {

    private static final Logger log = LoggerFactory.getLogger(TestHiveDriverRunHook.class);


    @Override
    public void preDriverRun(HiveDriverRunHookContext hookContext) throws Exception {
        log.info("pre diver run sql : {}", hookContext.getCommand());

    }

    @Override
    public void postDriverRun(HiveDriverRunHookContext hookContext) throws Exception {

        log.info("post diver run sql : {}", hookContext.getCommand());

    }
}
