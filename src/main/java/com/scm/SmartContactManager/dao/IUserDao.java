package com.scm.SmartContactManager.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.SmartContactManager.entities.User;

@Repository
public interface IUserDao extends JpaRepository<User,String>{
    
    Optional<User> findByEmail(String email);
}
