package com.example.demo;

import com.example.demo.beans.Book;
import com.example.demo.client.BookClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(BookClientService bookClientService) {
    return args -> {
      bookClientService.getBooks();
    };
  }

}

@Configuration
class GreetingRouter {

  @Bean
  RouterFunction<ServerResponse> routerFunction(GreetingHandler greetingHandler,
      BookHandler bookHandler) {
    return RouterFunctions
        .route(RequestPredicates.GET("/hello")
            .and(RequestPredicates.accept(MediaType.TEXT_HTML)), greetingHandler::hello)
        .andRoute(RequestPredicates.GET("/book"), bookHandler::getBook);
  }
}

@Component
class GreetingHandler {

  public Mono<ServerResponse> hello(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_HTML)
        .body(BodyInserters.fromValue("Hello World"));
  }

}

@Component
class BookHandler {

  public Mono<ServerResponse> getBook(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(new Book(1,"Cloud-Native Java", 100.0)));
  }
}


