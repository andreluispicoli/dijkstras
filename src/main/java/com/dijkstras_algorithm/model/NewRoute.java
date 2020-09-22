package com.dijkstras_algorithm.model;

public class NewRoute {

  private final String origin;
  private final String destiny;
  private final Integer cost;

  public NewRoute(String origin, String destiny, Integer cost) {
    this.origin = origin;
    this.destiny = destiny;
    this.cost = cost;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestiny() {
    return destiny;
  }

  public Integer getCost() {
    return cost;
  }
}
