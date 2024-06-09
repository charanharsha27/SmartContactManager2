package com.scm.SmartContactManager.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;

public interface IContactService {

    //methods to perform crud operations with contact dao
    public Contact addContact(Contact contact);
    public Optional<Contact> findByEmail(String email);
    public Contact getContactByEmail(String email);
    public Optional<Contact> getContact(String ContactId);
    public List<Contact> getAllContacts();
    public Optional<Contact> updateContact(Contact user);
    public void deleteContact(String ContactId);
    public void deleteContact(Contact contact);
    public Page<Contact> getContacts(User user,int page, int size,String sortBy, String direction);

    Page<Contact> getContactsByName(User user, String name, int page, int size, String sortBy, String direction);
    Page<Contact> getContactsByEmail(User user, String email, int page, int size, String sortBy, String direction);
    Page<Contact> getContactsByPhone(User user, String phone, int page, int size, String sortBy, String direction);
}
