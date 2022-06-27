package org.example.NotificationBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
