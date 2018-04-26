package com.dimax.retrospectator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@JsonIgnoreProperties({"team", "points", "sub"})
public class User {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToMany(mappedBy = "user" ,fetch = FetchType.EAGER)
    private Set<Team> team = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Point> points = new ArrayList<>();

    @Column
//            (unique = true)
    private String sub;

    @Column
    private String nickname;

    @Column
    private String name;

    @Column
    private String picUrl;

    public User() {
    }

    public User(AuthUser authUser) {
        this.sub = authUser.getSub();
        this.nickname = authUser.getNickname();
        this.name = authUser.getName();
        this.picUrl = authUser.getPicture();
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set <Team> team) {
        this.team = team;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
