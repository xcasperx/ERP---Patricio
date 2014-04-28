/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoMensual;

import Helpers.Message;
import Helpers.MessageList;
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
@WebServlet(name = "IndicadorMensualGetAddServlet", urlPatterns = {"/IndicadorMensualGetAddServlet"})
public class IndicadorMensualGetAddServlet extends HttpServlet {

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

            IndicadorMensualDAO imDAO = new IndicadorMensualDAO();
            imDAO.setConexion(conexion);

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
                if (userTypeX > 4) {
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {                    
                    /* establecer variables de usuario en sesion */
                    request.setAttribute("idUserX", idUserX);
                    request.setAttribute("usernameX", usernameX);
                    request.setAttribute("userTypeX", userTypeX);

                    ////////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETROS
                    ////////////////////////////////////

                    /* obtener atributos de session */
                    String redirect = (String) session.getAttribute("redirectAdd");
                    String ipc = (String) session.getAttribute("ipc");
                    String utm = (String) session.getAttribute("utm");
                    String publicTime = (String) session.getAttribute("publicTime");

                    /* obtener mensajes de session */
                    String msgErrorIPC = (String) session.getAttribute("msgErrorIPC");
                    String msgErrorUTM = (String) session.getAttribute("msgErrorUTM");
                    String msgErrorAdd = (String) session.getAttribute("msgErrorAdd");
                    String msgOk = (String) session.getAttribute("msgOk");

                    /* limpiar variables de sesion */
                    session.setAttribute("redirectAdd", null);
                    session.setAttribute("ipc", null);
                    session.setAttribute("utm", null);
                    session.setAttribute("publicTime", null);
                    session.setAttribute("msgErrorIPC", null);
                    session.setAttribute("msgErrorUTM", null);
                    session.setAttribute("msgErrorAdd", null);
                    session.setAttribute("msgOk", null);

                    /* instanciar lista de mensajes */
                    Collection<Message> msgList = new ArrayList<Message>();

                    /* comprobar redirect */
                    if (redirect == null || redirect.trim().equals("")) {
                        request.setAttribute("msg", "Ingrese un nuevo indicador.");

                    } else if (redirect.equals("indicadorMensual")) {
                        /* establecer atributos de session  */
                        request.setAttribute("ipc", ipc);
                        request.setAttribute("utm", utm);
                        request.setAttribute("publicTime", publicTime);

                        /* comprobar ipc */
                        if (msgErrorIPC == null || msgErrorIPC.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorIPC", true);
                            msgList.add(MessageList.addMessage(msgErrorIPC));
                        }

                        /* comprobar utm */
                        if (msgErrorUTM == null || msgErrorUTM.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorUTM", true);
                            msgList.add(MessageList.addMessage(msgErrorUTM));
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
                    request.setAttribute("indicadorMensualActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/indicadorEconomico/indicadorMensualAdd.jsp").forward(request, response);
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
