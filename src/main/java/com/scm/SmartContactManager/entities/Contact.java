package com.scm.SmartContactManager.entities;

import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    @Id
    private String contactId;
    
    private String name;
    private String email;
    private String phoneNumber;
    @Column(length = 1000)
    private String about;
    private String profilePic;
    private boolean favourite = false;
    private String cloudinaryContactId;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialLinks> socaialLinks = new ArrayList<>();

    
}
