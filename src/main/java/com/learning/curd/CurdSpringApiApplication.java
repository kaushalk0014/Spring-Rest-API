package com.learning.curd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.learning.curd.dao")
@EntityScan(basePackages ="com.learning.curd.entiry" )
public class CurdSpringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdSpringApiApplication.class, args);
	}

}
