package com.dimax.retrospectator.Controllers;


import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Services.RetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/retro")
@CrossOrigin(origins = "*")
public class RetroController {

    @Autowired
    RetroService retroRepository;


    @GetMapping("/{identifier}")
    public ResponseEntity<Retro> getCurrentRetro(@PathVariable String identifier){
        Retro retro = retroRepository.getCurrentRetro(identifier);
        return ResponseEntity.ok(retro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Retro> getRetroById(@PathVariable int id){
        Retro retro =retroRepository.getRetroById(id);
        return ResponseEntity.ok(retro);
    }

    @PatchMapping("/{identifier}/finish")
    public ResponseEntity<Retro> startNewRetro(@Valid @RequestBody String impression, @PathVariable String identifier) {
        Retro retro = retroRepository.startNewRetro(identifier, impression);
        return ResponseEntity.ok(retro);
    }

}
