package com.dijkstras_algorithm.controller;

import com.dijkstras_algorithm.business.DijkstrasBusiness;
import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.NewRoute;
import com.dijkstras_algorithm.model.Node;
import com.dijkstras_algorithm.model.Request;
import com.dijkstras_algorithm.model.Response;
import com.dijkstras_algorithm.util.GraphFeeder;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DijkstrasAlgController {

  @PostMapping("/bestRoute")
  public Response DijkstrasFinderBestRoute(@RequestBody Request request) throws IOException {

    var business = new DijkstrasBusiness();

    Graph graph = GraphFeeder.fillMatrixByFile(GraphFeeder.getPath("src/main/resources/input-file.txt"));

    var response = business.DijkstrasAlgorithm(
        graph,
        new Node(request.getOrigin(), new ArrayList<Node.Neighbors>()),
        new Node(request.getDestiny(), new ArrayList<Node.Neighbors>())
    );

    return response;
  }

  @PostMapping("/newRoute")
  public Response DijkstrasFinderNewRoute(@RequestBody NewRoute newRoute) throws IOException {
    var business = new DijkstrasBusiness();
    business.CreateNewRouteInFile(GraphFeeder.getPath("src/main/resources/input-file.txt"), newRoute);
    return new Response("New route "+newRoute.getOrigin()+" - "+newRoute.getDestiny()+" was created with sucess!");
  }

}
