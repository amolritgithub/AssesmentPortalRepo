package com.example.SignUP;

import com.example.SignUP.entity.Role;
import com.example.SignUP.entity.User;
import com.example.SignUP.entity.UserRole;
import com.example.SignUP.helper.UserFoundException;
import com.example.SignUP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SignUpApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public static void main(String[] args) {

		SpringApplication.run(SignUpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Starting Code");
		try {

			User user= new User();
			user.setFirstName("Amol");
			user.setLastName("Rajput");
			user.setUsername("admin");
			user.setEmail("amolcjadhav680@email.com");
			user.setProfile("default.png");
			user.setPassword("root");

			Role role1= new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");

			Set<UserRole> set= new HashSet<>();
			UserRole userRole=new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);
			set.add(userRole);

			User user1=this.userService.creatUser(user,set);
			System.out.println(user1.getUsername());
		}catch (UserFoundException e){
			e.printStackTrace();
		}
	}
}
