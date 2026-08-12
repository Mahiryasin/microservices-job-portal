package com.project.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@RefreshScope
@RequiredArgsConstructor
@Data
public class properties {
    @Value("${build.version:2.0}")
    private  String version;

}
