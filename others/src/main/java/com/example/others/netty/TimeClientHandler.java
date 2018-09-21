package com.example.others.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

public class TimeClientHandler implements Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandler(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    try{
                        handleInput(key);
                    }catch (Exception e){
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
        if(selector!=null){
            try {
                selector.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(socketChannel!=null){
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws Exception {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel)key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector,SelectionKey.OP_READ);
                    doWriter(sc);
                }
            }
            if(key.isReadable()){

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if(readBytes>0){
                    buffer.flip();
                    byte[] bytes =new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String body=new String(bytes,Charset.forName("utf8"));
                    System.out.println("now is : "+body );
                    this.stop=true;
                }else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }else{

                }

            }
        }
    }

    private void doConnect() throws Exception {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWriter(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    private void doWriter(SocketChannel socketChannel) throws Exception {

        byte[] req = "query time order".getBytes(Charset.forName("utf8"));
        ByteBuffer buffer = ByteBuffer.allocate(req.length);
        buffer.put(req);
        buffer.flip();
        socketChannel.write(buffer);

        if (!buffer.hasRemaining()) {
            System.out.println("send success");
        }

    }
}
