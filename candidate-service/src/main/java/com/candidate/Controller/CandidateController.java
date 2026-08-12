package com.candidate.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidate.DTO.CandidateDTO;
import com.candidate.DTO.CandidateDetailsDTO;
import com.candidate.Service.CandidateService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public Flux<CandidateDTO> getAllCandidates() {
        return candidateService.getallCandidate();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CandidateDetailsDTO>> getCandidateById(@PathVariable String id) {
        return candidateService.getbyid(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<CandidateDTO> saveCandidate(@RequestBody CandidateDTO candidateDTO) {
        return candidateService.saveCandidate(candidateDTO);
    }
}
