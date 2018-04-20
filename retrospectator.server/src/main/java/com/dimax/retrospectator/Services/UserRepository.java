package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsBySub(String sub);
    public User findBySub(String sub );

}
