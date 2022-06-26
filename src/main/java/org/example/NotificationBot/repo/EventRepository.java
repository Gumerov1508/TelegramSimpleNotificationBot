package org.example.NotificationBot.repo;

import org.example.NotificationBot.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEventId(long id);
}
