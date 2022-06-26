package org.example.NotificationBot.DAO;

import org.example.NotificationBot.entity.Event;
import org.example.NotificationBot.entity.User;
import org.example.NotificationBot.repo.EventRepository;
import org.example.NotificationBot.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDAO {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

@Autowired
    public EventDAO(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<Event> findByUserId(long id){
        User user = userRepository.findById(id);
        return user.getEvents();
    }

    public List<Event> findAllEvent(){
    return eventRepository.findAll();
    }

    public Event findBbyEventId(long id){
    return eventRepository.findByEventId(id);
    }

    public void remove(Event event){
    eventRepository.delete(event);
    }

    public void save(Event event){
    eventRepository.save(event);
    }


}
