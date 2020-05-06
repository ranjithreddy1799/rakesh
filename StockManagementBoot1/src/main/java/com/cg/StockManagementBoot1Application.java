package com.cg;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cg.inventory.entity.User;
import com.cg.inventory.web.LoginRestController;

@SpringBootApplication
public class StockManagementBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(StockManagementBoot1Application.class, args);
	}
	
	@Autowired
	public LoginRestController loginInterceptor;
	
	@Bean(name="authenticatemap")
	public Map<String,User> getAuthenticateMap(){
		return new HashMap<>();
	}

}
