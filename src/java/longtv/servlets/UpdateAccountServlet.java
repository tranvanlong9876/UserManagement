/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.servlets;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longtv.daos.AccountDAO;
import longtv.dtos.AccountDTO;
import longtv.dtos.AccountErrorObject;
import longtv.dtos.UpdateDTO;
import longtv.util.EncryptPassword;
import longtv.util.ValidateInput;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    private static final String ERROR = "error";
    private static final String SUCCESS = "searchuser";
    private static final String INVALID_INPUT = "edit";
    private static final String NOT_LOGIN = "logout";
    private static final String RELOGIN = "logout";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        Map<String, String> forward = (Map<String, String>) request.getServletContext().getAttribute("FORWARD");
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNTDETAIL");
            if (account != null) {
                String checkBox = request.getParameter("check-box-password");
                String username = request.getParameter("txtUserID");
                String oldPassword = request.getParameter("txtOldPassword");
                String password = request.getParameter("txtPassword");
                String confirmPassword = request.getParameter("txtConfirm");
                String photoLink = request.getParameter("currentPhotoLink");
                String fullName = request.getParameter("txtFullname");
                int role = 1;
                if (request.getParameter("dboRole") != null) {
                    role = Integer.parseInt(request.getParameter("dboRole"));
                }
                String email = request.getParameter("txtEmail");
                String phone = request.getParameter("txtPhone");
                UpdateDTO updateDTO = new UpdateDTO(email, phone, fullName, oldPassword, password, confirmPassword, username, photoLink, role);
                boolean checkIsYourSelf = false;
                if (account.getUserName().equals(username)) {
                    checkIsYourSelf = true;
                }
                if (checkBox != null) {
                    ValidateInput validate = new ValidateInput();
                    AccountErrorObject errorObj = validate.updateValidateWithPassword(updateDTO, checkIsYourSelf);
                    if (errorObj.isValidateStatus()) {
                        AccountDAO accountDAO = new AccountDAO();
                        updateDTO.setNewPassword(EncryptPassword.encodePassword(updateDTO.getNewPassword()));
                        if (checkIsYourSelf) {
                            updateDTO.setOldPassword(EncryptPassword.encodePassword(updateDTO.getOldPassword()));
                            if (accountDAO.checkRightOldPassword(username, updateDTO.getOldPassword())) {
                                if (accountDAO.updateAccountWithPassword(updateDTO)) {
                                    url = RELOGIN;
                                    request.setAttribute("LOGINSTATUS", "Your account's information is updated, login now!");
                                }
                            } else {
                                url = INVALID_INPUT;
                                errorObj.setInvalidOldPassword("Your old password does not matches!");
                                request.setAttribute("INVALID_UPDATE", errorObj);
                            }
                        } else {
                            if (accountDAO.updateAccountWithPassword(updateDTO)) {
                                request.setAttribute("STATUS_MANAGE", "The details of Username: " + updateDTO.getUsername() + " is updated.");
                                url = SUCCESS;
                            }
                        }
                    } else {
                        url = INVALID_INPUT;
                        request.setAttribute("INVALID_UPDATE", errorObj);
                    }
                } else {
                    ValidateInput validateInput = new ValidateInput();
                    AccountErrorObject errorObj = validateInput.updateValidateWithoutPassword(updateDTO, checkIsYourSelf);
                    if (errorObj.isValidateStatus()) {
                        AccountDAO accountDAO = new AccountDAO();
                        if (checkIsYourSelf) {
                            updateDTO.setOldPassword(EncryptPassword.encodePassword(updateDTO.getOldPassword()));
                            if (accountDAO.checkRightOldPassword(username, updateDTO.getOldPassword())) {
                                if (accountDAO.updateAccountWithoutPassword(updateDTO)) {
                                    url = RELOGIN;
                                    request.setAttribute("LOGINSTATUS", "Your account's information is updated, login now!");
                                }
                            } else {
                                url = INVALID_INPUT;
                                errorObj.setInvalidOldPassword("Your old password does not matches!");
                                request.setAttribute("INVALID_UPDATE", errorObj);
                            }
                        } else {
                            if (accountDAO.updateAccountWithoutPassword(updateDTO)) {
                                request.setAttribute("STATUS_MANAGE", "The details of Username: " + updateDTO.getUsername() + " is updated.");
                                url = SUCCESS;
                            }
                        }

                    } else {
                        url = INVALID_INPUT;
                        request.setAttribute("INVALID_UPDATE", errorObj);
                    }
                }
            } else {
                url = NOT_LOGIN;
            }
        } catch (Exception e) {
            log("ERROR at UpdateAccountServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Updating Account");
            errorObj.setErrorDetail("Can not update account right row, contact owner for more information!");
            request.setAttribute("ERROR", errorObj);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(forward.get(url));
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
