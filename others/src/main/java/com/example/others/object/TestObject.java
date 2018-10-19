package com.example.others.object;

public class TestObject {
    static String s = "aaaaaaaaaddaa";
    public static void main(String[] args) {




        OO o  = new OO() {

            @Override
            public void print() {

                System.out.println(s);
            }
        };
        o.print();
        s="ssss";
        o.print();




    }
}




