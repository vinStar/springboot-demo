package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by vin on 13/01/2018.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {


	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("moduleA")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.springboot.controller"))
				.paths(PathSelectors.any())
				.build();

	}


	@Bean
	public Docket createRestApi2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("moduleB")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.springboot.module"))
				.paths(PathSelectors.any())
				.build();

	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("谁来决定你的数据返回类型json|xml?")
				.description("关注：http://xxx/")
				.termsOfServiceUrl("http://xxx/")
				.contact("vin")
				.version("1.0")
				.build();
	}

}

