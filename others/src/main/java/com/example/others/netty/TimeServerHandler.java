package com.example.others.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf8"));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            String currentTime=null;
            String body=null;
            while ((body=reader.readLine())!=null){
                System.out.println("the time server recive body = "+body);
                currentTime="query time order".equalsIgnoreCase(body)?new Date().toString():"BAD ORDER";
                writer.println(currentTime);
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {

        }


    }
}
