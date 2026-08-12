package com.candidate.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class CandidateDTO {


    private String id;
    private String name;
    private Set<String> skills;
    private String hostName;
    

}
