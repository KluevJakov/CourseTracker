package ru.jafix.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.jafix.ct.entity.Role;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.repository.RoleRepository;
import ru.jafix.ct.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
public class CoursetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursetrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, RoleRepository roleRepository) {
		return (args) -> {
			List<Role> roles = roleRepository.findAll();

			System.out.println(roles);
		};
	}
}
