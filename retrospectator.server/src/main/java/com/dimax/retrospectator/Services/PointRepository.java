package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.Retro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findAllByRetro(Retro retro);
}

