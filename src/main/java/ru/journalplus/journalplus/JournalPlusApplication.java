package ru.journalplus.journalplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class JournalPlusApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JournalPlusApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8088"));
		app.run(args);
	}

}
