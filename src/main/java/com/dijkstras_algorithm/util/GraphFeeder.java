package com.dijkstras_algorithm.util;

import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.NewRoute;
import com.dijkstras_algorithm.model.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphFeeder {

  public static Graph fillMatrixByFile() throws IOException {

    File resourcesDirectory = new File("src/main/resources/input-file.txt");
    var path =  resourcesDirectory.getAbsolutePath();

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
    return new Graph(nodes);
  }

  public static void createNewRouteInFile(NewRoute newRoute) throws IOException {

    File resourcesDirectory = new File("src/main/resources/input-file.txt");
    var path =  resourcesDirectory.getAbsolutePath();

    BufferedReader csvReader = new BufferedReader(new FileReader(path));
    String row;
    HashMap<String, Integer> neighborsHashMap = new HashMap<>();

    List<String[]> datas = new ArrayList<String[]>();

    boolean updated = false;

    List<Node> nodes = new ArrayList<Node>();
    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");
      if(data[0].equals(newRoute.getOrigin()) && data[1].equals(newRoute.getDestiny())){
        updated = true;
        data[2] = newRoute.getCost().toString();
      }
      datas.add(data);
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
