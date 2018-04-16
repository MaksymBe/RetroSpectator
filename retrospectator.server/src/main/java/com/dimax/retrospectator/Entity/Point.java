package com.dimax.retrospectator.Entity;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String type;

    @Column
    private Date date;



    @ManyToOne
    private Retro retro;

}
