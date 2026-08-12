package com.candidate.Mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.candidate.DTO.CandidateDTO;
import com.candidate.DTO.CandidateDetailsDTO;
import com.candidate.Entity.Candidate;
import com.candidate.util.AppUtil;

@Component
public class EntityDtoUtil {
   public CandidateDTO convertToCandidateDTO(Candidate candidate) {
    CandidateDTO candidateDTO=new CandidateDTO();
    BeanUtils.copyProperties(candidate, candidateDTO);
    try {
        candidateDTO.setHostName(AppUtil.getHostName());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return candidateDTO;
   }

   public CandidateDetailsDTO convertToCandidateDetailsDTO( Candidate candidate) {
    CandidateDetailsDTO candidateDetailsDTO=new CandidateDetailsDTO();
    BeanUtils.copyProperties(candidate, candidateDetailsDTO);
    return candidateDetailsDTO;
   }

   public Candidate convertToCandidate(CandidateDTO candidateDTO){
    Candidate candidate=new Candidate();
    BeanUtils.copyProperties(candidateDTO, candidate);
    return candidate;

   }

}
