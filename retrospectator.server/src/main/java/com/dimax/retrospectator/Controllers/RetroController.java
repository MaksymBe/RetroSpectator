package com.dimax.retrospectator.Controllers;


import com.dimax.retrospectator.Services.RetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
public class RetroController {

    @Autowired
    RetroService service;







}
