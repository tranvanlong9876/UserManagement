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
public class AccountErrorObject implements Serializable {
    private String errorServlet, errorDetail;
    private String invalidEmail, invalidPhone, invalidPassword, invalidOldPassword, invalidConfirmPassword, invalidFullname, invalidUsername, invalidPhoto;  
    private boolean validateStatus;

    public AccountErrorObject() {
    }

    public String getErrorServlet() {
        return errorServlet;
    }

    public void setErrorServlet(String errorServlet) {
        this.errorServlet = errorServlet;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public boolean isValidateStatus() {
        return validateStatus;
    }

    public void setValidateStatus(boolean validateStatus) {
        this.validateStatus = validateStatus;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }

    public void setInvalidEmail(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidPhone() {
        return invalidPhone;
    }

    public void setInvalidPhone(String invalidPhone) {
        this.invalidPhone = invalidPhone;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }

    public void setInvalidPassword(String invalidPassword) {
        this.invalidPassword = invalidPassword;
    }

    public String getInvalidConfirmPassword() {
        return invalidConfirmPassword;
    }

    public void setInvalidConfirmPassword(String invalidConfirmPassword) {
        this.invalidConfirmPassword = invalidConfirmPassword;
    }

    public String getInvalidFullname() {
        return invalidFullname;
    }

    public void setInvalidFullname(String invalidFullname) {
        this.invalidFullname = invalidFullname;
    }

    public String getInvalidUsername() {
        return invalidUsername;
    }

    public void setInvalidUsername(String invalidUsername) {
        this.invalidUsername = invalidUsername;
    }

    public String getInvalidPhoto() {
        return invalidPhoto;
    }

    public void setInvalidPhoto(String invalidPhoto) {
        this.invalidPhoto = invalidPhoto;
    }

    public String getInvalidOldPassword() {
        return invalidOldPassword;
    }

    public void setInvalidOldPassword(String invalidOldPassword) {
        this.invalidOldPassword = invalidOldPassword;
    }
    
    
    
}
