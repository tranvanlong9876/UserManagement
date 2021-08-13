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
public class PromotionHistoryDTO implements Serializable {
    private String userAdded, userAssign, photoOfUserAdded, photoOfUserAssign, nameOfUserAdded, nameOfUserAssigned, timeOfAssign;
    private int promotionValue;

    public PromotionHistoryDTO() {
    }

    public PromotionHistoryDTO(String userAdded, String userAssign, String photoOfUserAdded, String photoOfUserAssign, String nameOfUserAdded, String nameOfUserAssigned, String timeOfAssign, int promotionValue) {
        this.userAdded = userAdded;
        this.userAssign = userAssign;
        this.photoOfUserAdded = photoOfUserAdded;
        this.photoOfUserAssign = photoOfUserAssign;
        this.nameOfUserAdded = nameOfUserAdded;
        this.nameOfUserAssigned = nameOfUserAssigned;
        this.timeOfAssign = timeOfAssign;
        this.promotionValue = promotionValue;
    }

    public String getPhotoOfUserAdded() {
        return photoOfUserAdded;
    }

    public void setPhotoOfUserAdded(String photoOfUserAdded) {
        this.photoOfUserAdded = photoOfUserAdded;
    }

    public String getPhotoOfUserAssign() {
        return photoOfUserAssign;
    }

    public void setPhotoOfUserAssign(String photoOfUserAssign) {
        this.photoOfUserAssign = photoOfUserAssign;
    }

    public String getNameOfUserAdded() {
        return nameOfUserAdded;
    }

    public void setNameOfUserAdded(String nameOfUserAdded) {
        this.nameOfUserAdded = nameOfUserAdded;
    }

    public String getNameOfUserAssigned() {
        return nameOfUserAssigned;
    }

    public void setNameOfUserAssigned(String nameOfUserAssigned) {
        this.nameOfUserAssigned = nameOfUserAssigned;
    }

    public String getUserAdded() {
        return userAdded;
    }

    public void setUserAdded(String userAdded) {
        this.userAdded = userAdded;
    }

    public String getUserAssign() {
        return userAssign;
    }

    public void setUserAssign(String userAssign) {
        this.userAssign = userAssign;
    }

    public String getTimeOfAssign() {
        return timeOfAssign;
    }

    public void setTimeOfAssign(String timeOfAssign) {
        this.timeOfAssign = timeOfAssign;
    }

    public int getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(int promotionValue) {
        this.promotionValue = promotionValue;
    }

    @Override
    public String toString() {
        return "PromotionHistoryDTO{" + "userAdded=" + userAdded + ", userAssign=" + userAssign + ", photoOfUserAdded=" + photoOfUserAdded + ", photoOfUserAssign=" + photoOfUserAssign + ", nameOfUserAdded=" + nameOfUserAdded + ", nameOfUserAssigned=" + nameOfUserAssigned + ", timeOfAssign=" + timeOfAssign + ", promotionValue=" + promotionValue + '}';
    }
    
   
}
