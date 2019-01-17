package com.example.spark.common

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, StringType, StructField, StructType}


class UserDefineUDAF extends UserDefinedAggregateFunction {
    override def inputSchema: StructType = StructType(List(StructField("time", StringType), StructField("btn", StringType)))

    override def bufferSchema: StructType = StructType(List(StructField("time", StringType), StructField("btn", StringType)))

    override def dataType: DataType = StringType

    override def deterministic: Boolean = true

    override def initialize(buffer: MutableAggregationBuffer): Unit = {
        buffer(0) = null
        buffer(1) = null
    }

    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
        val time = input.getString(0)
        if (buffer(0) == null || buffer(0).asInstanceOf[String].compare(time) < 0) {
            buffer(0) = time
            buffer(1) = input.getString(1)
        }
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
        val time = buffer2.getString(0)
        if (buffer1(0).asInstanceOf[String].compare(time) < 0) {
            buffer1(0) = time
            buffer1(1) = buffer2.getString(1)
        }
    }

    override def evaluate(buffer: Row): String = {
        buffer.getString(1)
    }
}