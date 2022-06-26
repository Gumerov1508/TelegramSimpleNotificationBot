package org.example.NotificationBot.DAO;

import org.example.NotificationBot.entity.User;
import org.example.NotificationBot.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByUserId(long id){
        return userRepository.findById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void removeUser(User user){
        userRepository.delete(user);
    }

    public void save (User user){
        userRepository.save(user);
    }

    public boolean isExists(long id){
        User user = userRepository.findById(id);
        return user != null;
    }
}
