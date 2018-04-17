package com.dimax.retrospectator.Entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Retro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private Date finishDate;

    @Column
    private String impression;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    @OneToMany(mappedBy = "retro")
    private Point point;

    @OneToMany(mappedBy = "retro")
    private ActionPoint actionPoint;


}
