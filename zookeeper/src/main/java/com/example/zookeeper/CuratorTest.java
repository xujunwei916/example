package com.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .ensembleProvider(new DefaultEnsembleProvider("cdh-01:2181"))
                .retryPolicy(new ExponentialBackoffRetry(100, 10, 30000))
                .build();
        zkClient.start();
        String data = new String(zkClient.getData().forPath("/tmp/aaa"), "utf-8");
        System.out.println(data);
        System.out.println(zkClient.getState());
//        zkClient.
    }
}
