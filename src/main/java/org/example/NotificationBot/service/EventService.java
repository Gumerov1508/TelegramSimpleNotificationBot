package org.example.NotificationBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.example.NotificationBot.DAO.EventCashDAO;
import org.example.NotificationBot.DAO.EventDAO;
import org.example.NotificationBot.entity.Event;
import org.example.NotificationBot.entity.EventCashEntity;
import org.example.NotificationBot.model.EventFreq;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;


@EnableScheduling
@Service
public class EventService {
    private final EventDAO eventDAO;
    private final EventCashDAO eventCashDAO;

    @Autowired
    public EventService(EventDAO eventDAO, EventCashDAO eventCashDAO) {
        this.eventDAO = eventDAO;
        this.eventCashDAO = eventCashDAO;
    }


    @Scheduled(cron = "0 15 22 * * *" , zone="Asia/Yekaterinburg")
    private void eventService() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        //get event list is now date
        List<Event> list = eventDAO.findAllEvent().stream().filter(event -> {
            if (event.getUser().isOn()) {
                EventFreq eventFreq = event.getFreq();

                //set user event time
                Calendar calendarUserTime = getDateUserTimeZone(event);

                int day1 = calendarUserTime.get(Calendar.DAY_OF_MONTH);
                int month1 = calendarUserTime.get(Calendar.MONTH);
                int year1 = calendarUserTime.get(Calendar.YEAR);
                switch (eventFreq.name()) {
                    case "TIME": //if one time - remove event
                        if (day == day1 && month == month1 && year == year1) {
                            // eventDAO.remove(event);
                            return true;
                        }else
                            return false;


                    case "EVERYDAY":
                        return true;
                    case "MONTH":
                        return day == day1;
                    case "YEAR":
                        return day == day1 && month == month1;
                    default: return false;
                }
            } else return false;
        }).collect(Collectors.toList());

        for (Event event : list) {
            //set user event time
            Calendar calendarUserTime = getDateUserTimeZone(event);
            int hour1 = calendarUserTime.get(Calendar.HOUR_OF_DAY);
            int minute1 = calendarUserTime.get(Calendar.MINUTE);
            calendarUserTime.set(year, month, day, hour1, minute1, 0);

            String description = event.getDescription();
            String userId = String.valueOf(event.getUser().getId());

            //save the event to the database in case the server reboots.
            EventCashEntity eventCashEntity = EventCashEntity.eventTo(calendarUserTime.getTime(), event.getDescription(), event.getUser().getId());
            eventCashDAO.save(eventCashEntity);

            //create a thread for the upcoming event with the launch at a specific time
            SendEvent sendEvent = new SendEvent();
            sendEvent.setSendMessage(new SendMessage(userId, description));
            sendEvent.setEventCashId(eventCashEntity.getId());
            new Timer().schedule(new SimpleTask(sendEvent), calendarUserTime.getTime());
        }
    }

    private Calendar getDateUserTimeZone(Event event) {
        Calendar calendarUserTime = Calendar.getInstance();
        calendarUserTime.setTime(event.getDate());
        int timeZone = event.getUser().getTimeZone();

        //set correct event time with user timezone
        calendarUserTime.add(Calendar.HOUR_OF_DAY, -timeZone);
        return calendarUserTime;
    }
}