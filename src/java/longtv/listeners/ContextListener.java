/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Admin
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String fileName = sc.getInitParameter("forwardPage");
        String filePath = sc.getRealPath("/" + fileName);

        try {
            File f = new File(filePath);
            if (f.exists()) {
                Map<String, String> map = new HashMap<>();
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] keyValue = line.split("=");
                    map.put(keyValue[0], keyValue[1]);
                }
                sc.setAttribute("FORWARD", map);
                br.close();
                fr.close();
            }
        } catch (IOException e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, "File Forward not found, could not filter website", e.getCause());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        sc.removeAttribute("FORWARD");
    }

}
