package com.example.hive.udf;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.ql.exec.MapredContext;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

public class TestUDF extends GenericUDF {

  Object config=null;


  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    return null;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {


    return null;
  }

  @Override
  public String getDisplayString(String[] children) {
    return null;
  }

  @Override
  public void configure(MapredContext context) {
    super.configure(context);
    Configuration conf = context.getJobConf();
    try {
      FileSystem fs= FileSystem.get(conf);
      String path = conf.get("aaaaaaaaaa");

      // 读取 内容
      // config = ...;

    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
