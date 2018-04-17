package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    public Team findByLink(String link);
}
