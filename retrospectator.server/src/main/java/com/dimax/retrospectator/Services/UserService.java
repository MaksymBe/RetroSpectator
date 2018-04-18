package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUserById(Integer id){
        User user = repository.findById(id).get();
        return user;
    }
}
