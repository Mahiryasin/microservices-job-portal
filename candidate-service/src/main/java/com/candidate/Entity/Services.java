package com.candidate.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "create")
@Data
@NoArgsConstructor
public class Services {

    private String name;

    private int port;

    private String hostPort;

    private String url;

    private String envVariable;

}
