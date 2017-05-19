/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel.puig
 */
@WebServlet(name = "FormPotionTrainer", urlPatterns = {"/FormPotionTrainer"})
public class FormPotionTrainer extends HttpServlet {
    
    @EJB
    StukemonEJB miEjb;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormPotionTrainer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormPotionTrainer at " + request.getContextPath() + "</h1>");
            out.println("<form action=\"ComprarPociones\" method=\"GET\">");
            out.println("<label>Entrenador</label>");
            out.println("<select name=\"entrenador\">");

            try {

                List<Trainer> allTrainers = miEjb.selectAllTrainers();

                for (Trainer currentTrainer : allTrainers) {
                    out.println("<option value=" + currentTrainer.getName() + ">" + currentTrainer.getName() + " Puntos: "+currentTrainer.getPoints()+"</option>");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            out.println("</select>");
            out.println("<label>Cantidad a comprar (10 puntos/unidad)</label>");
            out.println("<input type=\"number\" name=\"cantidad\">");

            out.println("<input type=\"submit\" value=\"Seleccionar\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
