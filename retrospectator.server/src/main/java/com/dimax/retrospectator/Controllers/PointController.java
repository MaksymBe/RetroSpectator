package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Services.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public class PointController {
    @Autowired
    PointService service;
}
