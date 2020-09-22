package com.dijkstras_algorithm.exceptions;

public class EmptyFileException extends RuntimeException {

  private static final long serialVersionUID = -7565844802108435796L;

  public EmptyFileException(Object... parameters) {
    super(String.valueOf(parameters));
  }
}
