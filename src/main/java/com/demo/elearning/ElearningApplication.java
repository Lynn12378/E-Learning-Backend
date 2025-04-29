package com.demo.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ElearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningApplication.class, args);
	}

}
