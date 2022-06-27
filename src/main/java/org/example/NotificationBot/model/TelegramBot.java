package org.example.NotificationBot.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;





@Getter
@Setter
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class TelegramBot extends SpringWebhookBot {


   // @Value("${telegrambot.webHookPath}")
    String botPath;
   // @Value("${telegrambot.userName}")
    String botUsername;
   // @Value("${telegrambot.botToken}")
    String botToken;

    private TelegramFacade telegramFacade;

    public TelegramBot(TelegramFacade telegramFacade, DefaultBotOptions defaultBotOptions, SetWebhook setWebhook){
        super(defaultBotOptions, setWebhook);
        this.telegramFacade = telegramFacade;
    }
    public TelegramBot(TelegramFacade telegramFacade, SetWebhook setWebhook){
        super(setWebhook);
        this.telegramFacade = telegramFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.handleUpdate(update);
    }
    public String getBotPath() {
        return botPath;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
