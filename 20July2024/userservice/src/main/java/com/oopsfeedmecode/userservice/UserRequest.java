package com.oopsfeedmecode.userservice;

import com.oopsfeedmecode.userservice.validationgroups.CreateGroup;
import com.oopsfeedmecode.userservice.validationgroups.UpdateGroup;
import com.oopsfeedmecode.userservice.validators.CustomEmail;
import com.oopsfeedmecode.userservice.validators.CustomPassword;
import com.oopsfeedmecode.userservice.validators.ValidEmailDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UserRequest {
    @NotNull(groups = {UpdateGroup.class})
    private Long id;
    @CustomEmail(groups = {CreateGroup.class, UpdateGroup.class})
    @NotNull(groups = {CreateGroup.class})
    private String email;

    //@CustomPassword(groups = {CreateGroup.class, UpdateGroup.class})
    //@NotNull(groups = {CreateGroup.class})
    private String password;
    @NotNull(groups = CreateGroup.class)
    private String firstName;
    @NotNull(groups = CreateGroup.class)
    private String lastName;
    @NotNull(groups = CreateGroup.class)
    private String phone;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
