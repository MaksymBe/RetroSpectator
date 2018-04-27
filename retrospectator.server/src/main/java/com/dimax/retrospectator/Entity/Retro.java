package com.dimax.retrospectator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@JsonIgnoreProperties({"team", "point", "actionPoint"})
public class Retro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Column
    private String impression;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "retro")
    @Nullable
    private List<Point> point = new ArrayList<>();

    @OneToMany(mappedBy = "retro")
    @Nullable
    private List<ActionPoint> actionPoint = new ArrayList<>();

    public Retro(Team team) {
        this.team = team;
    }

    public Retro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Point> getPoint() {
        return point;
    }

    public void setPoint(List<Point> point) {
        this.point = point;
    }

    public List<ActionPoint> getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(List<ActionPoint> actionPoint) {
        this.actionPoint = actionPoint;
    }
}
