package com.example.mymovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.example.mymovie"})
public class MymovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MymovieApplication.class, args);
		
		System.out.println("Hello! Welcome to MyMovieApplication");
	}

}
