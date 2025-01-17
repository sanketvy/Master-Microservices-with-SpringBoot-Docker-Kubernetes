package com.learning.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigurations {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
