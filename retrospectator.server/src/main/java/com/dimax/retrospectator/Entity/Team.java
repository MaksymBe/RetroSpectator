package com.dimax.retrospectator.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column
    private String link_uid;


    @ManyToMany(mappedBy = "team")
    private List<User> user;

    @OneToMany(mappedBy = "team")
    private List <Retro> retroes;

    @OneToOne
    private Retro retro;
}
