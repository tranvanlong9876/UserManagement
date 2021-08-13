/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegisterDTO implements Serializable {
    private String email, phone, fullName, password, confirmPassword, username, photoLink;
    private int role;
    
    public RegisterDTO() {
    }

    public RegisterDTO(String email, String phone, String fullName, String password, String confirmPassword, String username, String photoLink, int role) {
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.photoLink = photoLink;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }     
}
