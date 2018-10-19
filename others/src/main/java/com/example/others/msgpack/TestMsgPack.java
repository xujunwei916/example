package com.example.others.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

public class TestMsgPack {
    public static void main(String[] args) throws Exception {

        List<String> src = new ArrayList<>();
        src.add("dsafasdf");
        src.add("dfsasdfsdfdsfds");
        src.add("dsafasassdsfdf");
        src.add("dsafasdfdfdsfdsssdf");
        MessagePack messagePack = new MessagePack();
        byte [] raw =messagePack.write(src);

        List<String> dst1 = messagePack.read(raw,Templates.tList(Templates.TString));
        System.out.println(dst1);
        System.out.println(raw.length);


    }
}
