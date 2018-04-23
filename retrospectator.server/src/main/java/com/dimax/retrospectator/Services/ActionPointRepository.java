package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.ActionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionPointRepository extends JpaRepository<ActionPoint, Integer> {

}
