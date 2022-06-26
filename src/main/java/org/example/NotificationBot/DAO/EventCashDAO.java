package org.example.NotificationBot.DAO;

import org.example.NotificationBot.entity.Event;
import org.example.NotificationBot.entity.EventCashEntity;
import org.example.NotificationBot.entity.User;
import org.example.NotificationBot.repo.EventCashRepository;
import org.example.NotificationBot.repo.EventRepository;
import org.example.NotificationBot.repo.UserRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//handles events not dispatched after reboot heroku
public class EventCashDAO {

    private EventCashRepository eventCashRepository;

    @Autowired
    public void setEventCashRepository(EventCashRepository eventCashRepository) {
        this.eventCashRepository = eventCashRepository;
    }

    public List<EventCashEntity> findAllEventCash() {
        return eventCashRepository.findAll();
    }

    public void save(EventCashEntity eventCashEntity) {
        eventCashRepository.save(eventCashEntity);
    }

    public void delete(long id) {
        eventCashRepository.deleteById(id);
    }
}
