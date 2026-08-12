package com.candidate.Entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Candidate {
    @Id
    private String id;

    private String name;

    private Set<String> skills;

}
