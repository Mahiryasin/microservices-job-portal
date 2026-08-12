package com.candidate.Service;

import org.springframework.stereotype.Service;

import com.candidate.Reporistory.CandidateReporistory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.candidate.DTO.CandidateDTO;
import com.candidate.DTO.CandidateDetailsDTO;
import com.candidate.Mapper.EntityDtoUtil;

@Service
public class CandidateService {

    private final CandidateReporistory candidateReporistory;

    private final EntityDtoUtil entityDtoUtil;

    private final JobClient jobClient;

    public CandidateService(CandidateReporistory candidateReporistory,EntityDtoUtil entityDtoUtil,JobClient jobClient){
        this.candidateReporistory=candidateReporistory;
        this.entityDtoUtil=entityDtoUtil;
        this.jobClient=jobClient;
    }

    public Flux<CandidateDTO> getallCandidate(){
      return  candidateReporistory.findAll().map(entityDtoUtil::convertToCandidateDTO);
    }
    public Mono<CandidateDetailsDTO> getbyid(String id){
      return  candidateReporistory.findById(id).map(entityDtoUtil::convertToCandidateDetailsDTO)   
               .flatMap(this::addCandidateDetails);
               
        }

    public Mono<CandidateDetailsDTO> addCandidateDetails(CandidateDetailsDTO candidateDetailsDTO){
      return  this.jobClient.getRecomendedJobs(candidateDetailsDTO.getSkills())
      .doOnNext(candidateDetailsDTO::setJobDTOs).thenReturn(candidateDetailsDTO);

    }
    public Mono<CandidateDTO> saveCandidate(CandidateDTO candidateDTO){
     return Mono.just(candidateDTO).map(entityDtoUtil::convertToCandidate)
      .flatMap(candidateReporistory::save).map(entityDtoUtil::convertToCandidateDTO);
     }

}
