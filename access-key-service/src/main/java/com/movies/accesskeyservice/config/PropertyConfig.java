package com.movies.accesskeyservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("access-key")
@Data
public class PropertyConfig {
    private Set<String> keys;
}
