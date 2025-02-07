package com.azienda.dipendenti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DipendentiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DipendentiApplication.class, args);
	}

}
