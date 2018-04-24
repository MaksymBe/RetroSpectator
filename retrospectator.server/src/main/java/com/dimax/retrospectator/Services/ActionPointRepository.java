package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.ActionPoint;
import com.dimax.retrospectator.Entity.Retro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionPointRepository extends JpaRepository<ActionPoint, Integer> {
    List<ActionPoint> findAllByRetro(Retro retro);
}
