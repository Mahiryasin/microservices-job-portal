package com.example.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class JobDTO {

    private String id;
    private String description;

    private String company;

    private Set<String> skills;

    private Integer salary;

    private Boolean isRemote;

    private String hostName;

}
