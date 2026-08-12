package com.example.demo.generic;

import java.io.File;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String MONGO="mongodb";
    private static final String INIT_JS = "/docker-entrypoint-initdb.d/init.js";

    @SuppressWarnings("rawtypes")
    @ClassRule
    private static final DockerComposeContainer<?> compose = new DockerComposeContainer(new File("docker-compose.yml"));




    


  
            

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        compose.withExposedService(MONGO, MONGO_PORT,Wait.forListeningPort())
                .withEnv("MONGO_PORT","0") // rastgele port,
                .start();
    
            var host=compose.getServiceHost(MONGO, MONGO_PORT);
            var port=compose.getServicePort(MONGO, MONGO_PORT); // rastgele portu al !
               
        registry.add("spring.data.mongodb.uri", 
                () -> String.format("mongodb://mahir:1234@%s:%d/job", host,port));
    
    }

}
