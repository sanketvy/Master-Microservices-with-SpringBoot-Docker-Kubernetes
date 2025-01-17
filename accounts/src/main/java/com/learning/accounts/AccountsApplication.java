package com.learning.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info = @Info(
		title = "Accounts MS",
		contact = @Contact(
				name = "Sanket Vyawahare",
				email = "srvyawahare18@gmail.com"
		),
		license = @License(
			name = "Apache 2.0"
		),
		description = "This documentation contains all the MS exposed by accounts MS"))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
