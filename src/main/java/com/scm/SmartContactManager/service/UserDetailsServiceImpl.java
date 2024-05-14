package com.scm.SmartContactManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.scm.SmartContactManager.dao.IUserDao;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helper.ResourceNotFoundException;
import com.scm.SmartContactManager.security.userDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User u = userDao.findByEmail(username).orElseThrow( () -> {
            throw new ResourceNotFoundException("User not found with email: "+username+".");
        });

        return new userDetails(u);
    }

}
