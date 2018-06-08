package com.example.spark.job.example.dl4j;

import org.nd4j.jita.conf.CudaEnvironment;

public class TestDl4j {
    public static void main(String[] args) {
        CudaEnvironment.getInstance().getConfiguration().allowMultiGPU(true);
    }
}
