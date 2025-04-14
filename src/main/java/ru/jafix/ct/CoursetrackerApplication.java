package ru.jafix.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.jafix.ct.repository.UserRepository;

@SpringBootApplication
public class CoursetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursetrackerApplication.class, args);
	}
}
