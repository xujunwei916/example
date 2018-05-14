/**
创建hive表
 */
create  table IF NOT EXISTS test_write_flume(
dt String,
body String,
test_map Map<String,String>)
PARTITIONED BY(`date_type` STRING,`date` String, `hour` STRING)
 ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\x01'
COLLECTION ITEMS TERMINATED BY '\x02'
MAP KEYS TERMINATED BY '\x03'
STORED AS ORCFILE;