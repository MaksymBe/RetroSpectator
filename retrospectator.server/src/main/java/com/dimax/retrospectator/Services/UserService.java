package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.AuthUser;
import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Transactional
    public User getUserById(Integer id) {
        User user = repository.findById(id).get();
        return user;
    }

    @Transactional
    public User getUser(AuthUser authUser) {
        User user = new User(authUser);
        return  repository.save(user);
    }

    @Transactional
    public boolean existsBySub(String sub){
        return repository.existsBySub(sub);
    }

    @Transactional
    public User getBySub(String sub){
        return repository.findBySub(sub);
    }
}
