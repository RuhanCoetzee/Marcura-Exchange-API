package com.marcura.exchange.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MarcuraExchangeRateAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcuraExchangeRateAPIApplication.class, args);
	}
}
