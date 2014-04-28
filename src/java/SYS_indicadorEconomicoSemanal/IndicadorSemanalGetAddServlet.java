/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoSemanal;

import Helpers.Message;
import Helpers.MessageList;
import SYS_indicadorEconomicoEstadistico.IndicadorEconomicoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
 * @author patricio
 */
@WebServlet(name = "IndicadorSemanalGetAddServlet", urlPatterns = {"/IndicadorSemanalGetAddServlet"})
public class IndicadorSemanalGetAddServlet extends HttpServlet {

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

            IndicadorEconomicoDAO ieDAO = new IndicadorEconomicoDAO();
            ieDAO.setConexion(conexion);

            ////////////////////////
            // COMPROBAR SESSION
            ////////////////////////
            try {
                /* recuperar sesion */
                HttpSession session = request.getSession(false);

                /* obtener parametros de session */
                int idUserX = Integer.parseInt((String) session.getAttribute("idUserX"));
                int userTypeX = Integer.parseInt((String) session.getAttribute("userTypeX"));
                String usernameX = (String) session.getAttribute("usernameX");

                ///////////////////////////////////
                // COMPROBAR PERMISOS DE USUARIO
                ///////////////////////////////////

                /* comprobar permisos de usuario */
                if (userTypeX != 1) {
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {
                    /* ACCESO SUPERUSUARIO */

                    /* establecer variables de usuario en sesion */
                    request.setAttribute("idUserX", idUserX);
                    request.setAttribute("usernameX", usernameX);
                    request.setAttribute("userTypeX", userTypeX);

                    ////////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETROS
                    ////////////////////////////////////

                    /* obtener atributos de session */
                    String redirect = (String) session.getAttribute("redirectAdd");
                    String bencina93 = (String) session.getAttribute("bencina93");
                    String bencina95 = (String) session.getAttribute("bencina95");
                    String bencina97 = (String) session.getAttribute("bencina97");
                    String diesel = (String) session.getAttribute("diesel");

                    /* obtener mensajes de session */
                    String msgErrorBencina93 = (String) session.getAttribute("msgErrorBencina93");
                    String msgErrorBencina95 = (String) session.getAttribute("msgErrorBencina95");
                    String msgErrorBencina97 = (String) session.getAttribute("msgErrorBencina97");
                    String msgErrorDiesel = (String) session.getAttribute("msgErrorDiesel");
                    String msgErrorAdd = (String) session.getAttribute("msgErrorAdd");
                    String msgOk = (String) session.getAttribute("msgOk");

                    /* limpiar variables de sesion */
                    session.setAttribute("redirectAdd", null);
                    session.setAttribute("bencina93", null);
                    session.setAttribute("bencina95", null);
                    session.setAttribute("bencina97", null);
                    session.setAttribute("diesel", null);
                    session.setAttribute("msgErrorBencina93", null);
                    session.setAttribute("msgErrorBencina95", null);
                    session.setAttribute("msgErrorBencina97", null);
                    session.setAttribute("msgErrorDiesel", null);
                    session.setAttribute("msgErrorAdd", null);
                    session.setAttribute("msgOk", null);

                    /* instanciar lista de mensajes */
                    Collection<Message> msgList = new ArrayList<Message>();

                    /* comprobar redirect */
                    if (redirect == null || redirect.trim().equals("")) {
                        request.setAttribute("msg", "Ingrese un nuevo indicador.");

                    } else if (redirect.equals("indicadorSemanal")) {
                        /* establecer atributos de session */
                        request.setAttribute("bencina93", bencina93);
                        request.setAttribute("bencina95", bencina95);
                        request.setAttribute("bencina97", bencina97);
                        request.setAttribute("diesel", diesel);

                        /* comprobar bencina 93 */
                        if (msgErrorBencina93 == null || msgErrorBencina93.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorBencina93", true);
                            msgList.add(MessageList.addMessage(msgErrorBencina93));
                        }

                        /* comprobar bencina 95 */
                        if (msgErrorBencina95 == null || msgErrorBencina95.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorBencina95", true);
                            msgList.add(MessageList.addMessage(msgErrorBencina95));
                        }

                        /* comprobar bencina 97 */
                        if (msgErrorBencina97 == null || msgErrorBencina97.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorBencina97", true);
                            msgList.add(MessageList.addMessage(msgErrorBencina97));
                        }

                        /* comprobar diesel */
                        if (msgErrorDiesel == null || msgErrorDiesel.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorDiesel", true);
                            msgList.add(MessageList.addMessage(msgErrorDiesel));
                        }

                        /* comprobar update */
                        if (msgErrorAdd == null || msgErrorAdd.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorAdd", true);
                            msgList.add(MessageList.addMessage(msgErrorAdd));
                        }

                        /* mensaje de exito */
                        if (msgOk == null || msgOk.trim().equals("")) {
                        } else {
                            request.setAttribute("msgOk", msgOk);
                        }
                    }

                    /* despachar lista de mensajes */
                    if (!msgList.isEmpty()) {
                        request.setAttribute("msgList", msgList);
                    }

                    /* marcar pestaña de menu */
                    request.setAttribute("indicadorSemanalActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/indicadorEconomico/indicadorSemanalAdd.jsp").forward(request, response);
                }
            } catch (Exception sessionException) {
                request.getRequestDispatcher("/login/login.jsp").forward(request, response);
                System.out.println("no ha iniciado session");
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
