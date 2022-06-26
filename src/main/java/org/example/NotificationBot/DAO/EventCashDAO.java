package org.example.NotificationBot.DAO;



import org.example.NotificationBot.entity.EventCashEntity;
import org.example.NotificationBot.repo.EventCashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
