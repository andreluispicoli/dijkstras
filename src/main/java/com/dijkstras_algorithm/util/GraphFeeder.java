package com.dijkstras_algorithm.util;

import com.dijkstras_algorithm.exceptions.EmptyFileException;
import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphFeeder {

  public static String getPath(String path){
    File resourcesDirectory = new File(path);
    return resourcesDirectory.getAbsolutePath();
  }

  public static Graph fillMatrixByFile(String path) throws IOException {

    BufferedReader csvReader = new BufferedReader(new FileReader(path));
    String row;
    HashMap<String, Integer> neighborsHashMap = new HashMap<>();

    List<Node> nodes = new ArrayList<Node>();
    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");

      var neighbor = new Node.Neighbors(data[1], Integer.parseInt(data[2]));
      List<Node.Neighbors> neighbors = new ArrayList<Node.Neighbors>();
      neighbors.add(neighbor);
      var nodeOrigin = new Node(data[0], neighbors);
      var nodeDestiny = new Node(data[1], new ArrayList<Node.Neighbors>());

      if (neighborsHashMap.containsKey(nodeOrigin.getNode())) {
        var index = neighborsHashMap.get(nodeOrigin.getNode());
        var existent_node = nodes.get(index);
        existent_node.getNeighbors().add(neighbor);
      } else {
        neighborsHashMap.put(nodeOrigin.getNode(), nodes.size());
        nodes.add(nodeOrigin);
      }
      if (!neighborsHashMap.containsKey(nodeDestiny.getNode())) {
        neighborsHashMap.put(nodeDestiny.getNode(), nodes.size());
        nodes.add(nodeDestiny);
      }
    }
    if(nodes.size() == 0){
      throw new EmptyFileException("File has no data to here");
    }

    return new Graph(nodes);
  }

}
