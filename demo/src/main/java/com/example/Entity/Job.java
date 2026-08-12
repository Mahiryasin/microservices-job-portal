package com.example.Entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getters and Setters
@Document// NOSQL database
@AllArgsConstructor(staticName = "create") // Parametrized constructor
@NoArgsConstructor
public class Job {

    @Id // Primary Key
    private String id;

    
    private String description;

    private String company;

    private Set<String> skills;

    private Integer salary;

    private Boolean isRemote;
    
}
