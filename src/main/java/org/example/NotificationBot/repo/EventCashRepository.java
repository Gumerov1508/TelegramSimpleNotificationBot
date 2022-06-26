package org.example.NotificationBot.repo;


import org.example.NotificationBot.entity.EventCashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCashRepository extends JpaRepository<EventCashEntity, Long> {
    EventCashEntity findById(long id);
}
