package com.ativoeoperante;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AtivoEOperanteApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AtivoEOperanteApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}

}
