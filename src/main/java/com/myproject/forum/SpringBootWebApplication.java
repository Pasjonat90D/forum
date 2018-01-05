package com.myproject.forum;


import com.myproject.forum.models.*;
import com.myproject.forum.repository.CategoriesRepository;
import com.myproject.forum.repository.RepliesRepository;
import com.myproject.forum.repository.TopicsRepository;
import com.myproject.forum.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;


@SpringBootApplication
public class SpringBootWebApplication {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private CategoriesRepository categoryRepository;

	@Autowired
	private TopicsRepository topicRepository;

	@Autowired
	private RepliesRepository repliesRepository;



	@PostConstruct
	public void init(){
		User userAdmin = new User(
				"Admin",
				"Admin Last Name",
				"admin",
				passwordEncoder.encode("pass"),
				Arrays.asList(
						new Role("ROLE_ADMIN")));


		User userUser = new User(
				"User",
				"User Last Name",
				"user",
				passwordEncoder.encode("pass"),
				Arrays.asList(
						new Role("ROLE_USER")));

		Category category1 = new Category("Category1", "Description_Category_1");
		Category category2 = new Category("Category2", "Description_Category_2");
		Category category3 = new Category("Category3", "Description_Category_3");

		Topic topic1 = new Topic("Topic1", new Date());
		Topic topic2 = new Topic("Topic2", new Date());
		Topic topic3 = new Topic("Topic3", new Date());

		Reply replies1 = new Reply("Replies1", new Date());
		Reply replies2 = new Reply("Replies2", new Date());
		Reply replies3 = new Reply("Replies3", new Date());

		topic1.setCategory(category1);
		topic1.setUser(userAdmin);
		topic2.setCategory(category1);
		topic2.setUser(userAdmin);
		topic3.setCategory(category2);
		topic3.setUser(userUser);

		replies1.setTopic(topic1);
		replies2.setTopic(topic1);
		replies3.setTopic(topic3);

		replies1.setUser(userAdmin);
		replies2.setUser(userAdmin);
		replies3.setUser(userUser);

		repliesRepository.save(Arrays.asList(replies1,replies2,replies3));
		//non-used category
		categoryRepository.save(category3);
	}


	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

}