package com.dijkstras_algorithm.model;

import java.util.List;

public class Node {
  private final String node;
  private final List<Neighbors> neighbors;

  public Node(String node, List<Neighbors> neighbors) {
    this.node = node;
    this.neighbors = neighbors;
  }

  public static class Neighbors{

   private final String node;
   private final Integer cost;

    public Neighbors(String node, Integer cost) {
      this.node = node;
      this.cost = cost;
    }

    public String getNode() {
      return node;
    }

    public Integer getCost() {
      return cost;
    }
  }

  public String getNode() {
    return node;
  }

  public List<Neighbors> getNeighbors() {
    return neighbors;
  }
}