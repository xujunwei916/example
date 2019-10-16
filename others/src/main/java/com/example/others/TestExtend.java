package com.example.others;

public class TestExtend {
    public static void main(String[] args) {
        B b = new B();

        ((A)b).print(1000);


    }
}

class  A {

    private int a = 10 ;
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }


    public  void print(int c) {

        System.out.println(c);
    }

}


class B extends A {
    public  void print(int c) {
//        A a = (A) this;
//        a.print(c);
//        System.out.println(10);
    }
}
