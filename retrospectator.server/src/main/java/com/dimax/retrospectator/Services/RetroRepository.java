package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Retro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetroRepository extends JpaRepository<Retro, Integer> {
}
