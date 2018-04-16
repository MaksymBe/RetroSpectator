package com.dimax.retrospectator.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class ActionPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Retro retro;
}
