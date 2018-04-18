package com.dimax.retrospectator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetroService {

    @Autowired
    RetroRepository repository;
}
