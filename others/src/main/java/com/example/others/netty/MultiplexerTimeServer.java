package com.example.others.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;


    public MultiplexerTimeServer(int port) {

        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("start server on port : " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.stop = true;
    }


    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("selectionKeys.size()" +selectionKeys.size() );
                for (SelectionKey key : selectionKeys) {
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        //smmm
                        System.out.println(e);
                        e.printStackTrace();
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void handleInput(SelectionKey key) throws Exception {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                if(sc!=null){
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }

            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String body = new String(bytes, Charset.forName("utf8"));
                    System.out.println("the time server recive body = " + body);
                    String currentTime = "query time order".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
                    doWriter(sc, currentTime);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                } else {

                }
            }
        }
    }

    private void doWriter(SocketChannel channel, String response) throws Exception {

        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes(Charset.forName("utf8"));
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
            System.out.println("writer ok");
        }
    }
}
