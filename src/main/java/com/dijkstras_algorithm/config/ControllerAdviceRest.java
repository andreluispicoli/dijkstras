package com.dijkstras_algorithm.config;

import com.dijkstras_algorithm.exceptions.EmptyFileException;
import com.dijkstras_algorithm.exceptions.NotReachableRouteException;
import com.dijkstras_algorithm.exceptions.RouteDoestNotExistsException;
import com.dijkstras_algorithm.model.Response;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(0)
public class ControllerAdviceRest {

  @ExceptionHandler(EmptyFileException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> NotFoundFilePathException(){
    return new ResponseEntity<>(new Response("File has no data to be iterable!"), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RouteDoestNotExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> RouteDoestNotExistsException(){
    return new ResponseEntity<>(new Response("Route does not exists, check available routes!"), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotReachableRouteException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> NotReachableRouteException(){
    return new ResponseEntity<>(new Response("Route is not possible to be reachable, please insert an available one"), HttpStatus.BAD_REQUEST);
  }

}
