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
import longtv.dtos.AccountDTO;
import longtv.dtos.AccountErrorObject;
import longtv.dtos.PromotionDTO;
import longtv.dtos.PromotionListDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddPromotionListServlet", urlPatterns = {"/AddPromotionListServlet"})
public class AddPromotionListServlet extends HttpServlet {

    private static final String SUCCESS = "searchuser";
    private static final String ERROR = "error";
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
                    PromotionListDTO promotionList = (PromotionListDTO) session.getAttribute("PROMOTIONDETAIL");
                    if (promotionList == null) {
                        promotionList = new PromotionListDTO();
                    }
                    String userID = request.getParameter("txtUserID");
                    String fullName = request.getParameter("promotionName");
                    String role = request.getParameter("promotionRole");
                    String photo = request.getParameter("promotionImage");
                    int defaultRank = 5;
                    PromotionDTO dto = new PromotionDTO(userID, fullName, role, photo, defaultRank);
                    if (promotionList.getPromotionList().containsKey(dto.getUserID())) {
                        url = SUCCESS;
                        request.setAttribute("STATUS_MANAGE", "This Username has already in promotion list, view promotion list to see details!");
                    } else {
                        promotionList.addNewList(dto);
                        session.setAttribute("PROMOTIONDETAIL", promotionList);
                        url = SUCCESS;
                        request.setAttribute("STATUS_MANAGE", "New Username: " + userID + " has been added to promotion list.");
                    }
                } else {
                    url = SUCCESS;
                }
            } else {
                url = NOTLOGIN;
                request.setAttribute("LOGINSTATUS", "Your session has timed out, please log in again");
            }
        } catch (Exception e) {
            log("ERROR at AddPromotionListServlet: " + e.getMessage());
            AccountErrorObject errorObj = new AccountErrorObject();
            errorObj.setErrorServlet("Adding User To Promotion List");
            errorObj.setErrorDetail("Can not add new promotion list right row, contact owner for more information!");
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
