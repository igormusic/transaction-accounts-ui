package com.tvmsoftware.webclient;

import org.openapitools.client.model.AccountInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AccountClient {

    private final WebClient webClient;
    private final String apiUrl;

    public AccountClient(WebClient.Builder webClientBuilder, @Value("${api.url}") String apiUrl) {
        this.webClient = webClientBuilder.build();
        this.apiUrl = apiUrl;
    }

    public List<AccountInfo> getAll() {

        return webClient.get()
                .uri(apiUrl + "/accounts/")
                .retrieve()
                .bodyToFlux(AccountInfo.class)
                .collectList()
                .block();

    }
}
