package com.dijkstras_algorithm.model;

import org.springframework.lang.NonNull;

public class Response {

  private final String message;

  private Payload payload;

  public Response(String message, Payload payload) {
    this.message = message;
    this.payload = payload;
  }

  public Response(String message){
    this.message = message;
  }

  public static class Payload{
    @NonNull
    private final String path;

    @NonNull
    private final Integer cost;

    public Payload(@NonNull String path, @NonNull Integer cost) {
      this.path = path;
      this.cost = cost;
    }

    @NonNull
    public String getPath() {
      return path;
    }

    @NonNull
    public Integer getCost() {
      return cost;
    }
  }

  public String getMessage() {
    return message;
  }

  public Payload getPayload() {
    return payload;
  }
}
