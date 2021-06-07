package com.example.others.ast;

import org.roaringbitmap.RoaringBitmap;

public class Token {

  TokenType type;
  RoaringBitmap value;
  char symbol;

  Token() {
    type = TokenType.ERROR;
    value = new RoaringBitmap();
  }

}
