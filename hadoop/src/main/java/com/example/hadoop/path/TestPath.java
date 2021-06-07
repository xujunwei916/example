package com.example.hadoop.path;

import com.example.hadoop.filter.HourlyPathFileFilter;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsShell;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Job;

public class TestPath {

  public static void main(String[] args)  throws Exception{
//    System.out.println("你好");
//    Integer i = Integer.parseInt("100000");
//    System.out.println(i==100000);

//    throw new RuntimeException("米好");
//    Configuration conf = new Configura
//    tion();
////    FsShell fsShell = new FsShell();
////    fsShell.run(new String[]{"-ls", "/"});
//
//
//    System.setProperty("fs.s3a.access.key", "2YPU1VR3OKIR0YWTU98X");
//    System.setProperty("fs.s3a.secret.key", "esSrzgoLVUCs8WBzqXlaJDQYrcKMzyWGFpjkwOXQ");
//    System.setProperty("fs.s3a.endpoint", "uat-haa-s3.hypers.com.cn");
//    System.setProperty("fs.s3a.connection.ssl.enabled", "true");
//    System.setProperty("fs.s3a.path.style.access","true");
//
//
//    FsShell.main(new String[]{"-Dfs.s3a.access.key=2YPU1VR3OKIR0YWTU98X","-Dfs.s3a.secret.key=esSrzgoLVUCs8WBzqXlaJDQYrcKMzyWGFpjkwOXQ","-Dfs.s3a.endpoint=uat-haa-s3.hypers.com.cn","-Dfs.s3a.connection.ssl.enabled=true","-Dfs.s3a.path.style.access=true","-ls", "s3a://uat-haa/"});

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


//    Configuration conf = new Configuration();
//
//    FileSystem fs = FileSystem.get(conf);
////
////
////
////
//    for (FileStatus fileStatus:fs.listStatus(new Path("/"))) {
//
//      System.out.println(fileStatus.getPath());
//    }
////



//    boolean result = fs.exists(path);
//    System.out.println(result);

//    for (Object o:System.getProperties().entrySet()
//    ) {
//      System.out.println(o);
//    }

    FileSystem fs = FileSystem.get(new Configuration());
    Job job = Job.getInstance(fs.getConf(), "ORCSample");

   Path path= new Path("hdfs://cdh-node0.hypers.com:8020/user/hive/warehouse/HAA/project=HAA/date=20210508/type=imp/section=default");
    FileInputFormat2 fileInputFormat = new FileInputFormat2();
    JobConf conf = (JobConf) job.getConfiguration();
    conf.set("mapreduce.input.pathFilter.class","com.example.hadoop.filter.HourlyPathFileFilter");
    FileInputFormat2.addInputPath(conf,path);
    FileStatus []fileStatuses =  fileInputFormat.listStatus2(conf);


    System.out.println(fileStatuses);
    for (FileStatus fileStatus: fileStatuses    ) {
      System.out.println(fileStatus.getPath());
    }



  }
}

class FileInputFormat2 extends  FileInputFormat{

  @Override
  public RecordReader getRecordReader(InputSplit split, JobConf job, Reporter reporter)
      throws IOException {
    return null;
  }
  public FileStatus[] listStatus2(JobConf job) throws IOException {
    return super.listStatus(job);
  }
}


