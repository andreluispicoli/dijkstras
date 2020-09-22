package com.dijkstras_algorithm;

import com.dijkstras_algorithm.business.DijkstrasBusiness;
import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.Node;
import com.dijkstras_algorithm.util.GraphFeeder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Console {

  public static void main(String[] args) throws IOException {

    while (true) {
      try {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your origin: ");
        String origin = scanner.next();

        System.out.println("Enter your destination: ");
        String destiny = scanner.next();

        var business = new DijkstrasBusiness();

        Graph graph = GraphFeeder.fillMatrixByFile(GraphFeeder.getPath("src/main/resources/input-file.txt"));

        var response = business.DijkstrasAlgorithm(
            graph,
            new Node(origin, new ArrayList<>()),
            new Node(destiny, new ArrayList<>())
        );
        System.out.println("Best route: " + response.getPayload().getPath() + " > " + response.getPayload().getCost());
        System.out.println("");
      } catch (Exception e) {
        System.out.println("Error to check best route, route should be known!");
      }
    }
  }
}
