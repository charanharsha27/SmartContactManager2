package com.scm.SmartContactManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;

import java.util.List;


public interface IContactDao extends JpaRepository<Contact, String>{

    Page<Contact> findByUser(User user,Pageable pageable);

    @Query("Select c from Contact c where c.user.userId = :userid")
    List<Contact> findByUserId(@Param("userid") String userId);

}
