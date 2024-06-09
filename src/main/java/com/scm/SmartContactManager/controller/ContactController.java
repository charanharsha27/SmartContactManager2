package com.scm.SmartContactManager.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.SmartContactManager.entities.Contact;
import com.scm.SmartContactManager.entities.User;
import com.scm.SmartContactManager.forms.ContactForm;
import com.scm.SmartContactManager.forms.searchContactsForm;
import com.scm.SmartContactManager.helper.GetLoggedInUserName;
import com.scm.SmartContactManager.helper.MessageHelper;
import com.scm.SmartContactManager.service.IContactService;
import com.scm.SmartContactManager.service.IUserService;
import com.scm.SmartContactManager.service.ImageService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IContactService contactService;


    @GetMapping("/add")
    public String addContact(Model model){
        model.addAttribute("contactForm",new ContactForm());
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String processContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult rBindingResult,Authentication authentication,HttpSession session){
        
        if(rBindingResult.hasErrors()){
            session.setAttribute("message",new MessageHelper("Fill all the fields correctly","danger"));
            return "user/add_contact";
        }

        logger.info("contact Image: "+contactForm.getProfilePic().getOriginalFilename());

        String fileName = UUID.randomUUID().toString();
        logger.info("File Name : "+fileName);
        String contactImageUrl = imageService.getImageUrl(contactForm.getProfilePic(),fileName);
        logger.info("Contact Image URL : "+contactImageUrl);
        String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);

        logger.info("Contact Form : "+contactForm);
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setAbout(contactForm.getAbout());
        contact.setFavourite(contactForm.isFavorite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setContactId(UUID.randomUUID().toString());
        contact.setProfilePic(contactImageUrl);
        contact.setCloudinaryContactId(fileName);
        contact.setUser(user);

        Contact c = contactService.addContact(contact);
        logger.info("Contact added : "+c);
        session.setAttribute("message",new MessageHelper("Contact added to DB.!","success"));
        return "user/add_contact";
    }

    @GetMapping("/view")
    public String getContacts(Authentication authentication){
        String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);
        // List<Contact> contacts = contactService.getContacts(user);
        logger.info("Contacts : "+user.getContact());
        return "user/dashboard";
    }

    @GetMapping("/view-contacts")
    public String viewContacts(Authentication authentication,Map<String,Object> map,
                                @RequestParam(value = "page",defaultValue = "0") int page,
                                @RequestParam(value = "size",defaultValue = "1") int size,
                                @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
                                @RequestParam(value = "direction",defaultValue = "asc") String direction){
                                    
        String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);
        Page<Contact> contacts = contactService.getContacts(user,page,size,sortBy,direction);
        map.put("contacts", contacts);
        map.put("currPage",page);
        map.put("totalPages",contacts.getTotalPages());
        map.put("searchForm",new searchContactsForm());
        return "user/view_contacts";
    }

    @GetMapping("/search-contacts")
    public String searchContacts(@ModelAttribute("searchContactsForm") searchContactsForm search,Map<String,Object> map,Authentication authentication
                                ,@RequestParam(value = "page",defaultValue = "0") int page,
                                @RequestParam(value = "size",defaultValue = "1") int size,
                                @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
                                @RequestParam(value = "direction",defaultValue = "asc") String direction){
        String email = GetLoggedInUserName.getLoggedInUserEmail(authentication);
        User user = userService.getUserByEmail(email);
        if(search.getSearchValue().isEmpty() || search.getSearchValue().isBlank()){
            Page<Contact> contacts = contactService.getContacts(user,page,size,sortBy,direction);
            map.put("contacts", contacts);
            map.put("currPage",page);
            map.put("totalPages",contacts.getTotalPages());
            map.put("searchForm",search);
            return "user/view_contacts";
        }
        
        System.out.println("inside search controller");
        if(search.getSearchType().equalsIgnoreCase("name")){
            Page<Contact> contacts = contactService.getContactsByName(user,search.getSearchValue(),page,size,sortBy,direction);
            map.put("contacts", contacts);
            map.put("currPage",page);
            map.put("totalPages",contacts.getTotalPages());
        }else if(search.getSearchType().equalsIgnoreCase("email")){
            Page<Contact> contacts = contactService.getContactsByEmail(user,search.getSearchValue(),page,size,sortBy,direction);
            map.put("contacts", contacts);
            map.put("currPage",page);
            map.put("totalPages",contacts.getTotalPages());
        }
        else if(search.getSearchType().equalsIgnoreCase("phone")){
            Page<Contact> contacts = contactService.getContactsByPhone(user,search.getSearchValue(),page,size,sortBy,direction);
            map.put("contacts", contacts);
            map.put("currPage",page);
            map.put("totalPages",contacts.getTotalPages());
        }
        else{
            Page<Contact> contacts = contactService.getContacts(user,page,size,sortBy,direction);
            map.put("contacts", contacts);
            map.put("currPage",page);
            map.put("totalPages",contacts.getTotalPages());
        }
        map.put("searchForm",search);
        return "user/view_contacts";
    }
}
