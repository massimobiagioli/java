package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
	
	@Bean
	DummyBean dummyBean() {
		DummyBean bean = new DummyBean();
		bean.setMessage("This is a Dummy Bean!");
		return bean;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}