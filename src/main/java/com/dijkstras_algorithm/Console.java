package com.dijkstras_algorithm;

import com.dijkstras_algorithm.business.DijkstrasBusiness;
import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.Node;
import com.dijkstras_algorithm.util.GraphFeeder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your origin: ");
    String origin = scanner.next();

    System.out.println("Enter your destination: ");
    String destiny = scanner.next();

    var business = new DijkstrasBusiness();

    Graph graph = GraphFeeder.fillMatrixByFile(GraphFeeder.getPath("src/main/resources/input-file.txt"));

    var response = business.DijkstrasAlgorithm(
        graph,
        new Node(origin, new ArrayList<Node.Neighbors>()),
        new Node(destiny, new ArrayList<Node.Neighbors>())
    );
    System.out.println("Best route: "+response.getPayload().getPath()+" > "+response.getPayload().getCost());
  }
}
