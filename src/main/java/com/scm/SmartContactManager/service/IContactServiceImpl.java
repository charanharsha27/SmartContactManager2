package com.scm.SmartContactManager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.SmartContactManager.dao.IContactDao;
import com.scm.SmartContactManager.dao.IUserDao;
import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.helper.ResourceNotFoundException;

import lombok.var;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    }

    @Override
    public List<Contact> getAllContacts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllContacts'");
    }

    @Override
    public Optional<Contact> updateContact(Contact user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public void deleteContact(String ContactId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public void deleteContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
    }

    @Override
    public Page<Contact> getContacts(User user,int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageable = PageRequest.of(page, size);

        return contactDao.findByUser(user, pageable);
    }

}
