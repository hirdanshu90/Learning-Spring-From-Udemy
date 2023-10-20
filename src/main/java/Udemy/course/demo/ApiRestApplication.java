package Udemy.course.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

// import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan({ "Udemy.course.controller", "Udemy.course.service" })
@EntityScan("Udemy.course.entity")
@EnableJpaRepositories("Udemy.course.repository")
@EnableScheduling
public class ApiRestApplication {

	public static void main(String[] args) {
		System.out.println("Started the udemy course");
		SpringApplication.run(ApiRestApplication.class, args);
	}

}
