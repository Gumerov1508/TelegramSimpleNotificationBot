package org.example.NotificationBot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_zone", columnDefinition = "default 0")
    private int timeZone;

    @OneToOne(mappedBy="user")
    private List<Event> events;

    @Column(name = "on_off")
    // on/off send event
    private boolean on;

    public User() {
    }
}