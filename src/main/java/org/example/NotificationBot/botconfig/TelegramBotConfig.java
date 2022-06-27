package org.example.NotificationBot.botconfig;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
//@Getter
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class TelegramBotConfig {


    @Value("${telegramBot.webHookPath}")
    String webHookPath;
    @Value("${telegramBot.userName}")
    String userName;
    @Value("${telegramBot.botToken}")
    String botToken;


    public String getWebHookPath() {
        return webHookPath;
    }

    public String getUserName() {
        return userName;
    }

    public String getBotToken() {
        return botToken;
    }

}
