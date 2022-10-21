package com.exam;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user=new User();
//		user.setUsername("nik123");
//		user.setEmail("nk6511165@gmail.com");
//		user.setFirstName("Nitesh");
//		user.setPassword("%@$@%");
//		user.setLastName("Kumar");
//		user.setPhone("7378378777");
//		user.setProfile("default.png");
//
//		Role role=new Role();
//		role.setRoleId(445L);
//		role.setRoleName("ADMIN");
//
//		UserRole userRole=new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//
//		Set<UserRole> userRoleSet=new HashSet<>();
//		userRoleSet.add(userRole);
//		this.userService.createUser(user,userRoleSet);

	}
}
