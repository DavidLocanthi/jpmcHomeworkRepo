package com.jpmc;

import com.jpmc.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jpmc")
public class DemoApplication {

	@Autowired
	private Config config;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
