package com.example.Service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DTO.JobDTO;
import com.example.Mapper.JobMapper;
import com.example.Repository.JobRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

  

    public Flux<JobDTO> getAllJobs() {
        System.out.println(jobRepository.findAll()+" --------------------------->");
        return jobRepository.findAll().map(t -> {
            try {
                return jobMapper.toJobDTO(t);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        });
    }

    public Flux<JobDTO> findJobBySkills(Set<String> skills){
        return jobRepository.findBySkillsIn(skills).map(t -> {
            try {
                return jobMapper.toJobDTO(t);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        });
    }

    public Mono<JobDTO> getJobById(String id) throws Exception{
      return jobRepository.findById(id).map(t -> {
        try {
            return jobMapper.toJobDTO(t);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    });
    }

    public Mono<JobDTO> saveJob(JobDTO jobDTO){
        return  Mono.just(jobDTO).map(jobMapper::toJob)
        .flatMap(jobRepository::save).map(t -> {
            try {
                return jobMapper.toJobDTO(t);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return jobDTO;
        });
        
    }

    public Flux<JobDTO> findBySkills(String skill){
       return jobRepository.findBySkills(skill).map(t -> {
        try {
            return jobMapper.toJobDTO(t);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    });
    }

}
