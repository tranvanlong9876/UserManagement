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
import longtv.dtos.RegisterDTO;
import longtv.util.EncryptPassword;
import longtv.util.ValidateInput;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/RegisterAccountServlet"})
public class RegisterAccountServlet extends HttpServlet {

    private static final String VALIDATE_FAIL = "register";
    private static final String ERROR = "error";
    private static final String REGISTER_SUCCESS = "searchuser";
    private static final String INVALID_ROLE = "searchuser";
    private static final String NOTLOGIN = "backtologin";

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
        Map<String, String> map = (Map<String, String>) request.getServletContext().getAttribute("FORWARD");
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNTDETAIL");
            if (account != null) {
                if (account.getRole().equals("Admin")) {
                    String username = request.getParameter("txtUsername").trim().toLowerCase();
                    String password = request.getParameter("txtPassword");
                    String confirmPassword = request.getParameter("txtConfirm");
                    String fullName = request.getParameter("txtFullName");
                    String email = request.getParameter("txtEmail");
                    String phone = request.getParameter("txtPhone");
                    String fileName = request.getParameter("photoLink");
                    int role = Integer.parseInt(request.getParameter("cboRole"));
                    RegisterDTO dto = new RegisterDTO(email, phone, fullName, password, confirmPassword, username, fileName, role);
                    ValidateInput input = new ValidateInput();
                    AccountErrorObject errorObj = input.statusValidate(dto);
                    if (errorObj.isValidateStatus()) {
                        String encryptPassword = EncryptPassword.encodePassword(password);
                        dto.setPassword(encryptPassword);
                        AccountDAO dao = new AccountDAO();
                        if (!dao.checkExistUsername(username)) {
                            if (dao.insertNewAccount(dto)) {
                                url = REGISTER_SUCCESS;
                                request.setAttribute("STATUS_MANAGE", "New Username: " + dto.getUsername() + " has been created!");
                            }
                        } else {
                            url = VALIDATE_FAIL;
                            errorObj.setInvalidUsername("Username has already existed!");
                            request.setAttribute("INVALID_REGISTER", errorObj);
                        }
                    } else {
                        url = VALIDATE_FAIL;
                        request.setAttribute("INVALID_REGISTER", errorObj);
                    }
                } else {
                    url = INVALID_ROLE;
                }
            } else {
                url = NOTLOGIN;
            }
        } catch (Exception e) {
            log("ERROR at RegisterAccountServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Registering Account");
            errorObj.setErrorDetail("Can not register account right row, contact owner for more information!");
            request.setAttribute("ERROR", errorObj);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(map.get(url));
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
