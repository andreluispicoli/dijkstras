package com.dijkstras_algorithm.model;

public class Request {

  private String origin;
  private String destiny;

  public Request(String origin, String destiny) {
    this.origin = origin;
    this.destiny = destiny;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestiny() {
    return destiny;
  }
}
