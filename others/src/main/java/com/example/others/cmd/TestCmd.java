package com.example.others.cmd;



import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestCmd {
    public static void main(String[] args) throws Exception {
        DefaultExecutor executor = new DefaultExecutor();

        executor.setExitValues(null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
        executor.setStreamHandler(streamHandler);


        executor.execute(CommandLine.parse("/home/hadoop/test.sh --conf ssss -f sdkkkkd -k oodoodo"));


        String stderr = errorStream.toString();
        System.out.println(stderr);

        String queryResult = outputStream.toString();

        System.out.println(queryResult);

    }
}
