package com.project;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "accounts")
@RefreshScope
@Schema(name = "Accounts Info", description = "infoDTO", example = "{message: accounts-microservice, contactDetails: {name: mahir yasin, email: mahiryasin17@gmail.com}, Oncallsupport: [0506 093 77 89, 0501 359 97 03]}"


)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class infoDTO {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> Oncallsupport;
}