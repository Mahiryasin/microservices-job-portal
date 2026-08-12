package com.candidate.DTO;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CandidateDetailsDTO  extends CandidateDTO{

    private List<JobDTO> jobDTOs;

}
