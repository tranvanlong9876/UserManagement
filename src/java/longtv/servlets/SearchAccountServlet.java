/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longtv.daos.AccountDAO;
import longtv.daos.SearchDAO;
import longtv.dtos.AccountDTO;
import longtv.dtos.AccountErrorObject;
import longtv.dtos.RoleDTO;
import longtv.dtos.SearchDTO;
import longtv.dtos.UserDetailDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchAccountServlet", urlPatterns = {"/SearchAccountServlet"})
public class SearchAccountServlet extends HttpServlet {

    private static final String ERROR = "error";
    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String NOTLOGIN = "backtologin";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Map<String, String> map = (Map<String, String>) request.getServletContext().getAttribute("FORWARD");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNTDETAIL");
            if (account != null) {
                if (account.getRole().equals("Admin")) {
                    String searchRole = request.getParameter("dboSearchRole");
                    String searchContent = request.getParameter("txtSearchName");
                    if (searchRole == null || searchRole.equals("")) {
                        searchRole = "0";
                    }
                    if (searchContent == null) {
                        searchContent = "";
                    }
                    int role = Integer.parseInt(searchRole);
                    AccountDAO accountDAO = new AccountDAO();
                    List<RoleDTO> allRole = accountDAO.loadAllRoles();
                    if (allRole.size() > 0) {
                        SearchDAO searchDAO = new SearchDAO();
                        List<UserDetailDTO> allAccount = searchDAO.loadAllUser(searchContent, role);
                        SearchDTO detail = new SearchDTO(allAccount, allRole, searchContent, role);
                        request.setAttribute("SEARCH_DETAIL", detail);
                        url = ADMIN;
                    }
                } else if(account.getRole().equals("Employee") || account.getRole().equals("Leader")) {
                    String userID = account.getUserName();
                    SearchDAO searchDAO = new SearchDAO();
                    UserDetailDTO userDetail = searchDAO.loadUserInfo(userID);
                    if(userDetail != null) {
                        request.setAttribute("ACCOUNT_DETAIL", userDetail);
                        url = USER;
                    }
                }
            } else {
                url = NOTLOGIN;
            }
        } catch (Exception e) {
            log("ERROR at SearchAccountServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Searching Account");
            errorObj.setErrorDetail("Can not search account right row, contact owner for more information!");
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
