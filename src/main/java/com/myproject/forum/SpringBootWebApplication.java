package com.myproject.forum;

import com.myproject.forum.models.Role;
import com.myproject.forum.models.User;
import com.myproject.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootWebApplication {


	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init(){
		User userAdmin = new User(
				"Admin",
				"Admin Last Name",
				"admin",
				passwordEncoder.encode("pass"),
				Arrays.asList(
						new Role("ROLE_ADMIN")));

		if (userRepository.findByEmail(userAdmin.getEmail()) == null){
			userRepository.save(userAdmin);
		}
		User userUser = new User(
				"User",
				"User Last Name",
				"user",
				passwordEncoder.encode("pass"),
				Arrays.asList(
						new Role("ROLE_USER")));

		if (userRepository.findByEmail(userUser.getEmail()) == null){
			userRepository.save(userUser);
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

}