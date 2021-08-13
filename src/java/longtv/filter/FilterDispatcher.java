/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.filter;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import longtv.dtos.AccountErrorObject;
import longtv.listeners.ContextListener;

/**
 *
 * @author Admin
 */
public class FilterDispatcher implements Filter {

    public FilterDispatcher() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) {
        ServletContext sc = request.getServletContext();
        try {
            Map<String, String> map = (Map<String, String>) sc.getAttribute("FORWARD");
            HttpServletRequest req = (HttpServletRequest) request;
            req.setCharacterEncoding("UTF-8");
            String uri = req.getRequestURI();
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            String action = req.getParameter("action");
            if (action == null) {
                action = "";
            }
            if (resource.equals("manage")) {
                if (action.equals("Edit")) {
                    resource = "edit";
                } else if (action.equals("Delete")) {
                    resource = "delete";
                } else if (action.equals("addNewPromotion")) {
                    resource = "addToPromotion";
                } else {
                    resource = "error";
                }
            } else if (resource.equals("updateForm")) {
                if (action.equals("confirm")) {
                    resource = "update";
                } else if (action.equals("cancel")) {
                    resource = "searchuser";
                } else {
                    resource = "error";
                }
            } else if (resource.equals("registerForm")) {
                if (action.equals("Register")) {
                    resource = "registeraccount";
                } else if (action.equals("Cancel")) {
                    resource = "searchuser";
                } else {
                    resource = "error";
                }
            } else if (resource.equals("managepromotion")) {
                if (action.equals("Update")) {
                    resource = "updatepromotionlist";
                } else if (action.equals("Delete")) {
                    resource = "deletepromotionlist";
                } else {
                    resource = "error";
                }
            } else if (resource.equals("confirmPromotion")) {
                if (action.equals("Confirm")) {
                    resource = "confirmpromotionlist";
                } else if (action.equals("Cancel")) {
                    resource = "searchuser";
                } else {
                    resource = "error";
                }
            }
            if (resource.contains("Servlet") || resource.contains(".jsp")) {
                resource = "error";
            }
            if (resource.equals("error")) {
                AccountErrorObject errorObj = new AccountErrorObject();
                errorObj.setErrorServlet("Filtering Controller");
                errorObj.setErrorDetail("Your action is invalid");
                request.setAttribute("ERROR", errorObj);
            }
            String url = map.get(resource);
            if (url != null) {
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "Exception in Filter Controller", e.getCause());
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }
}
