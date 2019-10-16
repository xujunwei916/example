package com.example.others.ast;

public class ASTNode {
    private ASTNodeType type;
    private double value;
    private ASTNode leftChild;
    private ASTNode rightChild;

    public ASTNode() {
        type = ASTNodeType.UNDEFINED;
        value = 0;
        leftChild = null;
        rightChild = null;
    }

    public ASTNode(ASTNodeType type, double value, ASTNode leftChild, ASTNode rightChild) {
        this.type = type;
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        String str = "--------------------------------------------\n";
        switch (type) {
            case NUMBER_VALUE:
                str += "node_type: NUMBER_VALUE\n";
                str += "value: " + value + "\n";
                break;
            case OPERATOR_PLUS:
                str += "node_type: OPERATOR_PLUS\n";
                break;
            case OPERATOR_MINUS:
                str += "node_type: OPERATOR_MINUS\n";
                break;
            case OPERATOR_MUL:
                str += "node_type: OPERATOR_MUL\n";
                break;
            case OPERATOR_DIV:
                str += "node_type: OPERATOR_DIV\n";
                break;
            case OPERATOR_SIN:
                str += "node_type: OPERATOR_SIN\n";
                break;
            case OPERATOR_COS:
                str += "node_type: OPERATOR_COS\n";
                break;
            default:
                str += "ERROR!!!!!!!!!!!!!!\n";
                break;
        }
        if (leftChild != null) {
            str += "left_child";
            str += leftChild.toString();
        } else
            str += "left_child is null\n";

        if (rightChild != null) {
            str += "right_child";
            str += rightChild.toString();
        } else
            str += "right_child is null\n";

        str += "--------------------------------------------\n";
        return str;
    }

    public ASTNodeType getType() {
        return type;
    }

    public void setType(ASTNodeType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ASTNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ASTNode leftChild) {
        this.leftChild = leftChild;
    }

    public ASTNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(ASTNode rightChild) {
        this.rightChild = rightChild;
    }
}