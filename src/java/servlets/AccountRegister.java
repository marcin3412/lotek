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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import object.Database;
import object.JsonRespone;
import utils.MD5;

/**
 *
 * @author rekah4
 */
public class AccountRegister extends HttpServlet {

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
        response.setHeader("CORS","Access-Control-Allow-Origin");
        try (PrintWriter out = response.getWriter()) {
            String imie = null;
            String nazwisko = null;
            String haslo = null;
            String email = null;
            JsonRespone jr = new JsonRespone();
            
            jr.setStatus(true);
            if(request.getParameter("callback") != null)
                jr.callback = request.getParameter("callback");
            if(request.getParameter("imie") != null)
                imie = request.getParameter("imie");
            else
                jr.setStatus(false);
            
            if(request.getParameter("nazwisko") != null)
                nazwisko = request.getParameter("nazwisko");
            else 
                jr.setStatus(false);
            
            if(request.getParameter("email") != null)
                email = request.getParameter("email");
            else 
                jr.setStatus(false);
            
            if(request.getParameter("haslo") != null)
                haslo = request.getParameter("haslo");
            else 
                jr.setStatus(false);
            
            if(jr.isError()){
                jr.setMsg("Brak parametrow w zapytaniu.");
                out.print(jr.Flush(null));
            }
            else{
                Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
                ResultSet rs  = db.getStatement().executeQuery("SELECT Count(*) FROM klient WHERE email = '"+email+"'");
                rs.next();
                if(rs.getInt(1) == 0){
                    MD5 md5 = new MD5();
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
                    String create_at = dt.format(new Date());
                    
                    db.getStatement().executeUpdate("INSERT INTO `klient`(`imie`, `nazwisko`, `email`, `haslo`, `confirm_cod`, `confirmed`, `create_at`) "
                            + "Values('"+imie+"','"+nazwisko+"','"+email+"','"+md5.Code(haslo)+"','"+md5.Code(haslo+create_at)+"','0','"+create_at+"')");
                    jr.setMsg("Uzytkownik dodany do bazy");
                    jr.setStatus(true);
                    out.print(jr.Flush(null));
                    db.Close();
                }
                else{
                    jr.setMsg("Uzytkownik o takim emailu juz istnieje.");
                    jr.setStatus(false);
                    out.print(jr.Flush(null));
                }
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountRegister.class.getName()).log(Level.SEVERE, null, ex);
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
