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
public class PromotionDTO implements Serializable {
    private String userID, fullName, role, photo;
    private int promotionValue;

    public PromotionDTO(String userID, String fullName, String role, String photo, int promotionValue) {
        this.userID = userID;
        this.fullName = fullName;
        this.role = role;
        this.photo = photo;
        this.promotionValue = promotionValue;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(int promotionValue) {
        this.promotionValue = promotionValue;
    }
    
    
    
}
