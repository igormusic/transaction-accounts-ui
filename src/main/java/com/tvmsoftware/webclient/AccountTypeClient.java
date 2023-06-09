package com.tvmsoftware.webclient;

import org.openapitools.client.model.AccountType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AccountTypeClient {

    private final WebClient webClient;
    private final String apiUrl;

    public AccountTypeClient(WebClient.Builder webClientBuilder, @Value("${api.url}") String apiUrl) {
        this.webClient = webClientBuilder.build();
        this.apiUrl = apiUrl;
    }

    public List<AccountType> getAll() {

        return webClient.get()
                .uri(apiUrl + "/accounttypes")
                .retrieve()
                .bodyToFlux(AccountType.class)
                .collectList()
                .block();

    }
}
