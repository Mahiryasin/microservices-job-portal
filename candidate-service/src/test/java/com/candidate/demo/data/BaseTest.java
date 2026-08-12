package com.candidate.demo.data;

import java.io.File;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.candidate.Entity.Services;

@Testcontainers
public class BaseTest {

   private static final Services MONGO = Services.create("mongodb", 27017, "0", 
   "mongodb://admin:123@%s:%d/candidate","HOST_PORT1");

   private static final Services job=Services.create("mockserver", 1080, "0", "http://%s:%d", "HOST_PORT2");

   @Container  
    private static final DockerComposeContainer<?> compose=new DockerComposeContainer(new File("docker-compose.yml"));


   @DynamicPropertySource
   static void mongoProperties(DynamicPropertyRegistry registry){
      compose.withEnv(job.getEnvVariable(),job.getHostPort().toString()); // sureklı degisen host portları
      compose.withEnv(MONGO.getEnvVariable(),MONGO.getHostPort().toString());// surekli degisen host port env olarak ata !
      compose.withExposedService(MONGO.getName(),MONGO.getPort(), Wait.forListeningPort());// 27017 dısarı ac !
      compose.withExposedService(job.getName(),job.getPort(),Wait.forHttp("/health").forStatusCode(200)); // 1080 ic portu dısarı ac !
      compose.start();


      var mongoHost=compose.getServiceHost(MONGO.getName(), MONGO.getPort()); // dısarı acılan 27017 portunu al !
      var mongoPort=compose.getServicePort(MONGO.getName(), MONGO.getPort()); // host portu al !

      var jobHost=compose.getServiceHost(job.getName(),job.getPort());
      var jobPort=compose.getServicePort(job.getName(),job.getPort());
      
      registry.add("spring.data.mongodb.uri", () -> String.format(MONGO.getUrl(), mongoHost,mongoPort));
      registry.add("job.service.url",() -> String.format(job.getUrl(),jobHost,jobPort));
      
 }
}
