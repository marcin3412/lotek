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
import org.json.JSONObject;

/**
 *
 * @author rekah4
 */
public class UserEdit extends HttpServlet {

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
           String imie = null;
           String nazwisko = null;
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
           
           if(request.getParameter("imie") != null)
                imie = request.getParameter("imie");
            else
                jr.setStatus(false);
           
           if(request.getParameter("nazwisko") != null)
                nazwisko = request.getParameter("nazwisko");
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
                }else{
                    Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
                    
                    db.getStatement().executeUpdate("UPDATE `klient` SET `imie`='"+imie+"',`nazwisko`='"+nazwisko+"';");
                    jr.setStatus(true);
                    out.println(jr.Flush(null));
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
            Logger.getLogger(UserEdit.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserEdit.class.getName()).log(Level.SEVERE, null, ex);
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
