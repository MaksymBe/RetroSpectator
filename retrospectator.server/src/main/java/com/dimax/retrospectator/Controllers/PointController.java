package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("/point")
@CrossOrigin(origins = "*")
public class PointController {

    @Autowired
    PointService pointRepository;

    @PostMapping("/{identifier}")
    public ResponseEntity<Point> createPoint(@PathVariable String identifier,
                                            @RequestBody Point point,
                                            ServletRequest request) {

        User user = (User) request.getAttribute("user");
        Point createdPoint = pointRepository.createPoint(point, user, identifier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoint);

    }

    @GetMapping("/{identifier}/my")
    public ResponseEntity<List<Point>> getPointForUser(@PathVariable String identifier, ServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Point> points = pointRepository.getMyPointsForTeam(user, identifier);
        return ResponseEntity.ok(points);
    }

    @GetMapping("/{identifier}/all")
    public List<Point> getPointForTeam(@PathVariable String identifier) {
        List<Point> points = pointRepository.getAllPointsForTeam(identifier);
//        return ResponseEntity.status(HttpStatus.OK).body(points);
    return points;
    }


}
