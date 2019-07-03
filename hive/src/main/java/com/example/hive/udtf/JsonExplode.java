package com.example.hive.udtf;


import com.example.hive.common.JsonHelper;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.Map;

public class JsonExplode extends GenericUDTF {

    @Override
    public void close() throws HiveException {
        // TODO Auto-generated method stub

    }

    @Override
    public void process(Object[] arg0) throws HiveException {

        String value = arg0[0].toString();
        String indexStr = arg0[1].toString();

        Map<String, Object> map = JsonHelper.json2Object(value, Map.class);


        if (map == null) {
            return;
        }
        String[] indexs = new String[0];
        if (!StringUtils.isEmpty(indexStr)) {
            indexs = indexStr.split("\\.");
        }
        Map<String, Object> tmp = map;
        for (String index : indexs) {
            if (tmp == null) {
                break;
            } else {
                Object o = tmp.get(index);
                if (o instanceof Map) {
                    tmp = (Map<String, Object>) o;
                } else {
                    tmp = null;
                }
            }
        }
        if (tmp == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : tmp.entrySet()) {
            String[] result = new String[2];
            result[0] = entry.getKey();
            result[1] = JsonHelper.object2String(entry.getValue());
            forward(result);
//            System.out.println(StringUtils.join(result ,","));
        }


    }

    @Override
    public StructObjectInspector initialize(ObjectInspector[] args)
            throws UDFArgumentException {
        if (args.length != 2) {
            throw new UDFArgumentLengthException(
                    "Json Explode takes only two argument");
        }
        if (args[0].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentException(
                    "Json Explode First takes string as a parameter");
        }
        if (args[1].getCategory() != ObjectInspector.Category.PRIMITIVE) {
            throw new UDFArgumentException(
                    "Json Explode Second takes string as a parameter");
        }

        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldNames.add("key");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldNames.add("value");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(
                fieldNames, fieldOIs);
    }

    public static void main(String[] args) throws HiveException {

        String json = "{\"bm_13\":\"2\",\"djkd_01\":\"1\",\"kj_01\":\"1\",\"hf_64\":\"1\",\"sd_17\":\"1\",\"yhd_22\":\"1\",\"yddl_07\":\"1\",\"ym_17\":\"3\",\"xwl_08\":\"1\",\"sd_20\":\"1\"}";
        JsonExplode jsonExplode = new JsonExplode();

        Object[] arg0 = new Object[]{json,""};

        jsonExplode.process(arg0);


    }


}