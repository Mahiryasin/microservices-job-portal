package com.candidate.demo;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.candidate.DTO.CandidateDTO;
import com.candidate.Starter.DemoApplication;
import com.candidate.demo.data.BaseTest;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureWebTestClient
class CandidateIT extends BaseTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getAllCandidates() {
		webTestClient.get()
				.uri("/candidate")
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.consumeWith((e) -> new String(e.getResponseBody()))
				.jsonPath("$").isArray();
	}

	@Test
	void saveCandidates() {
		var candidateDto = CandidateDTO.create(null, "ade", Set.of("c", "python"), null);
		
		webTestClient.post()
				.uri("/candidate")
				.bodyValue(candidateDto)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.name").isEqualTo("es");
	}

	@Test
	void jobServiceReturn4xx(){
		webTestClient.get()
		.uri("/candidate/1")
		.exchange()
		.expectStatus()
		.is2xxSuccessful()
		.expectBody()
		.jsonPath("$.id").isNotEmpty()
		.jsonPath("$.name").isEqualTo("es")
		.jsonPath("$.skills.size()").isEqualTo(2)
		.jsonPath("recomendation").isEmpty();
		
	}
  

}
