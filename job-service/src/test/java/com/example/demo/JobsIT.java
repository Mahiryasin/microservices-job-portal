package com.example.demo;

import com.example.DTO.JobDTO;
import com.example.demo.generic.BaseTest;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest // tum uygulamayı applicationcontext test ıcın ayaga kaldırır !
@AutoConfigureWebTestClient // web flux http isteklerıne hazırlamak ıcın !
class JobsIT extends BaseTest {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void allJobsTest() {
		webTestClient.get()
		.uri("/jobs/all")
		.exchange()
		.expectStatus()
		.is2xxSuccessful()
		.expectBody()
		.consumeWith((e)->{
			System.out.println(new String(e.getResponseBody()));
		})
		.jsonPath("$").isNotEmpty();
	}

	
	@Test
	void findBySkillsTest() {
		webTestClient.get()
		.uri("/jobs/all?skills=python")
		 .exchange()
		 .expectStatus()
		 .is2xxSuccessful()
		 .expectBody()
		 .consumeWith((e)->System.out.println(new String(e.getResponseBody())))
		 .jsonPath("$.size()").isEqualTo(2);
		
	}

	@Test
	void postMethodTest() {
		var jobdto=JobDTO.create(null, "desc", "exed", Set.of("python","sql"), 12345, true,null);

		webTestClient.post()
		.uri("/")
		.bodyValue(jobdto)
		.exchange()
		.expectStatus()
			.is2xxSuccessful()
			.expectBody()
			.consumeWith((e)->System.out.println(new String(e.getResponseBody())))
			.jsonPath("$.description").isNumber();
			
			
		webTestClient.post()
		.uri("/jobs/save")
		.bodyValue(jobdto)
		.exchange()
		.expectStatus()
		.is2xxSuccessful()
		.expectBody()
		.consumeWith((e)->System.out.println(e.getResponseBody()))
		.jsonPath("$.description").isNotEmpty()
		.jsonPath("$.company").isEqualTo("exed");

	}
		


}
