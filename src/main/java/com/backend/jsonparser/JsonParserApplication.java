package com.backend.jsonparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonParserApplication {

	public static void main(String[] args) {

		SpringApplication.run(JsonParserApplication.class, args);
		JsonParser js = new JsonParser();
		js.checkJsonElements();
	}

}
