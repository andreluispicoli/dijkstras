package com.dijkstras_algorithm.business;

import static org.junit.jupiter.api.Assertions.*;

import com.dijkstras_algorithm.exceptions.NotReachableRouteException;
import com.dijkstras_algorithm.exceptions.RouteDoestNotExistsException;
import com.dijkstras_algorithm.model.Graph;
import com.dijkstras_algorithm.model.NewRoute;
import com.dijkstras_algorithm.model.Node;
import com.dijkstras_algorithm.util.GraphFeeder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DijkstrasBusinessTest {

  Graph graphMockFixture(){
    var nodes = new ArrayList<Node>();

    var neighbors_gru = new ArrayList<Node.Neighbors>();
    neighbors_gru.add(new Node.Neighbors("BRC", 10));
    neighbors_gru.add(new Node.Neighbors("CDG", 75));
    neighbors_gru.add(new Node.Neighbors("SCL", 20));
    neighbors_gru.add(new Node.Neighbors("ORL", 56));
    var mock_node_gru = new Node("GRU", neighbors_gru);
    nodes.add(mock_node_gru);

    var neighbors_brc = new ArrayList<Node.Neighbors>();
    neighbors_brc.add(new Node.Neighbors("SCL", 5));
    var mock_node_brc = new Node("BRC", neighbors_brc);
    nodes.add(mock_node_brc);

    var neighbors_scl = new ArrayList<Node.Neighbors>();
    neighbors_scl.add(new Node.Neighbors("ORL", 20));
    var mock_node_scl = new Node("SCL", neighbors_scl);
    nodes.add(mock_node_scl);

    var neighbors_orl = new ArrayList<Node.Neighbors>();
    neighbors_orl.add(new Node.Neighbors("CDG", 5));
    var mock_node_orl = new Node("ORL", neighbors_orl);
    nodes.add(mock_node_orl);

    var mock_node_cdg = new Node("CDG", new ArrayList<>());
    nodes.add(mock_node_cdg);

    return new Graph(nodes);
  }

  @Test
  @DisplayName("should return best route cost")
  void shouldReturnBestRouteCost() {
    var business = new DijkstrasBusiness();
    var mock_origin = new Node("GRU", new ArrayList<>());
    var mock_destiny = new Node("CDG", new ArrayList<>());

    var graph_mock = graphMockFixture();

    var response = business.DijkstrasAlgorithm(graph_mock, mock_origin, mock_destiny);

    assertEquals(response.getMessage(), "The best route was find with success!");
    assertEquals(response.getPayload().getPath(), "GRU BRC SCL ORL CDG");
    assertEquals(response.getPayload().getCost(), 40);
  }

  @Test
  @DisplayName("should return best route cost alternative")
  void shouldReturnBestRouteCost2() {
    var business = new DijkstrasBusiness();
    var mock_origin = new Node("ORL", new ArrayList<>());
    var mock_destiny = new Node("CDG", new ArrayList<>());

    var graph_mock = graphMockFixture();

    var response = business.DijkstrasAlgorithm(graph_mock, mock_origin, mock_destiny);

    assertEquals(response.getMessage(), "The best route was find with success!");
    assertEquals(response.getPayload().getPath(), "ORL CDG");
    assertEquals(response.getPayload().getCost(), 5);
  }

  @Test
  @DisplayName("should return route does not exists exception")
  void shouldReturnRouteDoesNotExistsException() {
    var business = new DijkstrasBusiness();
    var mock_origin = new Node("XCO", new ArrayList<>());
    var mock_destiny = new Node("MTB", new ArrayList<>());

    var graph_mock = graphMockFixture();

    assertThrows(RouteDoestNotExistsException.class, () -> business.DijkstrasAlgorithm(graph_mock, mock_origin, mock_destiny));
  }

  @Test
  @DisplayName("should create new route in file")
  void shouldCreateNewRouteInFile() throws IOException {

    var path = "src/main/resources/input-file-test.txt";
    var file = new File(path);
    file.createNewFile();

    var inserted = false;

    var mock_route = new NewRoute("CDG", "RAO", 10);
    new DijkstrasBusiness().CreateNewRouteInFile(GraphFeeder.getPath(path), mock_route);

    BufferedReader csvReader = new BufferedReader(new FileReader(path));
    String row;

    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");
      if(data[0].equals(mock_route.getOrigin())
          && data[1].equals(mock_route.getDestiny())
          && data[2].equals(mock_route.getCost().toString())){
        inserted = true;
      }
    }
    assertTrue(inserted);
    file.delete();
  }

  @Test
  @DisplayName("should return NotReachableRouteException")
  void shouldReturnNotReachableRouteException() throws IOException {

    var path = "src/main/resources/input-file-test.txt";
    var file = new File(path);
    file.createNewFile();

    var mock_route_ok = new NewRoute("CDG", "RAO", 10);
    new DijkstrasBusiness().CreateNewRouteInFile(GraphFeeder.getPath(path), mock_route_ok);

    var mock_route = new NewRoute("OXI", "UAI", 10);
    assertThrows(NotReachableRouteException.class,
        () -> new DijkstrasBusiness().CreateNewRouteInFile(
            GraphFeeder.getPath(path),
            mock_route
        )
    );
    file.delete();
  }

}
