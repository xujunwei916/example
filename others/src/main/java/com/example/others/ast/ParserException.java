package com.example.others.ast;

public class ParserException extends Exception {

    public ParserException(String message, int index) {


        super(message + ", index = " + index);


    }
}
