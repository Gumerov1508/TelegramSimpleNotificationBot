package org.example.NotificationBot.service;

import lombok.Setter;
import org.example.NotificationBot.DAO.EventCashDAO;
import org.example.NotificationBot.config.ApplicationContextProvider;
import org.example.NotificationBot.model.TelegramBot;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Setter
public class SendEvent extends Thread {


    private long eventCashId;
    private SendMessage sendMessage;


    public SendEvent() {
    }


    @SneakyThrows
    @Override
    public void run() {
        TelegramBot telegramBot = ApplicationContextProvider.getApplicationContext().getBean(TelegramBot.class);
        EventCashDAO eventCashDAO = ApplicationContextProvider.getApplicationContext().getBean(EventCashDAO.class);
        telegramBot.execute(sendMessage);
        eventCashDAO.delete(eventCashId);
    }

    /*

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public void setEventCashId(long id) {
    }
    */
}
