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
import java.util.Date;
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
import utils.MD5;

/**
 *
 * @author rekah4
 */
public class AccountLogin extends HttpServlet {

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
            String email = null;
            String password = null;
            
            JsonRespone jr = new JsonRespone();
            jr.setStatus(true);
            
            if(request.getParameter("email") != null)
                email = request.getParameter("email");
            else
                jr.setStatus(false);
            
            if(request.getParameter("haslo") != null)
                password = request.getParameter("haslo");
            else
                jr.setStatus(false);
            
            if(jr.isError()){
                jr.setMsg("Brak parametrow w zapytaniu.");
                out.print(jr.Flush(null));
            }
            else{
                //TODO
                MD5 md5 = new MD5();
                Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
                ResultSet rs  = db.getStatement().executeQuery("SELECT Count(*) FROM klient WHERE email = '"+email+"' AND haslo = '"+md5.Code(password)+"' AND confirmed = 1");
                rs.next();
                if(rs.getInt(1) == 1){
                    JSONObject ssid = new JSONObject();
                    String session_cod = md5.Code(new Date().toString() + password+email );

                   System.setProperty(session_cod, email);
                    
                    jr.setStatus(true);
                    jr.setMsg("uzytkownik zalogowany.");
                    out.print(jr.Flush(new JSONObject().put("ssid", session_cod)));
                    System.out.println("\t>> Zalogowanie: "+email+" at "+new Date());
                }
                else{
                jr.setMsg("Brak uzytkownika w bazie lub konto nie zostalo jeszcze aktywowane.");
                jr.setStatus(false);
                out.print(jr.Flush(null)); 
                }
                db.Close();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AccountLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(AccountLogin.class.getName()).log(Level.SEVERE, null, ex);
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
