package com.scm.SmartContactManager.service;

import java.util.*;
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
    public List<Contact> getContacts(User user);
}
