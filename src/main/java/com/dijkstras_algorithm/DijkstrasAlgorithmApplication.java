package com.dijkstras_algorithm;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DijkstrasAlgorithmApplication implements CommandLineRunner{

  public static void main(String[] args) throws IOException {
    SpringApplication.run(DijkstrasAlgorithmApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if(args.length > 0) {
      Console.main(args);
    }
  }
}
