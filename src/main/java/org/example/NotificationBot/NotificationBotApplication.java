package org.example.NotificationBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication // (scanBasePackages = {"org.example.NotificationBot.repo"} , exclude = JpaRepositoriesAutoConfiguration.class)
@ComponentScan({"org.example.NotificationBot.repo"})
public class NotificationBotApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotificationBotApplication.class, args);
	}

}
