package com.project.Starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.annotation.Validated;

import com.project.infoDTO;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.val;

@SpringBootApplication
@ComponentScan(basePackages = {"com.project"}) // hangi paketler taranacak !
@EntityScan(basePackages ={"com.project.Entity"} )
@EnableJpaRepositories(basePackages = {"com.project.Reporistory"})  
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@EnableConfigurationProperties(value = infoDTO.class)
@EnableDiscoveryClient
@OpenAPIDefinition(
	info = @Info(
		title="Account MicroService",
		description = "Account Microservice Description",
		version = "v1",

		contact = @Contact(
			name = "mahir yasin başkes",
			email = "mahiryasin17@gmail.com",
			url = "https://mahiryasin/io"
		),
		license = @License(
			name="Apache 2.0",
			url="https://mahiryasin/io/license"
		)		 
		),
		externalDocs = @ExternalDocumentation(
			description = "Accounts Project Documentation",
			url = "https://mahiryasin/io/docs"
           )
)
public class AccountsApplication {

	public static void main(String[] args) {

	
		SpringApplication.run(AccountsApplication.class, args);
	}

}
