package com.example.PAC_08;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	AuthorService authorService;
	
	@Autowired
	UserService userService;

	@Override
	public void run(String... args) throws Exception {

		logger.info("Welcome to the runner from commandLineRunner");

		
		User user1 = new User("1","Tom", "Jones");
		User user2 = new User("2", "Silvia", "Eriksson");
		User user3 = new User("3", "Anna", "Gates");
		User user4 = new User("4", "Paul", "Gates");
		
		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
		userService.save(user4);
		
		//private int id; public String name; public String country; public int dob; public int qtyBooks; public Boolean alive;
		Author author1 = new Author (0001, "Carlos Ruiz-Zafón", "Spain", 9-9-1964, 8, false);
		Author author2 = new Author (0002, "Arturo Perez-Reverte", "Span", 9-9-1964, 1, true);
		
		authorService.save(author1);
		authorService.save(author2);
		
		logger.info(authorService.findAll());
		
		
		logger.info("count: " + userService.count());
		logger.info(userService.findAll());
		logger.info("exists ['1']: " + userService.existsById("1"));
		
		
		userService.findAndDeleteById("1");
		
		logger.info("count: " + userService.count());
		logger.info(userService.findAll());
		logger.info("exists ['1']: " + userService.existsById("1"));
		
		userService.save(new User("5", "Paul", "Mendez"));
		

		logger.info("count: " + userService.count());
		logger.info(userService.findAll());
		logger.info("exists ['1']: " + userService.existsById("1"));
		logger.info("exists ['5']: " + userService.existsById("5"));
		
		user2.setFirstName("Olga");
		logger.info("count: " + userService.count());
		userService.update("2", user2);
		logger.info(userService.findAll());
		
		logger.info("count by lastname: " + userService.countByLastname("Gates"));

		author1.setUser(user2);
		author2.setUser(user2);
		//book3.setUser(user2);
		
		authorService.save(author1);
		authorService.save(author2);
		//bookService.save(book3);
		
		logger.info("toString author1: " + author1);
		logger.info("toString author1: " + author1.getName());
		logger.info("toString user2: " + user2);
		
		Author author3 = new Author (0003, "Miguel Angel", "Spain", 27-6-1964, 1, true);
		author3.setUser(user3);
		authorService.save(author3);
		
	}
}