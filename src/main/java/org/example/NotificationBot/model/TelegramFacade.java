package org.example.NotificationBot.model;

import com.example.NotificationBot.cash.BotStateCash;
import com.example.NotificationBot.model.handler.CallbackQueryHandler;
import com.example.NotificationBot.model.handler.MessageHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramFacade {

    final com.example.NotificationBot.model.handler.MessageHandler messageHandler;
    final CallbackQueryHandler callbackQueryHandler;
    final BotStateCash botStateCash;


    int adminId;

    public TelegramFacade(MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler, BotStateCash botStateCash) {
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.botStateCash = botStateCash;
    }


    public BotApiMethod<?> handleUpdate(Update update){
        if(update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackQueryHandler.processCallbackQuery(callbackQuery);
        } else {
            Message message = update.getMessage();
            if(message != null && message.hasText()){
                return handleInputMessage(message);
            }
        }
        return null;
    }

    private BotApiMethod<?> handleInputMessage(Message message){
        BotState botState;
        String inputMsg = message.getText();

        switch (inputMsg) {
            case "/start":
                botState = BotState.START;
                break;
            case "Мои напоминания":
                botState = BotState.MYEVENTS;
                break;
            case "Создать напоминание":
                botState = BotState.CREATE;
                break;
            case "Отключить напоминания":
            case "Включить напоминания":
                botState = BotState.ONEVENT;
                break;
            case "All users":
                if (message.getFrom().getId() == adminId)
                    botState = BotState.ALLUSERS;
                else botState = BotState.START;
                break;
            case "All events":
                if (message.getFrom().getId() == adminId)
                    botState = BotState.ALLEVENTS;
                else botState = BotState.START;
                break;
            default:
                botState = botStateCash.getBotStateMap().get(message.getFrom().getId()) == null ?
                        BotState.START : botStateCash.getBotStateMap().get(message.getFrom().getId());
        }
        return messageHandler.handle(message, botState);
    }
}
