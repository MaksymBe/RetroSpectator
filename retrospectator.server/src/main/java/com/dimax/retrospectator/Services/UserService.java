package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.AuthUser;
import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public User createUser(AuthUser authUser) {
        User user = new User(authUser);
        repository.save(user);
        return user;

    }

    public User findBySub(String sub) {
        return repository.findBySub(sub);
    }
}
