package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Column(length = 1,nullable = false)
    boolean admin;

//    I'm not sure a userName is even needed!
//    @Column(length = 60,nullable = false)
//    String userName;

    @OneToMany(orphanRemoval = true, mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference ("ticketsToUser")
    List<Ticket> tickets = new ArrayList<>();

    public User() {}

    public User(boolean admin) {
        this.admin = admin;
    }

}
