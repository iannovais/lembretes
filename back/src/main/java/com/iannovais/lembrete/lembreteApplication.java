package com.iannovais.lembrete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class lembreteApplication {

	public static void main(String[] args) {
		SpringApplication.run(lembreteApplication.class, args);
	}
}
