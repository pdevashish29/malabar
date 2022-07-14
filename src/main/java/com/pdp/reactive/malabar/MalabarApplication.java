package com.pdp.reactive.malabar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@SuppressWarnings("unused")
@SpringBootApplication
public class MalabarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MalabarApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	@Primary
	public WebClient getJsonPlaceHolderWebClient(){
		//172.31.40.38
		return  WebClient.create("https://jsonplaceholder.typicode.com/");
	}

	@Bean("GITHUB")
	@Scope("prototype")
	public WebClient getGithubWebClient(){
		System.out.println("Hello again");
		return WebClient.create("https://api.github.com");
	}


}
