package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetroRepository extends JpaRepository<Retro, Integer> {

}
