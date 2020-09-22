package com.dijkstras_algorithm.business;

import com.dijkstras_algorithm.exceptions.NotReachableRouteException;
import com.dijkstras_algorithm.exceptions.RouteDoestNotExistsException;
import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.NewRoute;
import com.dijkstras_algorithm.model.Node;
import com.dijkstras_algorithm.model.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DijkstrasBusiness {

  public Response DijkstrasAlgorithm(Graph graph, Node start, Node goal) {

    var existsNodeStart = graph.getNodes()
        .stream()
        .filter(gp -> gp.getNode().equals(start.getNode()))
        .collect(Collectors.toList());
    var existsNodeGoal = graph.getNodes()
        .stream()
        .filter(gp -> gp.getNode().equals(goal.getNode()))
        .collect(Collectors.toList());

    if(existsNodeStart.size() == 0 || existsNodeGoal.size() == 0){
      throw new RouteDoestNotExistsException();
    }

    HashMap<String, Integer> totalCost = new HashMap<>();
    HashMap<String, String> previousNodes = new HashMap<>();

    for (Node node : graph.getNodes()) {
      if(!node.getNode().equals(start.getNode())) {
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

  public static void CreateNewRouteInFile(String path, NewRoute newRoute) throws IOException {

    BufferedReader csvReader = new BufferedReader(new FileReader(path));
    String row;

    List<String[]> datas = new ArrayList<>();

    boolean updated = false;
    boolean reachable = false;

    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");
      if(data[0].equals(newRoute.getOrigin()) && data[1].equals(newRoute.getDestiny())){
        updated = true;
        data[2] = newRoute.getCost().toString();
      }
      if((data[0].equals(newRoute.getOrigin()) || data[0].equals(newRoute.getDestiny())) ||
          (data[1].equals(newRoute.getOrigin()) || data[1].equals(newRoute.getDestiny()))){
        reachable = true;
      }
      datas.add(data);
    }
    //Verify if route can be add... if it's first one, does not throw exception
    if(!reachable && datas.size() > 0){
      throw new NotReachableRouteException();
    }

    if(!updated){
      String[] insertNewRoute = new String[3];
      insertNewRoute[0] = newRoute.getOrigin(); insertNewRoute[1] = newRoute.getDestiny(); insertNewRoute[2] = newRoute.getCost().toString();
      datas.add(insertNewRoute);
    }

    FileWriter csvWriter = new FileWriter(path);

    for (String[] rowData : datas) {
      csvWriter.append(String.join(",", rowData));
      csvWriter.append("\n");
    }
    csvWriter.flush();
    csvWriter.close();
  }


}
