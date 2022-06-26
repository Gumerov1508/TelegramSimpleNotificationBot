package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication // (scanBasePackages = {"org.example.NotificationBot.repo", "org.example.NotificationBot"} )
//@ComponentScan({"org.example.NotificationBot.repo"})
/*
@EnableJpaRepositories("org.example.NotificationBot.repo")
@EntityScan("org.example.NotificationBot.repo")
*/
public class NotificationBotApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotificationBotApplication.class, args);
    }

}
