package com.dimax.retrospectator.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table
@JsonIgnoreProperties({"retroes", "user", "retro", "uid"})
public class Team {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uid")
    private String uid;

    @Column(unique = true)
    private String identifier;

    @Column
    @NotNull
    private String title;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Retro> retroes = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "retro_id")
    private Retro retro;

    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = {@JoinColumn(name = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private Set<User> user = new HashSet<>();

    public Team() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
