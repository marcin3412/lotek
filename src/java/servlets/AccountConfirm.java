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

/**
 *
 * @author rekah4
 */
public class AccountConfirm extends HttpServlet {

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
           String confirm_cod = null;
           
           JsonRespone jr = new JsonRespone();
            jr.setStatus(true);
            
            if(request.getParameter("confirm_cod") != null)
                confirm_cod = request.getParameter("confirm_cod");
            else
                jr.setStatus(false);
            
            if(jr.isError()){
                jr.setMsg("Brak parametrow w zapytaniu.");
                out.print(jr.Flush(null));
            }
            else{
                Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
                ResultSet rs  = db.getStatement().executeQuery("SELECT Count(*) FROM klient WHERE confirm_cod = '"+confirm_cod+"'");
                rs.next();
                if(rs.getInt(1) == 1){
                    ResultSet rs2 = db.getStatement().executeQuery("SELECT * FROM klient WHERE confirm_cod = '"+confirm_cod+"'");
                    rs2.next();
                    
                    db.getStatement().executeUpdate("UPDATE `klient` SET `imie`='"+rs2.getString("imie")+"',`nazwisko`='"+rs2.getString("nazwisko")+"',`email`='"+rs2.getString("email")+"',`haslo`='"+rs2.getString("haslo")+"',`confirm_cod`='',`confirmed`='1',`create_at`='"+rs2.getString("create_at")+"' WHERE id = " + rs2.getInt("id"));
                    jr.setMsg("Aktywowano konto");
                    jr.setStatus(true);
                    out.println(jr.Flush(null));
                }   
                
                else{
                    jr.setStatus(false);
                    jr.setMsg("Bledny kod aktywacyjny");
                    out.println(jr.Flush(null));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountConfirm.class.getName()).log(Level.SEVERE, null, ex);
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
