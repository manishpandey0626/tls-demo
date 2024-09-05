package com.manish.tls.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TlsDemoApplication {


	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(TlsDemoApplication.class);
		SpringApplication.run(TlsDemoApplication.class, args);
		logger.info("Application started");
	}

}
