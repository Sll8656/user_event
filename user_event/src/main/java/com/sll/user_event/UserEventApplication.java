package com.sll.user_event;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sll.user_event.mapper")
public class UserEventApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserEventApplication.class, args);
	}

}
