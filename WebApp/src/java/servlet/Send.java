/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import grafo.Grafo;
import grafo.GrafoCreate;
import grafo.Ruta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Nodo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Krauss
 */
public class Send extends HttpServlet {

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
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            String accion = request.getParameter("accion");
            String s = "";
            switch (accion) {
                case "list":
                    s = nodes();
                    break;
                case "rutaRapida":
                    s = rutaRapida(request);
                    break;
                case "rutaEco":
                    s = rutaEco(request);
                    break;
                case "cost":
                    s = costMin();
                    break;
                case "dist":
                    s = distMin();
                    break;
            }
            response.getWriter().write(s);
        } catch (Exception e) {
        }
    }
    
        public String nodes() throws JSONException {
        Grafo<String, Integer, String> grafo = GrafoCreate.cost();
        List<String> vertices = grafo.getIdsVertices();
        JSONArray array = new JSONArray();
        for (String vertice : vertices) {
            JSONObject obj = new JSONObject();
            obj.put("key", vertice);
            array.put(obj);
        }
        return (array.toString());
    }
    
    public String rutaRapida(HttpServletRequest request) throws JSONException {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        ArrayList<Ruta> caminos = GrafoCreate.dist().getRutas(origen,destino);
        ArrayList<Nodo<String,Integer,String>> caminoMenor = GrafoCreate.dist().getMenor(caminos);
        JSONArray array = new JSONArray();
        for (Nodo<String, Integer, String> nodo : caminoMenor) {
            JSONObject obj = new JSONObject();
            String $nodo = nodo.getId()+"";
            obj.put("key", $nodo);
            array.put(obj);
        }
        return (array.toString());
    }
    
    public String rutaEco(HttpServletRequest request) throws JSONException {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        ArrayList<Ruta> caminos = GrafoCreate.cost().getRutas(origen,destino);
        ArrayList<Nodo<String,Integer,String>> caminoMenor = GrafoCreate.cost().getMenor(caminos);
        JSONArray array = new JSONArray();
        for (Nodo<String, Integer, String> nodo : caminoMenor) {
            JSONObject obj = new JSONObject();
            String $nodo = nodo.getId()+"";
            obj.put("key", $nodo);
            array.put(obj);
        }
        return (array.toString());
    }
    
    public String costMin() throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("key", GrafoCreate.cost().getMenor());
        return obj.toString();
    }
    
    public String distMin() throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("key", GrafoCreate.dist().getMenor());
        return obj.toString();
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
