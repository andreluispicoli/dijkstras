package com.dijkstras_algorithm.util;

import static org.junit.jupiter.api.Assertions.*;

import com.dijkstras_algorithm.exceptions.EmptyFileException;
import com.dijkstras_algorithm.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GraphFeederTest {

  @Test
  @DisplayName("should return Graph Fed")
  void shouldReturnGraphFed() throws IOException {
    Graph graph = GraphFeeder.fillMatrixByFile(GraphFeeder.getPath("src/main/resources/input-file.txt"));
    assertNotEquals(graph.getNodes(), null);
    assertNotEquals(graph.getNodes().size(), 0);
  }

  @Test
  @DisplayName("should return Empty File Exception")
  void shouldReturnEmptyFileException() throws IOException {

    var path = "src/main/resources/input-file-empty.txt";
    var file = new File(path);
    file.createNewFile();

    EmptyFileException emptyFileException = assertThrows(EmptyFileException.class, () -> {
      GraphFeeder.fillMatrixByFile(GraphFeeder.getPath("src/main/resources/input-file-empty.txt"));
    });

    file.delete();
  }

  @Test
  @DisplayName("should return File Not Found Exception")
  void shouldReturnFileNotFoundException() throws IOException {
    FileNotFoundException emptyFileException = assertThrows(FileNotFoundException.class, () -> {
      GraphFeeder.fillMatrixByFile(GraphFeeder.getPath("src/main/resources/input-file-emptya.txt"));
    });
  }
}