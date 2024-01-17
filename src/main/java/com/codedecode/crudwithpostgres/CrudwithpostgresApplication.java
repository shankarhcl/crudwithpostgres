package com.codedecode.crudwithpostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//optional
//@EnableBatchProcessing //spring-boot-starter-batch
public class CrudwithpostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudwithpostgresApplication.class, args);
	}

}
