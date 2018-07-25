
package com.st.webservlet;

import com.st.bean.BusInfo;
import com.st.bean.DriverInfo;
import com.st.bean.LoginInfo;
import com.st.db.Manager;
import com.st.db.ManagerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author admininstrator
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        Manager manager = new Manager();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("In web");
            JSONObject jSONObject = new JSONObject();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String data = bufferedReader.readLine();
            try {
                JSONObject json = (JSONObject) new JSONParser().parse(data);
                System.out.println(json.toJSONString());
                LoginInfo lt = new LoginInfo();
                String[] key = {"username", "password"};
                String[] value = {json.get("username").toString(), json.get("password").toString()};
                try {
                    lt = (LoginInfo) manager.getExists(lt.getClass(), key, value);
                } catch (ManagerException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (lt != null) {
                    jSONObject.put("user_id", lt.getId());
                    jSONObject.put("type", lt.getType());
                    if (lt.getType().equals("driver")) {
                        DriverInfo driverInfo = (DriverInfo) manager.getBean(DriverInfo.class, "login_id", lt.getId());
                        BusInfo busInfo = (BusInfo) manager.getBean(BusInfo.class, "id", driverInfo.getBus_id());
                        jSONObject.put("bus_id", busInfo.getId());
                        jSONObject.put("status", busInfo.getBus_status());
                    }
                    System.out.println(jSONObject.toString());

                } else {
                    jSONObject.put("user_id", "fail");

                }
                out.write(jSONObject.toString());
            } catch (ParseException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
