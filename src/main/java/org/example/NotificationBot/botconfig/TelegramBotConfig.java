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


    //@Value("${telegramBot.webHookPath}")
    String webHookPath = "https://1c16-178-214-248-183.eu.ngrok.io";
    //@Value("${telegramBot.userName}")
    String userName  = "myNewTestSmakayevoIshimbayUfaBOt";
   // @Value("${telegramBot.botToken}")
    String botToken  = "5524533853:AAHfk0nylA_PnIASkfhwLKqq0XJhU_QoiiQ";


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
