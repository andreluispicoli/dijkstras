package com.dijkstras_algorithm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DijkstrasAlgController.class)
class DijkstrasAlgControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void whenValidRequestBestRouteReturns200() throws Exception {
    mockMvc.perform(post("/bestRoute")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"origin\": \"GRU\", \"destiny\": \"CDG\"}"))
        .andExpect(status().isOk());
  }

  @Test
  void whenInvalidRequestBestRouteReturns400() throws Exception {
    mockMvc.perform(post("/bestRoute")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void whenValidRequestNewRouteReturns200() throws Exception {
    mockMvc.perform(post("/newRoute")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"origin\": \"GRU\", \"destiny\": \"CDG\", \"cost\": \"75\"}"))
        .andExpect(status().isOk());
  }

  @Test
  void whenInvalidRequestNewRouteReturns400() throws Exception {
    mockMvc.perform(post("/newRoute")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }


}