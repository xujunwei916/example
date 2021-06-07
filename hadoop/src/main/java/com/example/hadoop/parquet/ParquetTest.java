package com.example.hadoop.parquet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupFactory;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.schema.ConversionPatterns;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.OriginalType;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.apache.parquet.schema.Type;
import org.apache.parquet.schema.Type.Repetition;
import org.apache.parquet.schema.Types;

public class ParquetTest {

  public static void main(String[] args) throws Exception {

    Path path = new Path("/user/Hypers/test002/test.parquet");
    Configuration configuration = new Configuration();
    GroupWriteSupport writeSupport = new GroupWriteSupport();

    MessageType schema = new MessageType("hive",
        convertField("String", "name", Repetition.OPTIONAL),
        convertField("int", "id", Repetition.OPTIONAL),
        convertField("boolean", "flag", Repetition.OPTIONAL),
        convertComplexField("array<bigint>", "scores", Repetition.OPTIONAL));

    System.out.println(schema);
    System.out.println(convertField("boolean", "flag", Repetition.OPTIONAL).getOriginalType());
//
//    GroupFactory factory = new SimpleGroupFactory(schema);
//
//
//    writeSupport.setSchema(schema, configuration);
////
//    ParquetWriter<Group> writer = new ParquetWriter<Group>(path, configuration, writeSupport);
//
//    for (int i = 0; i < 100; i++) {
//
//
//      Group group = factory.newGroup();
//      group.add("name", "name" + i);
//      group.add("id", i);
//      Group scoreGroup =group.addGroup("scores");
//
//      Map<Integer,Long>  map = new HashMap<>();
//      map.put(1,i*10+1L);
//      map.put(2,i*20+1L);
//      map.put(3,i*30+1L);
//      map.put(4,null);
//      map.put(5,null);
//
//
//
//      for (Map.Entry<Integer,Long> entry: map.entrySet()) {
////        Group mapGroup =scoreGroup.addGroup("array");
////        mapGroup.add("key",entry.getKey());
////        if(entry.getValue()!=null){
//        scoreGroup.append("array",entry.getValue());
////        }
//
//      }
//
//
//      writer.write(group);
//    }
//    writer.close();


  }

  private static Type convertField(String dataType, String name, Repetition repetition) {
    switch (dataType.toLowerCase()) {
      case "boolean":
        return Types.primitive(PrimitiveTypeName.BOOLEAN, repetition).named(name);
      case "byte":
        return Types.primitive(PrimitiveTypeName.INT32, repetition).as(OriginalType.INT_8)
            .named(name);
      case "short":
        return Types.primitive(PrimitiveTypeName.INT32, repetition).as(OriginalType.INT_16)
            .named(name);
      case "int":
        return Types.primitive(PrimitiveTypeName.INT32, repetition).named(name);
      case "bigint":
        return Types.primitive(PrimitiveTypeName.INT64, repetition).named(name);
      case "float":
        return Types.primitive(PrimitiveTypeName.FLOAT, repetition).named(name);
      case "double":
        return Types.primitive(PrimitiveTypeName.DOUBLE, repetition).named(name);
      case "string":
        return Types.primitive(PrimitiveTypeName.BINARY, repetition).as(OriginalType.UTF8)
            .named(name);
      case "date":
        return Types.primitive(PrimitiveTypeName.INT32, repetition).as(OriginalType.DATE)
            .named(name);
      case "binary":
        return Types.primitive(PrimitiveTypeName.BINARY, repetition).named(name);
      default:
        throw new RuntimeException("不是简单类型");
    }
  }


  public static final Pattern MAP_TYPE_REGEX = Pattern.compile("(map)\\<([a-z]+),([a-z]+)\\>");
  public static final Pattern ARRAY_TYPE_REGEX = Pattern.compile("(array)\\<([a-z]+)\\>");

  private static GroupType convertComplexField(String dataType, String name,Repetition repetition) {
    if (dataType.toLowerCase().startsWith("map")) {

      Matcher matcher =  MAP_TYPE_REGEX.matcher(dataType.toLowerCase());
      if(matcher.find()){
        String type1 = matcher.group(2);
        String type2 = matcher.group(3);
        return ConversionPatterns.mapType(
            repetition,
            name,
            convertField(type1,"key",Repetition.REQUIRED),
            convertField(type2,"value",Repetition.OPTIONAL)
        );
      }else{
        throw new RuntimeException("MAP复杂类型格式不对"+dataType);
      }

    } else if (dataType.toLowerCase().startsWith("array")) {
      Matcher matcher =  ARRAY_TYPE_REGEX.matcher(dataType.toLowerCase());
      if(matcher.find()){
        String type = matcher.group(2);
        return  Types
            .buildGroup(repetition).as(OriginalType.LIST)
            .addFields(convertField(type,"array",Repetition.REPEATED))
            .named(name);
      }else{
        throw new RuntimeException("MAP复杂类型格式不对"+dataType);
      }

    } else {
      throw new RuntimeException("不是复杂类型");
    }

  }


}
