package com.emrubik.springcloud.samples;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcloudSamplesApplication {

	public static void main(String[] args) {
		SignatureAlgorithm alg = SignatureAlgorithm.RS256;
		SpringApplication.run(SpringcloudSamplesApplication.class, args);
	}
}
