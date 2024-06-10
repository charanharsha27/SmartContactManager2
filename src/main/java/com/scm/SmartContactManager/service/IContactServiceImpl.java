package com.scm.SmartContactManager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.SmartContactManager.dao.IContactDao;
import com.scm.SmartContactManager.dao.IUserDao;
import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helper.ResourceNotFoundException;

@Service
public class IContactServiceImpl implements IContactService{

    @Autowired
    private IContactDao contactDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public Contact addContact(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    public Optional<Contact> findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public Contact getContactByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContactByEmail'");
    }

    @Override
    public Optional<Contact> getContact(String ContactId) {
        Optional<Contact> contact = contactDao.findById(ContactId);
        if(contact.isEmpty()) {
            throw new ResourceNotFoundException("Contact not found with id: "+ContactId);
        }
        System.out.println("Contact found: "+contact.get());
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllContacts'");
    }

    @Override
    public Contact updateContact(Contact user) {
        return contactDao.save(user);
    }

    @Override
    public void deleteContact(String ContactId) {
        contactDao.deleteById(ContactId);  
    }

    

    @Override
    public Page<Contact> getContacts(User user,int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size);

        return contactDao.findByUser(user, pageable);
    }

    @Override
    public Page<Contact> getContactsByName(User user, String name, int page, int size, String sortBy,String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest of = PageRequest.of(page, size, sort);

        Page<Contact> contacts = contactDao.findByUserAndNameContaining(user, name, of);
        
        return contacts;
    }

    @Override
    public Page<Contact> getContactsByEmail(User user, String email, int page, int size, String sortBy,String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest of = PageRequest.of(page, size, sort);

        Page<Contact> contacts = contactDao.findByUserAndEmailContaining(user, email, of);
        
        return contacts;
    }

    @Override
    public Page<Contact> getContactsByPhone(User user, String phone, int page, int size, String sortBy,String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest of = PageRequest.of(page, size, sort);

        Page<Contact> contacts = contactDao.findByUserAndPhoneNumberContaining(user, phone, of);

        return contacts;
    }

}
