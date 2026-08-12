package com.candidate.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {


    private String id;
    private String description;

    private String company;

    private Set<String> skills;

    private Integer salary;

    private Boolean isRemote;

}
