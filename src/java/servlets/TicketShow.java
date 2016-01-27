/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.Database;
import object.JsonRespone;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rekah4
 */
public class TicketShow extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String ssid = null;
            String ticket_id = null;
            String email = null;
            
            JsonRespone jr = new JsonRespone();
            jr.setStatus(true);
            
            if(request.getParameter("ssid") != null)
                ssid = request.getParameter("ssid");
            else
                jr.setStatus(false);
            
            if(request.getParameter("email") != null)
                email = request.getParameter("email");
            else
                jr.setStatus(false);
            
            if(request.getParameter("ticket_id") != null)
                ticket_id = request.getParameter("ticket_id");
            else
                jr.setStatus(false);
            
            if(jr.isError()){
                jr.setMsg("Brak parametrow w zapytaniu.");
                out.print(jr.Flush(null));
            }
            else{
                System.out.println(System.getProperty(ssid));
                //ServletContext application = getServletConfig().getServletContext();
                if(System.getProperty(ssid) == null){
                    jr.setMsg("Nie zalogowany");
                    jr.setStatus(false);
                    out.println(jr.Flush(null));
                    
                }
                else
                if(!email.equals(System.getProperty(ssid))){
                   jr.setMsg("Dostęp do danych innego użytkownika niż zalogowany jest zabroniony.");
                   jr.setStatus(false);
                }
                else{
                    JSONObject data = new JSONObject();
                    Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
                    ResultSet rs = db.getStatement().executeQuery("SELECT * FROM los WHERE id='"+ticket_id+"'");
                    rs.next();
                    
                    data.put("id", rs.getString("id"));
                    data.put("num1", rs.getString("num1"));
                    data.put("num2", rs.getString("num2"));
                    data.put("num3", rs.getString("num3"));
                    data.put("num4", rs.getString("num4"));
                    data.put("num5", rs.getString("num5"));
                    data.put("num6", rs.getString("num6"));
                    data.put("klient_id", rs.getString("klient_id"));
                    data.put("create_at", rs.getString("create_at"));
                    data.put("kumulacja_id", rs.getString("kumulacja_id"));
                    data.put("wygrany", rs.getString("wygrany"));
                    data.put("ile_traf", rs.getString("ile_traf"));
                    jr.setStatus(true);
                    out.println(jr.Flush(data));
                    db.Close();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketShow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(TicketShow.class.getName()).log(Level.SEVERE, null, ex);
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
