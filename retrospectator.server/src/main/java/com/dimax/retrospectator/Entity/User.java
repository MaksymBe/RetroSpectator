package com.dimax.retrospectator.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;


    @ManyToMany(mappedBy = "user")
    private List<Team> team;

    @OneToMany
    private List <ActionPoint> actionPoints;

    @OneToMany
    private List <Point> points;

}
