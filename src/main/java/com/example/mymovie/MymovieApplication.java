package com.example.mymovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MymovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MymovieApplication.class, args);
		System.out.println("Hello srpingboot");
	}

}
