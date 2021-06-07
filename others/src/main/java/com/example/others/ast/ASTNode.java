package com.example.others.ast;

import org.roaringbitmap.RoaringBitmap;

public class ASTNode {

  private ASTNodeType type;
  private RoaringBitmap value;
  private ASTNode leftChild;
  private ASTNode rightChild;

  public ASTNode() {
    type = ASTNodeType.UNDEFINED;
    value = new RoaringBitmap();
    leftChild = null;
    rightChild = null;
  }
  public ASTNode(ASTNodeType type, RoaringBitmap value, ASTNode leftChild, ASTNode rightChild) {
    this.type = type;
    this.value = value;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  @Override
  public String toString() {
    String str = "--------------------------------------------\n";
    switch (type) {
      case BITSET_VALUE:
        str += "node_type: BITSET_VALUE \n";
        str += "value: " + value + "\n";
        break;
      case OPERATOR_AND:
        str += "node_type: OPERATOR_AND\n";
        break;
      case OPERATOR_AND_NOT:
        str += "node_type: OPERATOR_AND_NOT\n";
        break;
      case OPERATOR_OR:
        str += "node_type: OPERATOR_OR\n";
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

  public RoaringBitmap getValue() {
    return value;
  }

  public void setValue(RoaringBitmap value) {
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
