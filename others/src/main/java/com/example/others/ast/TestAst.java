package com.example.others.ast;

public class TestAst {
    public static void main(String[] args) throws Exception {


        ASTNode astNode = Parser.parse("11*2+3*5");
       double result =   Evaluator.evaluate(astNode);

        System.out.println(result);


    }
}
