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
import longtv.daos.PromotionDAO;
import longtv.dtos.AccountDTO;
import longtv.dtos.AccountErrorObject;
import longtv.dtos.PromotionHistoryDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PromotionHistoryServlet", urlPatterns = {"/PromotionHistoryServlet"})
public class PromotionHistoryServlet extends HttpServlet {
    private static final String ERROR = "error";
    private static final String SUCCESS = "promotionhistorypage";
    private static final String INVALIDROLE = "searchuser";
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
            if(account != null) {
                if(account.getRole().equals("Admin")) {
                    PromotionDAO dao = new PromotionDAO();
                    List<PromotionHistoryDTO> promotionList = dao.loadAllPromotionHistory();
                    request.setAttribute("PROMOTIONHISTORY", promotionList);
                    url = SUCCESS;
                } else {
                    url = INVALIDROLE;
                }
            } else {
                url = NOTLOGIN;
                request.setAttribute("LOGINSTATUS", "Your session has timed out, please login again!");
            }
        } catch (Exception e) {
            log("ERROR at PromotionHistoryServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Showing Promotion History");
            errorObj.setErrorDetail("Can not show history right row, contact owner for more information!");
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
