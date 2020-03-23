package com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;

public class TestHbase {
    public static void main(String[] args) throws Exception {


        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Table htable = connection.getTable(TableName.valueOf("userRfmInfo"));

        Get get = new Get("008A5AE2F2BD000DBCBC9C7E2C38".getBytes());
        long start = System.currentTimeMillis();
//        List<Get> gets = new ArrayList<>();

//        DfUserFavor first=  DfUserFavor.getFavorFromCell(htable.get(get));
//        for (int i = 0; i < 10000; i++) {
//
//            Result result = htable.get(get);
//            first.mergeUserFavor(DfUserFavor.getFavorFromCell(result)) ;
//        }

        Result result = htable.get(get);
        System.out.println(result.listCells().get(0).getTimestamp());


        System.out.println("cost : " + (System.currentTimeMillis() - start));

        htable.close();


    }
}
