/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.util;

import java.io.Serializable;
import longtv.daos.AccountDAO;
import longtv.dtos.AccountErrorObject;
import longtv.dtos.RegisterDTO;
import longtv.dtos.UpdateDTO;

/**
 *
 * @author Admin
 */
public class ValidateInput implements Serializable {

    public static boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z]+[a-zA-Z0-9\\-_]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$");
    }

    public static boolean validatePhone(String phone) {
        return phone.matches("[0-9]{5,20}");
    }

    public static boolean validateUsername(String username) {
        return (username.length() <= 50 && username.length() > 6);
    }

    public static boolean validatePassword(String password) {
        return (password.length() <= 50 && password.length() > 6);
    }

    public static boolean validateOldPassword(String password) {
        return (password.length() <= 50 && password.length() > 6);
    }

    public static boolean validateMatchingPassword(String password, String confirm) {
        return password.equals(confirm);
    }

    public static boolean validateFullName(String fullName) {
        return (fullName.length() >= 2 && fullName.length() <= 50);
    }

    public static boolean validatePhoto(String photo) {
        return photo.length() >= 3;
    }

    public static boolean validateExistEmail(String email, String username) throws Exception {
        AccountDAO checkAccount = new AccountDAO();
        return checkAccount.checkExistEmail(email, username);
    }

    public static boolean validateExistPhone(String phone, String username) throws Exception {
        AccountDAO checkAccount = new AccountDAO();
        return checkAccount.checkExistPhone(phone, username);
    }

    public AccountErrorObject statusValidate(RegisterDTO dto) throws Exception {
        AccountErrorObject errorObject = new AccountErrorObject();
        boolean valid = true;
        if (!validateEmail(dto.getEmail())) {
            errorObject.setInvalidEmail("Your email is invalid");
            valid = false;
        }

        if (!validatePassword(dto.getPassword())) {
            errorObject.setInvalidPassword("Your password is invalid");
            valid = false;
        }
        if (!validateMatchingPassword(dto.getPassword(), dto.getConfirmPassword())) {
            errorObject.setInvalidConfirmPassword("Password must match confirm password");
            valid = false;
        }
        if (!validatePhone(dto.getPhone())) {
            errorObject.setInvalidPhone("Your phone is invalid");
            valid = false;
        }
        if (!validateUsername(dto.getUsername())) {
            errorObject.setInvalidUsername("Your username is invalid");
            valid = false;
        }

        if (!validateFullName(dto.getFullName())) {
            errorObject.setInvalidFullname("Name too short or too long");
            valid = false;
        }

        if (!validatePhoto(dto.getPhotoLink())) {
            errorObject.setInvalidPhoto("You must choose a photo!");
            valid = false;
        }

        if (validateExistEmail(dto.getEmail(), dto.getUsername())) {
            errorObject.setInvalidEmail("This email is already exist!");
            valid = false;
        }

        if (validateExistPhone(dto.getPhone(), dto.getUsername())) {
            errorObject.setInvalidPhone("This phone is already exist!");
            valid = false;
        }

        errorObject.setValidateStatus(valid);
        return errorObject;
    }

    public AccountErrorObject updateValidateWithPassword(UpdateDTO dto, boolean isYourself) throws Exception {
        AccountErrorObject errorObject = new AccountErrorObject();
        boolean valid = true;
        if (!validateEmail(dto.getEmail())) {
            errorObject.setInvalidEmail("Your email is invalid");
            valid = false;
        }

        if (isYourself) {
            if (!validateOldPassword(dto.getOldPassword())) {
                errorObject.setInvalidOldPassword("Your old password is invalid");
                valid = false;
            }
        }

        if (!validatePassword(dto.getNewPassword())) {
            errorObject.setInvalidPassword("Your password is invalid");
            valid = false;
        }
        if (!validateMatchingPassword(dto.getNewPassword(), dto.getConfirmPassword())) {
            errorObject.setInvalidConfirmPassword("Password must match confirm password");
            valid = false;
        }
        if (!validatePhone(dto.getPhone())) {
            errorObject.setInvalidPhone("Your phone is invalid");
            valid = false;
        }
        if (!validateUsername(dto.getUsername())) {
            errorObject.setInvalidUsername("Your username is invalid");
            valid = false;
        }

        if (!validateFullName(dto.getFullName())) {
            errorObject.setInvalidFullname("Name too short or too long");
            valid = false;
        }

        if (!validatePhoto(dto.getPhotoLink())) {
            errorObject.setInvalidPhoto("You must choose a photo!");
            valid = false;
        }

        if (validateExistEmail(dto.getEmail(), dto.getUsername())) {
            errorObject.setInvalidEmail("This email is already exist!");
            valid = false;
        }

        if (validateExistPhone(dto.getPhone(), dto.getUsername())) {
            errorObject.setInvalidPhone("This phone is already exist!");
            valid = false;
        }

        errorObject.setValidateStatus(valid);
        return errorObject;
    }

    public AccountErrorObject updateValidateWithoutPassword(UpdateDTO dto, boolean isYourself) throws Exception {
        AccountErrorObject errorObject = new AccountErrorObject();
        boolean valid = true;

        if (isYourself) {
            if (!validateOldPassword(dto.getOldPassword())) {
                errorObject.setInvalidOldPassword("Your old password is invalid");
                valid = false;
            }
        }
        
        if (!validateEmail(dto.getEmail())) {
            errorObject.setInvalidEmail("Your email is invalid");
            valid = false;
        }

        if (!validatePhone(dto.getPhone())) {
            errorObject.setInvalidPhone("Your phone is invalid");
            valid = false;
        }
        if (!validateUsername(dto.getUsername())) {
            errorObject.setInvalidUsername("Your username is invalid");
            valid = false;
        }

        if (!validateFullName(dto.getFullName())) {
            errorObject.setInvalidFullname("Name too short or too long");
            valid = false;
        }

        if (!validatePhoto(dto.getPhotoLink())) {
            errorObject.setInvalidPhoto("You must choose a photo!");
            valid = false;
        }

        if (validateExistEmail(dto.getEmail(), dto.getUsername())) {
            errorObject.setInvalidEmail("This email is already exist!");
            valid = false;
        }

        if (validateExistPhone(dto.getPhone(), dto.getUsername())) {
            errorObject.setInvalidPhone("This phone is already exist!");
            valid = false;
        }

        errorObject.setValidateStatus(valid);
        return errorObject;
    }

}
