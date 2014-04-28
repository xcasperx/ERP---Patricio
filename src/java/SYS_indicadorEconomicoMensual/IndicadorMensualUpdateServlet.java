/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoMensual;

import Helpers.Format;
import SYS_indicadorEconomicoSemanal.IndicadorEconomicoSemanalBean;
import SYS_indicadorEconomicoSemanal.IndicadorSemanalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
@WebServlet(name = "IndicadorMensualUpdateServlet", urlPatterns = {"/IndicadorMensualUpdateServlet"})
public class IndicadorMensualUpdateServlet extends HttpServlet {

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

        ///////////////////////////
        // ESTABLECER CONEXION
        ///////////////////////////
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
                if (userTypeX != 1) {
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {

                    /* establecer variables de usuario en sesion */
                    request.setAttribute("idUserX", idUserX);
                    request.setAttribute("usernameX", usernameX);
                    request.setAttribute("userTypeX", userTypeX);

                    ////////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETROS
                    ////////////////////////////////////

                    String sid = request.getParameter("id");
                    String ipc = request.getParameter("ipc");
                    String utm = request.getParameter("utm");
                    String publicTime = request.getParameter("publicTime");

                    /* instanciar url */
                    String url = "?id=" + sid;

                    /* establecer variables de sesion */
                    session.setAttribute("redirectUpdate", "indicadorMensual");
                    session.setAttribute("ipc", ipc);
                    session.setAttribute("utm", utm);
                    session.setAttribute("publicTime", publicTime);

                    /* instanciar indicador */
                    IndicadorEconomicoMensualBean indicador = new IndicadorEconomicoMensualBean();

                    /* flag de error */
                    boolean error = false;

                    /* comprobar id */
                    if (sid == null || sid.trim().equals("")) {
                        error = true;
                    } else {
                        try {
                            indicador.setId(Integer.parseInt(sid));
                        } catch (NumberFormatException n) {
                            error = true;
                        }
                    }

                    /* comprobar ipc */
                    if (ipc == null || ipc.trim().equals("")) {
                        session.setAttribute("msgErrorIPC", "Debe ingresar IPC.");
                        error = true;
                    } else {
                        try {
                            indicador.setIpc(Float.parseFloat(ipc));
                        } catch (NumberFormatException n) {
                            session.setAttribute("msgErrorIPC", "El porcentaje del IPC debe ser numérico");
                            error = true;
                        }
                    }

                    /* comprobar utm */
                    if (utm == null || utm.trim().equals("")) {
                        session.setAttribute("msgErrorUTM", "Debe ingresar UTM.");
                        error = true;
                    } else {
                        try {
                            indicador.setUtm(Float.parseFloat(utm));
                        } catch (NumberFormatException n) {
                            session.setAttribute("msgErrorUTM", "El valor de la UTM debe ser numérico");
                            error = true;
                        }
                    }

                    /* comprobar public time */
                    if (publicTime == null || publicTime.trim().equals("")) {
                        session.setAttribute("msgErrorPublicTime", "Debe ingresar fecha de publicación.");
                        error = true;
                    } else {
                        indicador.setPublicTime(Format.date2DDMMYYYY(publicTime));
                    }

                    /* establecer publicador */
                    indicador.setIdUser(idUserX);

                    ///////////////////////
                    // LOGICA DE NEGOCIO
                    ///////////////////////

                    /* actualizar registro */
                    if (!error) {
                        try {
                            imDAO.indicadorMensualUpdate(indicador);
                            session.setAttribute("msgOk", "Registro actualizado exitosamente.");
                        } catch (Exception ex) {
                            session.setAttribute("msgErrorUpdate", "Ha ocurrido un problema y no puede actualizar. Error:" + ex.getLocalizedMessage());
                            ex.getCause();
                        }
                    }

                    /* send redirect */
                    response.sendRedirect("/ERP/IndicadorMensualGetServlet" + url);
                }
            } catch (Exception sessionException) {
                /* enviar a la vista de login */
                System.out.println("no ha iniciado session");
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
