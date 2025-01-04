package com.example.demo.client;

import com.example.demo.entity.Book;
import org.springframework.web.reactive.function.client.WebClient;

public class BookServiceReactiveClient {

    private final String apiUrl = "http://localhost:8080/api/books";
    private final WebClient webClient;

    public BookServiceReactiveClient(WebClient webClient){
        this.webClient = WebClient.builder().baseUrl(apiUrl).build();
    }
     public Book getBookbyId(Long id) {
         return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
            .retrieve()
            .bodyToMono(Book.class)
            .block();
     }
}
