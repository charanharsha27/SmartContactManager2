package com.scm.SmartContactManager.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class UserForm {
    @NotBlank(message = "Name field is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 to 20 characters")
    private String name;
    @Email(message = "Invalid email")
    @NotBlank(message = "Email field is required")
    private String email;
    @NotBlank(message = "Password field is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Meet all the below specified password criteria")
    private String password;
    @NotBlank(message = "About field is required")
    private String about;
}
