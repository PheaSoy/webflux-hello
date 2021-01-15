package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyAppTest {

  @Autowired
  WebTestClient webTestClient;

  @Test
  void testHello() {
    webTestClient.get().uri("/hello")
        .accept(MediaType.TEXT_HTML)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(String.class).isEqualTo("Hello World");
  }

  @Test
  void testGetBook_expect_2xx() {
    webTestClient.get().uri("/book")
        .accept(MediaType.TEXT_HTML)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(Book.class).returnResult().getResponseBody().getTitle().equals("Cloud-Native Java");
  }

}
