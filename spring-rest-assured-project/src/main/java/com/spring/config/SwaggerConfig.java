package com.spring.config;

import java.util.Collections;

import org.reflections.adapters.MetadataAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2		
public class SwaggerConfig {

	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("com.spring.technician.controller"))              
				.paths(PathSelectors.regex("/service.*"))                          
				.build().apiInfo(apiInfo());                                           
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"My REST API", 
				"Some custom description of API.", 
				"API TOS", 
				"Terms of service", 
				new Contact("John Doe", "www.example.com", "myeaddress@company.com"), 
				"License of API", "API license URL", Collections.emptyList());
	}
}
