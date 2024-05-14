package com.scm.SmartContactManager.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    // user details
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private String userId;
    @Column(nullable = false)
    private String name;
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length=10000)
    private String profilePic;
    private String phoneNumber;

    // verification details
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // login via details

    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();


}
