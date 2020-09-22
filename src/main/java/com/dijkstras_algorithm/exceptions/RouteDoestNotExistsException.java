package com.dijkstras_algorithm.exceptions;

public class RouteDoestNotExistsException extends RuntimeException {

  private static final long serialVersionUID = 2038530407107089659L;

  public RouteDoestNotExistsException(Object... parameters) {
    super(String.valueOf(parameters));
  }
}
