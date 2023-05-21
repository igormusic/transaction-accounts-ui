package com.tvmsoftware.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AccountTypeClient {

    private final WebClient webClient;
    private final String apiUrl;

    public AccountTypeClient(WebClient.Builder webClientBuilder, @Value("${api.url}") String apiUrl) {
        this.webClient = webClientBuilder.build();
        this.apiUrl = apiUrl;
    }

    public void fetchData() {
        webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    // Handle the API response
                    System.out.println(response);
                });
    }
}
