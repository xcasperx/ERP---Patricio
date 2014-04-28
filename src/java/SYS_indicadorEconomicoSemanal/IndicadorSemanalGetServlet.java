/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoSemanal;

import Helpers.Format;
import Helpers.Message;
import Helpers.MessageList;
import SYS_indicadorEconomicoEstadistico.IndicadorEconomicoDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "IndicadorSemanalGetServlet", urlPatterns = {"/IndicadorSemanalGetServlet"})
public class IndicadorSemanalGetServlet extends HttpServlet {

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

            IndicadorSemanalDAO isDAO = new IndicadorSemanalDAO();
            isDAO.setConexion(conexion);

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
                if (userTypeX > 2) {
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

                    /* recibir atributos por PRG */
                    String sid = request.getParameter("id");

                    /* obtener atributos de session */
                    String redirect = (String) session.getAttribute("redirectUpdate");
                    String bencina93 = (String) session.getAttribute("bencina93");
                    String bencina95 = (String) session.getAttribute("bencina95");
                    String bencina97 = (String) session.getAttribute("bencina97");
                    String diesel = (String) session.getAttribute("diesel");
                    String publicTime = (String) session.getAttribute("publicTime");

                    /* obtener mensajes de session */
                    String msgErrorBencina93 = (String) session.getAttribute("msgErrorBencina93");
                    String msgErrorBencina95 = (String) session.getAttribute("msgErrorBencina95");
                    String msgErrorBencina97 = (String) session.getAttribute("msgErrorBencina97");
                    String msgErrorPublicTime = (String) session.getAttribute("msgErrorPublicTime");
                    String msgErrorDiesel = (String) session.getAttribute("msgErrorDiesel");
                    String msgErrorUpdate = (String) session.getAttribute("msgErrorUpdate");
                    String msgOk = (String) session.getAttribute("msgOk");

                    /* limpiar variables de sesion */
                    session.setAttribute("redirectUpdate", null);
                    session.setAttribute("bencina93", null);
                    session.setAttribute("bencina95", null);
                    session.setAttribute("bencina97", null);
                    session.setAttribute("diesel", null);
                    session.setAttribute("publicTime", null);
                    session.setAttribute("msgErrorBencina93", null);
                    session.setAttribute("msgErrorBencina95", null);
                    session.setAttribute("msgErrorBencina97", null);
                    session.setAttribute("msgErrorDiesel", null);
                    session.setAttribute("msgErrorPublicTime", null);
                    session.setAttribute("msgErrorUpdate", null);
                    session.setAttribute("msgOk", null);

                    /* instanciar lista de mensajes */
                    Collection<Message> msgList = new ArrayList<Message>();

                    try {
                        /* obtener indicador por id */
                        IndicadorEconomicoSemanalBean reg = isDAO.findByIdSemanal(Integer.parseInt(sid));
                        if (reg != null) {
                            /* establecer atributos de reg */
                            request.setAttribute("id", reg.getId());

                            /* comprobar redirect */
                            if (redirect == null || redirect.trim().equals("")) {
                                /* establecer atributos de reg */
                                request.setAttribute("msg", "El registro ha sido encontrado!");
                                request.setAttribute("bencina93", reg.getBencina93());
                                request.setAttribute("bencina95", reg.getBencina95());
                                request.setAttribute("bencina97", reg.getBencina97());
                                request.setAttribute("diesel", reg.getDiesel());
                                System.out.println(reg.getPublicTime());
                                System.out.println(Format.dateDDMMYYYY(reg.getPublicTime()));
                                request.setAttribute("publicTime", Format.dateDDMMYYYY(reg.getPublicTime()));

                            } else if (redirect.equals("indicadorDiario")) {
                                /* establecer atributos de session */
                                request.setAttribute("bencina93", bencina93);
                                request.setAttribute("bencina95", bencina95);
                                request.setAttribute("bencina97", bencina97);
                                request.setAttribute("diesel", diesel);                                
                                request.setAttribute("publicTime", publicTime);

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
                                    msgList.add(MessageList.addMessage(msgErrorDiesel));
                                }

                                /* comprobar diesel */
                                if (msgErrorDiesel == null || msgErrorDiesel.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorDiesel", true);
                                    msgList.add(MessageList.addMessage(msgErrorDiesel));
                                }

                                /* comprobar public time */
                                if (msgErrorPublicTime == null || msgErrorPublicTime.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorPublicTime", true);
                                    msgList.add(MessageList.addMessage(msgErrorPublicTime));
                                }

                                /* comprobar update */
                                if (msgErrorUpdate == null || msgErrorUpdate.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorUpdate", true);
                                    msgList.add(MessageList.addMessage(msgErrorUpdate));
                                }

                                /* mensaje de exito */
                                if (msgOk == null || msgOk.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgOk", msgOk);
                                }
                            }
                        } else {
                            request.setAttribute("msgErrorFound", true);
                            msgList.add(MessageList.addMessage("El registro no ha sido encontrado."));
                        }
                    } catch (Exception ex) {
                        ex.getCause();
                        request.setAttribute("msgErrorFound", true);
                        msgList.add(MessageList.addMessage("Ha ocurrido un problema y no puede obtener el registro. Error:" + ex.getLocalizedMessage()));
                    }

                    /* despachar lista de mensajes */
                    if (!msgList.isEmpty()) {
                        request.setAttribute("msgList", msgList);
                    }

                    /* marcar pestaña de menu */
                    request.setAttribute("indicadorSemanalActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/indicadorEconomico/indicadorSemanalUpdate.jsp").forward(request, response);
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
