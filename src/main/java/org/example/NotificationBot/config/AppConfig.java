package org.example.NotificationBot.config;

import org.example.NotificationBot.botconfig.TelegramBotConfig;
import org.example.NotificationBot.model.TelegramBot;
import org.example.NotificationBot.model.TelegramFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class AppConfig {
    private final TelegramBotConfig botConfig;

    public AppConfig(TelegramBotConfig config){
        this.botConfig = config;
    }
    @Bean
    public SetWebhook setWebhookInstance(){
        return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public TelegramBot springWebHookBot(SetWebhook setWebhook, TelegramFacade telegramFacade)
    {
        TelegramBot bot = new TelegramBot(telegramFacade, setWebhook);
        bot.setBotToken(botConfig.getBotToken());
        bot.setBotUsername(botConfig.getUserName());
        bot.setBotPath(botConfig.getWebHookPath());

        return bot;
    }
}
