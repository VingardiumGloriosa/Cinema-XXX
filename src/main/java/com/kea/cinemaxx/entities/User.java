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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference ("ticketsForUser")
    List<Ticket> tickets = new ArrayList<>();

    public User() {}

    public User(int userId) { this.userId = userId; }

    public User(boolean admin) {
        this.admin = admin;
    }

}
