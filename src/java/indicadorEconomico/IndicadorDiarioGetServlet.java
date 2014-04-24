/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorEconomico;

import Helpers.Message;
import Helpers.MessageList;
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
import user.UserBean;
import user.UserDAO;

/**
 *
 * @author patricio
 */
@WebServlet(name = "IndicadorDiarioGetServlet", urlPatterns = {"/IndicadorDiarioGetServlet"})
public class IndicadorDiarioGetServlet extends HttpServlet {

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

                    /* recibir atributos por PRG */
                    String sid = request.getParameter("id");

                    /* obtener atributos de session */
                    String redirect = (String) session.getAttribute("redirectUpdate");
                    String uf = (String) session.getAttribute("uf");
                    String euro = (String) session.getAttribute("euro");
                    String dolar = (String) session.getAttribute("dolar");
                    String publicTime = (String) session.getAttribute("publicTime");

                    /* obtener mensajes de session */
                    String msgErrorUpdate = (String) session.getAttribute("msgErrorUpdate");
                    String msgOk = (String) session.getAttribute("msgOk");

                    /* limpiar variables de sesion */
                    session.setAttribute("redirectUpdate", null);
                    session.setAttribute("msgErrorUpdate", null);
                    session.setAttribute("msgOk", null);

                    /* instanciar lista de mensajes */
                    Collection<Message> msgList = new ArrayList<Message>();

                    /* obtener indicador por id */
                    IndicadorEconomicoDiarioBean reg = null;
                    try {
                        reg = ieDAO.findByIdDiario(Integer.parseInt(sid));
                    } catch (Exception ex) {
                        ex.getCause();
                    }

                    if (reg != null) {
                        /* establecer atributos de reg */
                        request.setAttribute("id", reg.getId());

                        /* comprobar redirect */
                        if (redirect == null || redirect.trim().equals("")) {
                            /* establecer atributos de reg */
                            request.setAttribute("msg", "El registro ha sido encontrado!");
                            request.setAttribute("uf", reg.getUf());
                            request.setAttribute("euro", reg.getEuro());
                            request.setAttribute("dolar", reg.getDolar());
                            request.setAttribute("publicTime", reg.getPublicTime());

                        } else if (redirect.equals("indicadorDiario")) {
                            /* establecer atributos de session */
                            request.setAttribute("uf", uf);
                            request.setAttribute("euro", euro);
                            request.setAttribute("dolar", dolar);
                            request.setAttribute("publicTime", publicTime);                            

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

                    /* despachar lista de mensajes */
                    if (!msgList.isEmpty()) {
                        request.setAttribute("msgList", msgList);
                    }

                    /* marcar pestaña de menu */
                    request.setAttribute("indicadorDiarioActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/indicadorEconomico/indicadorDiarioUpdate.jsp").forward(request, response);
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
