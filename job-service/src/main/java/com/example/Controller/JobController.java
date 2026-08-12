package com.example.Controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.example.DTO.JobDTO;
import com.example.Service.JobService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.bson.Document;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/jobs",produces="application/json")
public class JobController {

    private final JobService jobService;
    private final ReactiveMongoTemplate mongoTemplate;

  
    @GetMapping("/all")
    public Flux<JobDTO> getAllJobs() {
         return jobService.getAllJobs();

    }
    /**
     * @param skills
     * @return
     */
    @GetMapping("/")
    public Flux<JobDTO> findJobBySkills(@RequestParam(name = "skills") Set<String> skills) throws Exception{
        return jobService.findJobBySkills(skills);

    }
    
    
    @GetMapping("/{id}")
    public Mono<JobDTO> getJobById(@PathVariable(name = "id") String id) throws Exception{
        return jobService.getJobById(id);
    }
    
    @PostMapping("/save")
    public Mono<JobDTO> saveJob(@RequestBody JobDTO jobDTO) throws Exception{
        return jobService.saveJob(jobDTO);
    }
    @GetMapping(value = "/all", params = "skills")
    public Flux<JobDTO> findBySkills(@RequestParam(name = "skills") String skill) throws Exception{
      return jobService.findBySkills(skill);
    }

}
