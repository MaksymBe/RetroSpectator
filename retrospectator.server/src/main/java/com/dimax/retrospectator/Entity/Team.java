package com.dimax.retrospectator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@JsonView(TeamMaker.Team.class)
@JsonIgnoreProperties({"retroes"})
public class Team {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    private Integer id;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uid" )
    private String uid;


    @Column
    private String identifier;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Retro> retroes = new ArrayList<>();

    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="retro_id")
    private Retro retro;

    @ManyToMany(mappedBy = "team" ,fetch = FetchType.EAGER)
    private List<User> user = new ArrayList<>();

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Retro> getRetroes() {
        return retroes;
    }

    public void setRetroes(List<Retro> retroes) {
        this.retroes = retroes;
    }

    public Retro getRetro() {
        return retro;
    }

    public void setRetro(Retro retro) {
        this.retro = retro;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
