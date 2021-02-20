package com.example.hadoop.path;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FsShell;

public class TestPath {

  public static void main(String[] args)  throws Exception{
    Configuration conf = new Configuration();
//    FsShell fsShell = new FsShell();
//    fsShell.run(new String[]{"-ls", "/"});


    System.setProperty("fs.s3a.access.key", "2YPU1VR3OKIR0YWTU98X");
    System.setProperty("fs.s3a.secret.key", "esSrzgoLVUCs8WBzqXlaJDQYrcKMzyWGFpjkwOXQ");
    System.setProperty("fs.s3a.endpoint", "uat-haa-s3.hypers.com.cn");
    System.setProperty("fs.s3a.connection.ssl.enabled", "true");
    System.setProperty("fs.s3a.path.style.access","true");


    FsShell.main(new String[]{"-Dfs.s3a.access.key=2YPU1VR3OKIR0YWTU98X","-Dfs.s3a.secret.key=esSrzgoLVUCs8WBzqXlaJDQYrcKMzyWGFpjkwOXQ","-Dfs.s3a.endpoint=uat-haa-s3.hypers.com.cn","-Dfs.s3a.connection.ssl.enabled=true","-Dfs.s3a.path.style.access=true","-ls", "s3a://uat-haa/"});

    // 设置文件系统为本地模式
//    conf.set("fs.s3a.access.key", "2YPU1VR3OKIR0YWTU98X");
//    conf.set("fs.s3a.secret.key", "esSrzgoLVUCs8WBzqXlaJDQYrcKMzyWGFpjkwOXQ");
//    conf.set("fs.s3a.endpoint", "uat-haa-s3.hypers.com.cn");
//    conf.set("fs.s3a.connection.ssl.enabled", "true");
//    conf.set("fs.s3a.path.style.access","true");
//    conf.set("fs.defaultFS","viewfs://clusterX");
//
//    conf.set("fs.viewfs.mounttable.clusterX.link./hdfs","hdfs://cdh-node0.hypers.com:8020/");
//    conf.set("fs.viewfs.mounttable.clusterX.link./hdfs3","hdfs://cdh-node0.hypers.com:8020/user");
//    conf.set("fs.viewfs.mounttable.clusterX.link./s3","s3a://uat-haa/cpo_uat");


//    FileSystem fs = FileSystem.get(conf);
//    Path path = new Path("file:///");
//
//    fs.exists(path);
//
//
//    for (FileStatus fileStatus:fs.listStatus(path)) {
//
//      System.out.println(fileStatus.getPath());
//    }
//



//    boolean result = fs.exists(path);
//    System.out.println(result);



  }


}
