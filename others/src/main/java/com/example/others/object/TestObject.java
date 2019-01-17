package com.example.others.object;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class TestObject {
    static String s = "aaaaaaaaaddaa";

    public static void main(String[] args) throws Exception {


//        OO o = new OO() {
//
//            @Override
//            public void print() {
//
//                System.out.println(s);
//            }
//        };
//        o.print();
//        s = "ssss";
//        o.print();

//        Bean1 bean1 = new Bean1();
//        bean1.setId(10);
//        bean1.setName("ajjj");
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(bean1);
//        objectOutputStream.close();
//
//        String result = new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
//        System.out.println(result);
         byte[] bytes = Base64.getDecoder().decode("rO0ABXNyAB9jb20uZXhhbXBsZS5vdGhlcnMub2JqZWN0LkJlYW4x1faK4jYYeK8CAAJKAAJpZEwABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cAAAAAAAAAAKdAAEYWpqag==");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
        Object o = inputStream.readObject();
        System.out.println(o);


    }
}

class Bean1 implements Serializable {
    final public static long serialVersionUID = -3029080995185264465L;
    String name;
    long id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public void getId3() {
//        id += 1;
//    }
        public void getId8() {
        id += 1;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.id;
    }
}




