package net.javalearner.springwithpostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "net.javalearner.springwithpostgres")

@SpringBootApplication
public class SpringwithpostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringwithpostgresApplication.class, args);
	}

}
