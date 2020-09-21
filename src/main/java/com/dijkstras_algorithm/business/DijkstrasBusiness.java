package com.dijkstras_algorithm.business;

import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.Node;
import com.dijkstras_algorithm.model.Response;
import com.dijkstras_algorithm.util.GraphFeeder;

import java.io.IOException;
import java.util.HashMap;

public class DijkstrasBusiness {

  public Response DijkstrasAlgorithm(Node start, Node goal) throws IOException {

    Graph graph = GraphFeeder.fillMatrixByFile();

    HashMap<String, Integer> totalCost = new HashMap<>();
    HashMap<String, String> previousNodes = new HashMap<>();

    for (Node node : graph.getNodes()) {
      if(node.getNode() != start.getNode()) {
        totalCost.put(node.getNode(), Integer.MAX_VALUE);
      }
    }
    totalCost.put(start.getNode(), 0);

    Node minDistance = null;

    for (Node node : graph.getNodes()) {
      if(minDistance == null) {
        totalCost.put(node.getNode(), 0);
      }
      minDistance = node;
      for (Node.Neighbors neighbor : node.getNeighbors()) {

        var newDist = neighbor.getCost() + totalCost.get(minDistance.getNode());
        var currentDist = totalCost.get(neighbor.getNode());

        if (newDist < currentDist) {
          totalCost.put(neighbor.getNode(), newDist);
          previousNodes.put(neighbor.getNode(), node.getNode());
        }
      }
    }

    var previousNode = previousNodes.get(goal.getNode());
    var trackPrecedent = goal.getNode();

    while(!previousNode.equals(start.getNode())){
      trackPrecedent = previousNode + " " + trackPrecedent;
      previousNode = previousNodes.get(previousNode);
    }
    trackPrecedent = start.getNode() + " " + trackPrecedent;

    var totalCostGoal = totalCost.get(goal.getNode());

    return new Response("The best route was find with success!", new Response.Payload(trackPrecedent, totalCostGoal));
  }
}
