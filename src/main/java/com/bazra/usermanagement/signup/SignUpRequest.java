package com.bazra.usermanagement.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.validation.constraints.*;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import com.bazra.usermanagement.model.Roles;

/**
 * 
 * @author Bemnet
 * @version 4/2022
 *
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class SignUpRequest {
    @NotBlank(message = "Enter your name")
//    @Size(min = 4, message = "Name must be at least 4 characters")
    private String firstname;

    @NotBlank(message = "Enter your father name")
//    @Size(min = 4, message = "Name must be at least 4 characters")
    private String lastname;

    @NotBlank(message = "Enter your password")
//	@ValidPassword(message = "Invalid Password")
    @NonNull
    private String password;

    @NotBlank(message = "Enter your phone")
    @Size(min = 4)
    private String username;

    @NotBlank(message = "Enter your email")
    @Column(unique = true)
    @Email
    private String email;
    private Roles roles;
    @NotBlank
    private String birthday;

    private String gender;

    @NotBlank
    private String password2;

    @NotBlank
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    
    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SignUpRequest [firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
                + ",  username=" + username + ", password2=" + password2 + "]";
    }

}
