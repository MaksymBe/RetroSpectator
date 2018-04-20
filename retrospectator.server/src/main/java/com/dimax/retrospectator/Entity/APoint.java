package com.dimax.retrospectator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
@JsonIgnoreProperties({"retro", "retro_id"})
public class APoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String title;

    @Column
    private Date date;

    @Column
    private short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "retro_id")
    private Retro retro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Retro getRetro_id() {
        return retro;
    }

    public void setRetro_id(Retro retro_id) {
        this.retro = retro;
    }
}
