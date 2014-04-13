/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author patricio alberto
 */
@WebServlet(name = "AdminMainServlet", urlPatterns = {"/AdminMainServlet"})
public class AdminMainServlet extends HttpServlet {

    @Resource(name = "jdbc/ERP")
    private DataSource ds;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Connection conexion = null;

        /////////////////////////
        // ESTABLECER CONEXION
        /////////////////////////
        try {
            conexion = ds.getConnection();

            AdminDAO adminDAO = new AdminDAO();
            adminDAO.setConexion(conexion);

            ////////////////////////
            // COMPROBAR SESSION
            ////////////////////////
            try {
                /* recuperar sesion */
                HttpSession session = request.getSession(false);

                /* obtener parametros de session */
                int access = Integer.parseInt((String) session.getAttribute("access"));
                String user = (String) session.getAttribute("admin");
                String sidUser = (String) session.getAttribute("idUser");
                int idUser = Integer.parseInt(sidUser);

                /* comprobar permisos de usuario */
                if (access != 777) {
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {
                    /* obtener los valores de session y asignar valores a la jsp */
                    request.setAttribute("userJsp", user);
                    request.setAttribute("access", access);
                    request.setAttribute("idUser", idUser);

                    //////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETOS
                    //////////////////////////////////

                    /* obtener atributos de session */
                    String redirect = (String) session.getAttribute("redirectDel");

                    /* obtener mensajes de session */
                    String msgDel = (String) session.getAttribute("msgDel");
                    String msgErrorConstraint = (String) session.getAttribute("msgErrorConstraint");
                    String msgListErrorConstaint = (String) session.getAttribute("msgListErrorConstraint");

                    /* limpiar variables de session */
                    session.setAttribute("redirectDel", null);
                    session.setAttribute("msgDel", null);
                    session.setAttribute("msgErrorConstraint", null);
                    session.setAttribute("msgListErrorConstraint", null);

                    /* comprobar redirect */
                    if (redirect == null || redirect.trim().equals("")) {
                    } else if (redirect.equals("admin")) {

                        /* comprobar eliminacion */
                        if (msgDel == null || msgDel.trim().equals("")) {
                        } else {
                            request.setAttribute("msgDel", msgDel);
                        }

                        /* comprobar error eliminacion */
                        if (msgErrorConstraint == null || msgErrorConstraint.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorConstraint", msgErrorConstraint);
                        }

                        /* comprobar lista de errores de eliminacion */
                        if (msgListErrorConstaint == null || msgListErrorConstaint.isEmpty() || msgListErrorConstaint.trim().equals("")) {
                        } else {
                            request.setAttribute("msgListErrorConstraint", msgListErrorConstaint);
                        }
                    }

                    /* obtener lista de registros */
                    try {
                        Collection< Admin> list = adminDAO.getAll();
                        request.setAttribute("list", list);

                        request.setAttribute("regCount", list.size());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    /* despachar a la vista */
                    request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
                /*enviar al login*/
                request.getRequestDispatcher("/login/login.jsp").forward(request, response);
            }
        } catch (Exception connectionException) {
            connectionException.printStackTrace();
        } finally {
            /* cerrar conexion */
            try {
                conexion.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
