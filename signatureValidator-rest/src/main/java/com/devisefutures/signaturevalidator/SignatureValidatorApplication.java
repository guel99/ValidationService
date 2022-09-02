package com.devisefutures.signaturevalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SignatureValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignatureValidatorApplication.class, args);
	}

}
