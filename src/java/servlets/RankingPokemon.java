/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StukemonEJB;
import entities.Pokemon;
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
@WebServlet(name = "RankingPokemon", urlPatterns = {"/RankingPokemon"})
public class RankingPokemon extends HttpServlet {
    
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
            out.println("<title>Servlet RankingPokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RankingPokemon at " + request.getContextPath() + "</h1>");
            List<Pokemon> allPokemon = miEjb.getPokemonRanking();
            out.println("<div>");
            out.println("<span>Pokemon</span>");
            out.println("<span style=\"position:absolute;left:200px\">Entrenador</span>");
            out.println("<span style=\"position:absolute;left:400px\">Vida</span>");
            out.println("<hr>");
            out.println("<div>");
            for (Pokemon currentPoke : allPokemon) {
                out.println("<div>");
                out.println("<span>" + currentPoke.getName() + ", Nivel "+currentPoke.getLevel()+"</span>");
                out.println("<span style=\"position:absolute;left:200px\">" + currentPoke.getTrainer().getName() + "</span>");
                out.println("<span style=\"position:absolute;left:400px\"> Vida: " + currentPoke.getLife()+ "</span>");
                out.println("</div>");
            }
            out.println("</div>");
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
