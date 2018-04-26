package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    RetroRepository retroRepository;

    @Autowired
    TeamRepository teamRepository;


    public Point createPoint(Point point, User user, String identifier) {
        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = team.getRetro();
        point.setUser(user);
        point.setRetro(retro);
        point.setDate(new Date());
        pointRepository.save(point);
        return point;
    }


    public List<Point> getMyPointsForTeam(User user, String identifier) {
        Team team = teamRepository.findByIdentifier(identifier);
        List<Point> points = team.getRetro().getPoint();
        List<Point> pointForUser = new ArrayList<>();
        for (Point point : points) {
            if (point.getUser().getId() == user.getId()) {
                pointForUser.add(point);
            }
        }
        return pointForUser;
    }


    public List<Point> getAllPointsForTeam(String identifier) {
        Team team = teamRepository.findByIdentifier(identifier);
        List<Point> points = team.getRetro().getPoint();
        return points;
    }

    public Point deletePointById(int id) {
        if (!pointRepository.existsById(id))
            return null;

        Point point = pointRepository.findById(id).get();
        pointRepository.deleteById(id);

        return point;
    }

    public Point updatePointById(Point point, int id) {
        if (!pointRepository.existsById(id)) {
            return null;
        }

        Point pointToUpdate = pointRepository.findById(id).get();
        pointToUpdate.setTitle(point.getTitle());
        pointRepository.save(pointToUpdate);

        return pointToUpdate;
    }

    public List<Point> getAllPointsByRetro(int id) {
        Retro retro = retroRepository.findById(id).get();
        List<Point> points = pointRepository.findAllByRetro(retro);
        return points;
    }
}
