package com.dijkstras_algorithm.exceptions;

public class NotReachableRouteException extends RuntimeException {

  private static final long serialVersionUID = 4448959547828960150L;

  public NotReachableRouteException(Object... parameters) {
    super(String.valueOf(parameters));
  }
}
