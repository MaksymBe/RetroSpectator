package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByIdentifierAndProvider(Long identifier, String provider );
    public User getByIdentifierAndProvider(Long identifier, String provider );
}
