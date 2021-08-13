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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String ERROR = "error";
    private static final String SUCCESS = "loginsuccess";
    private static final String FAILED = "loginfailed";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        Map<String, String> map = (Map<String, String>) request.getServletContext().getAttribute("FORWARD");
        try {
            HttpSession session = request.getSession();
            AccountDTO checkAccount = (AccountDTO) session.getAttribute("ACCOUNTDETAIL");
            if (checkAccount == null) {
                String username = request.getParameter("txtUsername").trim();
                String password = request.getParameter("txtPassword").trim();
                if (username == null || username.length() == 0) {
                    username = "";
                }
                if (password == null || password.length() == 0) {
                    password = "";
                }
                
                String encryptPassword = EncryptPassword.encodePassword(password);
                AccountDAO dao = new AccountDAO();
                AccountDTO account = dao.checkLogin(username, encryptPassword);
                if (account != null) {
                    url = SUCCESS;
                    session.setAttribute("ACCOUNTDETAIL", account);
                } else {
                    url = FAILED;
                    request.setAttribute("LOGINSTATUS", "Invalid Username or Password");
                }
            } else {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at LoginServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Logining Account");
            errorObj.setErrorDetail("Can not login right row, contact owner for more information!");
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
