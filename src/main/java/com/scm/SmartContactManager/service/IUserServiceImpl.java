package com.scm.SmartContactManager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.SmartContactManager.dao.IUserDao;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helper.ResourceNotFoundException;

@Service
public class IUserServiceImpl  implements IUserService{

    @Autowired
    private IUserDao userDao;

    private Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        logger.info("User data saved to database");
        return userDao.save(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User u = userDao.findById(user.getUserId()).orElseThrow( ()-> {
            throw new ResourceNotFoundException("User not found");
        });

        return Optional.ofNullable(userDao.save(u));
    }

    @Override
    public void deleteUser(String userId) {
        User u = userDao.findById(userId).orElseThrow( ()-> {
            throw new ResourceNotFoundException("User not found");
        });
        
        userDao.delete(u);
    }

    @Override
    public void deleteUser(User user) {
        User u = userDao.findById(user.getUserId()).orElseThrow( ()->{
            throw new ResourceNotFoundException("User not found");
        });
        userDao.delete(u);
    }

    @Override
    public Optional<User> getUser(String userId) {
        User u = userDao.findById(userId).orElseThrow( ()->{
            throw new ResourceNotFoundException("User not found");
        });

        return Optional.ofNullable(u);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User u =  userDao.findByEmail(email).orElseThrow( ()->{
            throw new ResourceNotFoundException("User not found");
        });

        return Optional.ofNullable(u);
    }

}
