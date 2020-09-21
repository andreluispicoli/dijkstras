package com.dijkstras_algorithm;

import com.dijkstras_algorithm.business.DijkstrasBusiness;
import com.dijkstras_algorithm.model.Node;

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

    var response = business.DijkstrasAlgorithm(
        new Node(origin, new ArrayList<Node.Neighbors>()),
        new Node(destiny, new ArrayList<Node.Neighbors>())
    );
    System.out.println("Best route: "+response.getPayload().getPath()+" > "+response.getPayload().getCost());
  }
}
