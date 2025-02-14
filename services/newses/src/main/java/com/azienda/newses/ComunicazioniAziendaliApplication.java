package com.azienda.newses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComunicazioniAziendaliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunicazioniAziendaliApplication.class, args);
	}

}
