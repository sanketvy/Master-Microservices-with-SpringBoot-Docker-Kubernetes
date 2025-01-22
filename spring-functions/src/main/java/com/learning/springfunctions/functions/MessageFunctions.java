package com.learning.springfunctions.functions;

import com.learning.springfunctions.dto.CustomerDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    @Bean
    public Function<CustomerDto, CustomerDto> email(){
        return customerDto->{
            System.out.println("Email sent to : " + customerDto.email());
            return customerDto;
        };
    }

    @Bean
    public Function<CustomerDto, String> sms(){
        return customerDto->{
            System.out.println("SMS sent to : " + customerDto.email());
            return customerDto.name();
        };
    }

}
