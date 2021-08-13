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
import longtv.daos.AccountDAO;
import longtv.daos.SearchDAO;
import longtv.dtos.AccountErrorObject;
import longtv.dtos.RoleDTO;
import longtv.dtos.UserDetailDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "GetAccountDetailServlet", urlPatterns = {"/GetAccountDetailServlet"})
public class GetAccountDetailServlet extends HttpServlet {
    private static final String ERROR = "error";
    private static final String UPDATE_PAGE = "updatepage";
    private static final String NOTFOUND = "searchuser";
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
        Map<String, String> map = (Map<String,String>) request.getServletContext().getAttribute("FORWARD");
        try {
            String username = request.getParameter("txtUserID");
            if(username.length() > 0) {
                SearchDAO searchDAO = new SearchDAO();
                AccountDAO accountDAO = new AccountDAO();
                UserDetailDTO userdetail = searchDAO.loadUserForUpdate(username);
                if(userdetail != null) {
                    List<RoleDTO> allRole = accountDAO.loadAllRoles();
                    request.setAttribute("USER_DETAIL", userdetail);
                    request.setAttribute("USER_ROLE", allRole);
                    url = UPDATE_PAGE;
                } else {
                    url = NOTFOUND;
                }
            } else {
                url = NOTFOUND;
            }
        } catch (Exception e) {
            log("ERROR at GetAccountDetailServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Showing Account Details");
            errorObj.setErrorDetail("Can not show right row, contact owner for more information!");
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
