package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.AuthUser;
import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public User getUserById(Integer id) {
        User user = repository.findById(id).get();
        return user;
    }

    @Transactional
    public User createUser(AuthUser authUser) {

            User user = new User(authUser);
//        List<Point> points = new ArrayList<>();
//            user.setPoints(points);
        repository.save(user);
//        User user = new User(authUser);
//        entityManager.persist(user);

        return  user;

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
