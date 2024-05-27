package com.scm.SmartContactManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.SmartContactManager.entities.Contact;

public interface IContactDao extends JpaRepository<Contact, String>{

}
