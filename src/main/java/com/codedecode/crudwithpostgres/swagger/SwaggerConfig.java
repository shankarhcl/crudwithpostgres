package com.codedecode.crudwithpostgres.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-apis")
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.codedecode.crudwithpostgres")) //to remove base error controller on UI
				//.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class) //recommended to use to remove base error controller on UI instead of above line but not supporting in this project
				.build();
		
	}
	
	public ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Code Decode APIS").description("Apis created by code decode").version("1").build();

	}

}
