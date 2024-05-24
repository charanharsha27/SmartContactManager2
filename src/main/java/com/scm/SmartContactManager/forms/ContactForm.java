package com.scm.SmartContactManager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {
    @NotBlank(message = "Contact name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 to 20 characters")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "About field is required")
    private String about;
    private String profilePic;
    @NotBlank(message = "Favourite field is required")
    private boolean favorite;
}
