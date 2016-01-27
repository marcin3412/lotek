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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rekah4
 */
public class TicketShowAll extends HttpServlet {

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
            String limit = null;
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
            
            if(request.getParameter("limit") != null)
                limit = request.getParameter("limit");
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
                    ResultSet rs = db.getStatement().executeQuery("SELECT id FROM klient WHERE email='"+email+"'");
                    rs.next();
                    String klient_id = rs.getString("id");
                    System.out.println("Search: array of ticket for "+klient_id);
                    ResultSet rs2 = db.getStatement().executeQuery("SELECT * FROM los WHERE klient_id='"+klient_id+"'");
                    JSONArray lista = new JSONArray();
                    
                    while(rs2.next()){
                        JSONObject los = new JSONObject();
                        los.put("id", rs2.getString("id"));
                        los.put("num1", rs2.getString("num1"));
                        los.put("num2", rs2.getString("num2"));
                        los.put("num3", rs2.getString("num3"));
                        los.put("num4", rs2.getString("num4"));
                        los.put("num5", rs2.getString("num5"));
                        los.put("num6", rs2.getString("num6"));
                        los.put("klient_id", rs2.getString("klient_id"));
                        los.put("create_at", rs2.getString("create_at"));
                        los.put("kumulacja_id", rs2.getString("kumulacja_id"));
                        los.put("wygrany", rs2.getString("wygrany"));
                        los.put("ile_traf", rs2.getString("ile_traf"));
                        lista.put(los);
                    }
                    data.put("lista", lista);
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
