package com.candidate.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.candidate.DTO.JobDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JobClient {

    private final String baseUrl;

    private final WebClient webClient;

    public JobClient(@Value("${job.service.url}") @NonNull String baseUrl) {
        this.baseUrl = baseUrl;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<JobDTO>> getRecomendedJobs(Set<String> skills) {
        String uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path("/jobs")
                .queryParam("skills", skills)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(JobDTO.class)
                .collectList()
                .onErrorReturn(Collections.emptyList());

    }

}
