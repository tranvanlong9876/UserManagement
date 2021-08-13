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
import longtv.util.EncryptPassword;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    private static final String ERROR = "error";
    private static final String SUCCESS = "searchuser";
    private static final String LOGOUT = "logout";
    private static final String BACKTOLOGIN = "backtologin";

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
        String url = ERROR;
        Map<String, String> map = (Map<String, String>) request.getServletContext().getAttribute("FORWARD");
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNTDETAIL");
            if (account != null) {
                String password = request.getParameter("txtPassword");
                if (password == null) {
                    password = "";
                }
                password = EncryptPassword.encodePassword(password);
                AccountDAO dao = new AccountDAO();
                if (dao.checkRightOldPassword(account.getUserName(), password)) {
                    String userID = request.getParameter("txtUserID").trim();
                    if (dao.deleteAccount(userID)) {
                        if (account.getUserName().equals(userID)) {
                            url = LOGOUT;
                            request.setAttribute("LOGINSTATUS", "Your account is now inactivate, contact the owner for more information.");
                        } else {
                            request.setAttribute("STATUS_MANAGE", "Username: " + userID + " has been deleted!");
                            url = SUCCESS;
                        }
                    }
                } else {
                    url = SUCCESS;
                    request.setAttribute("STATUS_MANAGE", "Delete Fail: Your password is incorrect.");
                }
            } else {
                url = BACKTOLOGIN;
                request.setAttribute("LOGINSTATUS", "Your session has timed out, you need to login again to confirm deleting account");
            }
        } catch (Exception e) {
            log("ERROR at DeleteAccountServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Deleting Account");
            errorObj.setErrorDetail("Can not delete right row, contact owner for more information!");
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
