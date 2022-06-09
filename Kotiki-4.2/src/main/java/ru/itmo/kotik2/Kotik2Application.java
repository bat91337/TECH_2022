package ru.itmo.kotik2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.itmo.kotik2.Service.KotikiService;
import ru.itmo.kotik2.Service.OwnerService;
import ru.itmo.kotik2.Service.UserService;
import ru.itmo.kotik2.entitites.*;

@SpringBootApplication
public class Kotik2Application {

	public static void main(String[] args) {
		SpringApplication.run(Kotik2Application.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService, OwnerService ownerService, KotikiService kotikiService){
//		return args ->{
//			Users user = new Users("vasya", "12345", Role.USER);
//			userService.create(user);
//			Owner owner = new Owner(1L,"vasya", "2022-09-09", user);
//			ownerService.create(owner);
//			Users user1 = new Users("dima", "12345", Role.ADMIN);
//			userService.create(user1);
//			Owner owner1 = new Owner(2L,"dima", "2022-09-09", user1);
//			ownerService.create(owner1);
//			Users user2 = new Users("simon", "12345", Role.USER);
//			userService.create(user2);
//			Owner owner2 = new Owner(3L,"simon", "2022-09-09", user2);
//			ownerService.create(owner2);
//			Kotiki kotiki = new Kotiki(1L, "vasya", "2020-03-02", "l",owner, Colors.RED);
//			kotikiService.create(kotiki);
//			Kotiki kotiki1 = new Kotiki(2L, "petya", "2020-03-02", "l",owner, Colors.RED);
//			kotikiService.create(kotiki1);
//			Kotiki kotiki2 = new Kotiki(3L, "misha", "2020-03-02", "l",owner1, Colors.RED);
//			kotikiService.create(kotiki2);
//		};
//	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	@Bean
	public JsonDeserializer jsonDeserializer() {
		return new JsonDeserializer();
	}

}
