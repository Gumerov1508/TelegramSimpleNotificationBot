package org.example.NotificationBot.repo;


import org.example.NotificationBot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
