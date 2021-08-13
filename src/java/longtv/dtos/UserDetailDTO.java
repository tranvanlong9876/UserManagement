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
public class UserDetailDTO implements Serializable {
    private String username, fullname, rolename, email, phone, photo;
    private int promotionValue;

    public UserDetailDTO() {
    }

    public UserDetailDTO(String username, String fullname, String rolename, String email, String phone, String photo) {
        this.username = username;
        this.fullname = fullname;
        this.rolename = rolename;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }

    public int getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(int promotionValue) {
        this.promotionValue = promotionValue;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
}
