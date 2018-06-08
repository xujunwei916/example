package com.example.spark.job.example.dl4j
import com.ibm.gpuenabler.CUDAFunction
import org.apache.spark.{SparkConf, SparkContext}
import com.ibm.gpuenabler.CUDARDDImplicits._

object TestGPU {

    def main(args: Array[String]) = {
//        val masterURL = if (args.length > 0) args(0) else "local[*]"
        val sparkConf = new SparkConf().setAppName("GpuEnablerExample1")
        val sc = new SparkContext(sparkConf)

        val ptxURL = getClass.getResource("/GpuEnablerExamples.ptx")
        val mapFunction = new CUDAFunction(
            "multiplyBy2",
            Array("this"),
            Array("this"),
            ptxURL)

        val dimensions = (size: Long, stage: Int) => stage match {
            case 0 => (64, 256)
            case 1 => (1, 1)
        }
        val reduceFunction = new CUDAFunction(
            "sum",
            Array("this"),
            Array("this"),
            ptxURL,
            Seq(),
            Some((size: Long) => 2),
            Some(dimensions))

        val n = 10
        val output = sc.parallelize(1 to n, 1)
            .mapExtFunc((x: Int) => 2 * x, mapFunction)
            .reduceExtFunc((x: Int, y: Int) => x + y, reduceFunction)

        println("Sum of the list is " + output)
    }

}