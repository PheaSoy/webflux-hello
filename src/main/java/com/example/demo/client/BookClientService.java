package com.example.demo.client;

import com.example.demo.beans.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class BookClientService {

  WebClient webClient = WebClient.create("http://localhost:3000");

  public void getBooks() {
    log.info("Get Books function is executed.");
    Mono<Book> resultBooks = webClient.get()
        .uri("/books/1")
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Book.class));
    log.info("Book result:{}",resultBooks.block());
  }

}
