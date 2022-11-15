package com.sneakpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.sneakpick")
@EnableJpaRepositories
@EnableTransactionManagement
public class SneackpickApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SneackpickApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SneackpickApplication.class);
	}

}
