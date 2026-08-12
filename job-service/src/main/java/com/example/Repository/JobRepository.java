package com.example.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import com.example.Entity.Job;
import java.util.Set;

// reactive mongodb gibi asenkron iletişim icin !

public interface JobRepository extends ReactiveCrudRepository<Job, String> {

    Flux<Job> findBySkillsIn(Set<String> skills); // flux<job>=List<job>

    Flux<Job> findBySkills(String skill);

}
