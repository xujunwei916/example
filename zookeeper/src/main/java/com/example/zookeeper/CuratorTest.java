package com.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .ensembleProvider(new DefaultEnsembleProvider("cdh-01:2181"))
                .retryPolicy(new ExponentialBackoffRetry(100, 10, 30000))
                .build();
        zkClient.start();
        zkClient.create().withMode(CreateMode.EPHEMERAL).forPath("/tmp/ccc","000000".getBytes());
        String data = new String(zkClient.getData().forPath("/tmp/aaa"), "utf-8");
        System.out.println(data);
//        System.out.println(zkClient.getState());
        Thread.sleep(1000000L);
        zkClient.close();
    }
}
