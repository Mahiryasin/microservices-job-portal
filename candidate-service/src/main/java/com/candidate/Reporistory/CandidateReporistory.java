package com.candidate.Reporistory;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.candidate.Entity.Candidate;


public interface CandidateReporistory extends ReactiveCrudRepository<Candidate,String> {

}
