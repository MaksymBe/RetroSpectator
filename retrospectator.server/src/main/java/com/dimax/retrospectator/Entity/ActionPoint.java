package com.dimax.retrospectator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@JsonIgnoreProperties({"retro", "retro_id"})
public class ActionPoint {
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

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
    }

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

    public Retro getRetro() {
        return retro;
    }

    public void setRetro(Retro retro) {
        this.retro = retro;
    }
}
