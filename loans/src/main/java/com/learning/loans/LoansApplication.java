package com.learning.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
