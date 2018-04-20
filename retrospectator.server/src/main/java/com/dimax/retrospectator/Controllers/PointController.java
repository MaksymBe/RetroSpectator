package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Point createPoint(@PathVariable String identifier,
                             @RequestBody Point point,
                             ServletRequest request)
    {
        User user = (User) request.getAttribute("user");
        return  pointRepository.createPoint(point, user, identifier);

    }

    @GetMapping("/{identifier}/my")
    public List<Point> getPointForUser(@PathVariable String identifier, ServletRequest request){
        User user = (User)request.getAttribute("user");
        return pointRepository.getMyPointsForTeam(user, identifier);
    }

    @GetMapping("/{identifier}/all")
    public List<Point> getPointForTeam(@PathVariable String identifier){
        return pointRepository.getAllPointsForTeam(identifier);
    }

}
