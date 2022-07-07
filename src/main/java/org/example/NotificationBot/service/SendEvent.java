package org.example.NotificationBot.service;

import lombok.Setter;
import org.example.NotificationBot.DAO.EventCashDAO;
import org.example.NotificationBot.DAO.EventDAO;
import org.example.NotificationBot.config.ApplicationContextProvider;
import org.example.NotificationBot.entity.Event;
import org.example.NotificationBot.model.TelegramBot;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
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
        // Event event = ApplicationContextProvider.getApplicationContext().getBean(Event.class);
        // EventDAO eventDAO = ApplicationContextProvider.getApplicationContext().getBean(EventDAO.class);
        telegramBot.execute(sendMessage);
        eventCashDAO.delete(eventCashId);
        /*
        if(event.getFreq().toString() == "TIME")
        {
            eventDAO.remove(event);
        }
        */

    }

    /*

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public void setEventCashId(long id) {
    }
    */
}
