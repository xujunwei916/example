package com.example.spark.job.example.streaming

import java.util.Properties
import java.util.concurrent.atomic.AtomicReference

import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, WatchedEvent, Watcher, ZooKeeper}


object NginxLogReaderStreaming {


    val logger = Logger.getLogger(this.getClass)

    val CTRL_A = "\u0001"
    val CTRL_B = "\u0002"


    def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf
        sparkConf.setAppName("test nginx name")
        sparkConf.setMaster("local[2]")
        val ssc = new StreamingContext(sparkConf, Seconds(5))
        val properties = readPropertiesByClasspath("core.properties")

        //        val brokers = "172.17.20.231:9092,172.16.0.180:9092"

        val offsetRanges = new AtomicReference[Array[OffsetRange]]

        val quorum = properties.getProperty("zookeeper.quorum")
        val zooKeeperPath = properties.getProperty("nginx.log.zookeeper.path")

        val checkpoint = readPartitionByZk(quorum, zooKeeperPath)

        val kafkaParams = Map[String, Object](
            "bootstrap.servers" -> properties.get("bootstrap.servers"),
            "key.deserializer" -> classOf[StringDeserializer],
            "value.deserializer" -> classOf[StringDeserializer],
            "group.id" -> properties.get("group.id"),
            "auto.offset.reset" -> properties.get("auto.offset.reset"),
            "enable.auto.commit" -> properties.getProperty("enable.auto.commit", "false")
                .toBoolean.asInstanceOf[AnyRef]
        )

        val topics = Array(properties.getProperty("topic"))
        val stream =
            if (checkpoint.isEmpty) {
                KafkaUtils.createDirectStream[String, String](
                    ssc,
                    PreferConsistent
                    , Subscribe[String, String](topics, kafkaParams))
            } else {
                KafkaUtils.createDirectStream[String, String](
                    ssc,
                    PreferConsistent
                    , Subscribe[String, String](topics, kafkaParams, checkpoint))
            }

        stream.map(item=>(item.key(),item.value())).count().print()


        stream.foreachRDD(
            rdd => {
                writePartition2Zk(quorum,zooKeeperPath,rdd.asInstanceOf[HasOffsetRanges].offsetRanges)
            }
        )

        ssc.start()
        ssc.awaitTermination()


    }


    /**
      * 读取zookeeper信息
      *
      * @param quorum
      * @param path
      * @return
      */
    def readPartitionByZk(quorum: String, path: String): Map[TopicPartition, Long] = {
        val zkClient = new ZooKeeper(quorum, 30000, new Watcher {
            override def process(event: WatchedEvent): Unit = {
                logger.info("process zookeeper: type = " + event.getType + " , path = " + event.getPath)
            }
        })

        print(zkClient.getSessionId)
        val result: Map[TopicPartition, Long] =
            if (zkClient.exists(path, true) != null) {
                val data = new String(zkClient.getData(path, true, null))
                data.split(CTRL_A).map(it => {
                    val o = it.split(CTRL_B)
                    new TopicPartition(o(0), o(1).toInt) -> o(2).toLong
                }).toMap[TopicPartition, Long]
            }
            else {
                Map()
            }
        zkClient.close()
        result
    }

    /**
      * 写zookeeper
      *
      * @param quorum
      * @param path
      * @param offsetRange
      * @return
      */
    def writePartition2Zk(quorum: String, path: String, offsetRange: Array[OffsetRange]) = {

        if (offsetRange == null || offsetRange.isEmpty) {}
        else {
            val zkClient = new ZooKeeper(quorum, 30000, new Watcher {
                override def process(event: WatchedEvent): Unit = {
                    logger.info("process zookeeper: type = " + event.getType + " , path = " + event.getPath)
                }
            })
            val data = offsetRange
                .map(offset => offset.topic + CTRL_B + offset.partition + CTRL_B + offset.fromOffset)
                .mkString(CTRL_A)
            if (zkClient.exists(path, true) == null)
                zkClient.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
            else
                zkClient.setData(path, data.getBytes(), -1)

        }


    }


    /**
      * 读取配置文件
      *
      * @param fileName
      * @return
      */
    def readPropertiesByClasspath(fileName: String) = {
        val properties = new Properties()
        properties.load(ClassLoader.getSystemClassLoader.getResourceAsStream(fileName))
        properties

    }

}
