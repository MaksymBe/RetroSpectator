package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/point")
@CrossOrigin(origins = "*")
public class PointController {

    @Autowired
    PointService pointService;

    @PostMapping("/{identifier}")
    public Point createPoint(@PathVariable String identifier,
                             @Valid @RequestBody Point point,
                                            ServletRequest request) {

        User user = (User) request.getAttribute("user");
        Point createdPoint = pointService.createPoint(point, user, identifier);
        return createdPoint;

    }

    @GetMapping("/{identifier}/my")
    public List<Point> getPointForUser(@PathVariable String identifier, ServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Point> points = pointService.getMyPointsForTeam(user, identifier);
        if(points == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        return points;
    }

    @GetMapping("/{identifier}/all")
    public List<Point> getPointForTeam(@PathVariable String identifier) {
        List<Point> points = pointService.getAllPointsForTeam(identifier);
//        return ResponseEntity.status(HttpStatus.OK).body(points);
    return points;
    }

    @DeleteMapping("/{pointId}")
    public ResponseEntity<Point> deletePointById(@PathVariable int pointId) {
        Point point = pointService.deletePointById(pointId);
        if(point == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(point);
    }

    @PatchMapping("/{pointId}")
    public ResponseEntity<Point> updatePointById(@Valid @RequestBody Point point, @PathVariable int pointId) {

        Point updatedPoint = pointService.updatePointById(point, pointId);

        if(updatedPoint == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedPoint);
    }

    @GetMapping("/retro/{retroId}")
    public ResponseEntity<List<Point>> getPointsByRetroId(@PathVariable int retroId) {
        List<Point> points = pointService.getAllPointsByRetro(retroId);
        return ResponseEntity.ok().body(points);
    }
}
