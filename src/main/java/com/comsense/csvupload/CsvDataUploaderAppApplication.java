package com.comsense.csvupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.comsense.csvupload.repo")
@EntityScan(basePackages = "com.comsense.csvupload.model")
public class CsvDataUploaderAppApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CsvDataUploaderAppApplication.class, args);
	}

}
