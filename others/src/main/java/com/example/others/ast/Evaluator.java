package com.example.others.ast;

public class Evaluator {
    public static double evaluate(ASTNode ast) throws EvaluatorException {
        if (null == ast)
            throw new EvaluatorException("Incorrect abstract syntax tree");
        switch (ast.getType()) {
            case NUMBER_VALUE:
                return ast.getValue();
            case UNARY_MINUS:
                return -Evaluator.evaluate(ast.getLeftChild());
            case OPERATOR_SIN:
                double temp = Evaluator.evaluate(ast.getLeftChild());
                return Math.sin(temp);
            case OPERATOR_COS:
                return Math.cos(Evaluator.evaluate(ast.getLeftChild()));
            case UNDEFINED:
                throw new EvaluatorException("Incorrect abstract syntax tree");
            default:
                double v1 = Evaluator.evaluate(ast.getLeftChild());
                double v2 = Evaluator.evaluate(ast.getRightChild());
                switch (ast.getType()) {
                    case OPERATOR_PLUS: return v1 + v2;
                    case OPERATOR_MINUS: return v2 - v1;
                    case OPERATOR_MUL: return v1 * v2;
                    case OPERATOR_DIV: return v2 / v1;
                }
        }
        throw new EvaluatorException("Incorrect abstract syntax tree");
    }
}
