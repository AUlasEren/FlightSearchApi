package com.ulas.repository;

import com.ulas.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndPassword(String userName, String password);
}
