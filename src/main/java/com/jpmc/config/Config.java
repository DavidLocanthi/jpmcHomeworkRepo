package com.jpmc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
// TO SET FOR DIFFERENT ENVIRONMENT, can set this prefix to be interpolated and have additional data in the props
@ConfigurationProperties(prefix = "data")
@Data
public class Config {
    private Map<String, String> providerMap;

    public String check(String name) {

        return providerMap.get(name);
    };
}
