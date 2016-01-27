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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.Database;
import object.JsonRespone;

/**
 *
 * @author rekah4
 */
@WebServlet(name = "TicketAdd", urlPatterns = {"/ticket/add"}, initParams = {
    @WebInitParam(name = "ssid", value = ""),
    @WebInitParam(name = "kumulacja_id", value = ""),
    @WebInitParam(name = "num1", value = ""),
    @WebInitParam(name = "num2", value = ""),
    @WebInitParam(name = "num3", value = ""),
    @WebInitParam(name = "num4", value = ""),
    @WebInitParam(name = "num5", value = ""),
    @WebInitParam(name = "num6", value = "")})
public class TicketAdd extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String ssid = null;
            String kumulacja_id = null;
            String num1 = null;
            String num2 = null;
            String num3 = null;
            String num4 = null;
            String num5 = null;
            String num6 = null;
            String email = null;
            JsonRespone jr = new JsonRespone();
            
            if(request.getParameter("ssid") != null)
                ssid = request.getParameter("ssid");
            else
                jr.setStatus(false);
            
            if(request.getParameter("kumulacja_id") != null)
                kumulacja_id = request.getParameter("kumulacja_id");
            else
                jr.setStatus(false);
            
            if(request.getParameter("num1") != null)
                num1 = request.getParameter("num1");
            else
                jr.setStatus(false);
            
            if(request.getParameter("num2") != null)
                num2 = request.getParameter("num2");
            else
                jr.setStatus(false);
            
            if(request.getParameter("num3") != null)
                num3= request.getParameter("num3");
            else
                jr.setStatus(false);
            
            if(request.getParameter("num4") != null)
                num4 = request.getParameter("num4");
            else
                jr.setStatus(false);
            
            if(request.getParameter("num5") != null)
                num5 = request.getParameter("num5");
            else
                jr.setStatus(false);
            
            if(request.getParameter("num6") != null)
                num6 = request.getParameter("num6");
            else
                jr.setStatus(false);
            
            if(request.getParameter("email") != null)
                email = request.getParameter("email");
            else
                jr.setStatus(false);
            
            
            
            if(jr.isError()){
                jr.setMsg("Brak parametrow w zapytaniu.");
                out.print(jr.Flush(null));
            }else{
               if(System.getProperty(ssid) == null){
                    jr.setMsg("Nie zalogowany");
                    jr.setStatus(false);
                    out.println(jr.Flush(null));
                    
                }
               else
                   if(!email.equals(System.getProperty(ssid))){
                   jr.setMsg("Nie można zmienić danych innego użytkownika.");
                   jr.setStatus(false);
                }else
               {
                   //Gdy zalogowany
                   //dodajemy do bazy tiket
                   Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
                   SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
                   String create_at = dt.format(new Date());
                   
                   
                   //sprawdzanie kto to
                   ResultSet rs = db.getStatement().executeQuery("SELECT id FROM klient WHERE email='"+email+"'");
                   rs.next();
                   int klient_id = rs.getInt(1);
                   System.out.println("ticket added by klient: "+klient_id);
//                   db.getStatement().executeUpdate("INSERT INTO `los`( `num1`, `num2`, `num3`, `num4`, `num5`, `num6`, `klient_id`, `create_at`, `kumulacja_id`) "
//                           + "VALUES ('"+num1+"','"+num2+"','"+num3+"','"+num4+"','"+num5+"','"+num6+"','"+klient_id+"','"+create_at+"','"+kumulacja_id+"')");
                   jr.setMsg("Los został dodany.");
                   jr.setStatus(true);
                   out.print(jr.Flush(null));
                   db.Close();
               }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TicketAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TicketAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
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
