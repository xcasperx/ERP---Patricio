/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorEconomico;

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
@WebServlet(name = "IndicadorEconomicoGetServlet", urlPatterns = {"/IndicadorEconomicoGetServlet"})
public class IndicadorEconomicoGetServlet extends HttpServlet {

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

            ///////////////////////
            // COMPROBAR SESSION
            ///////////////////////
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

                if (userTypeX > 3) {
                    /* acceso prohibido */
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {
                    /* acceso permitido para: 
                     * - superusuario 
                     * - administrador
                     * - coadiminstrador */

                    /* establecer variables de usuario en sesion */
                    request.setAttribute("idUserX", idUserX);
                    request.setAttribute("usernameX", usernameX);
                    request.setAttribute("userTypeX", userTypeX);

                    ////////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETROS
                    ////////////////////////////////////

                    /* obtener atributos de session */
                    String redirect = (String) session.getAttribute("redirectUpdate");

                    /* obtener mensajes de session */


                    /* limpiar variables de session */
                    session.setAttribute("redirectUpdate", null);
                    session.setAttribute("msgOk", null);

                    /* comprobar redirect */
                    if (redirect == null || redirect.trim().equals("")) {
                    } else if (redirect.equals("user")) {
                        /* establecer atributos a la vista */

                        /* instanciar lista de mensajes */
                        Collection<Message> msgList = new ArrayList<Message>();

                        /* establecer lista de mensajes a la peticion */
                        if (!msgList.isEmpty()) {
                            request.setAttribute("msgList", msgList);
                        }
                    }

                    //////////////
                    // GRAFICOS
                    //////////////

                    /* obtener indicador diario (uf, dolar, euro) */
                    try {
                        /* grafico euro vs dolar de 365 elementos */
                        Collection<IndicadorEconomicoDiarioBean> ufEuroDolarList = ieDAO.getIED365Days();
                        request.setAttribute("ufEuroDolarList", ufEuroDolarList);

                        /* grafico uf de 365 elementos */
                        Collection<IndicadorEconomicoDiarioBean> ufList = ieDAO.getIED365DaysUnixTime();
                        request.setAttribute("ufList", ufList);

                        /* gráfico de 14 elementos */
                        Collection<IndicadorEconomicoDiarioBean> iedList = ieDAO.getIED14();
                        request.setAttribute("iedList", iedList);

                        for (IndicadorEconomicoDiarioBean aux : iedList) {
                            /* valor uf actual */
                            request.setAttribute("UFhoy", aux.getUf());
                            /* valor dolar actual */
                            request.setAttribute("dolarHoy", aux.getDolar());
                            /* valor euro hoy */
                            request.setAttribute("euroHoy", aux.getEuro());
                        }
                    } catch (Exception ex) {
                        ex.getCause();
                    }

                    /* obtener indicador semanal (93, 95, 97, diesel) */
                    try {
                        /* grafico de combustibles de 365 elementos */
                        Collection<IndicadorEconomicoSemanalBean> combList = ieDAO.getIES365Days();
                        request.setAttribute("combList", combList);

                        /* grafico de 14 elementos */
                        Collection<IndicadorEconomicoSemanalBean> iesList = ieDAO.getIES14();
                        request.setAttribute("iesList", iesList);

                        for (IndicadorEconomicoSemanalBean aux : iesList) {
                            /* valor b93 actual */
                            request.setAttribute("b93Actual", aux.getBencina93());
                            /* valor b95 actual */
                            request.setAttribute("b95Actual", aux.getBencina95());
                            /* valor b97 actual */
                            request.setAttribute("b97Actual", aux.getBencina97());
                            /* valor de diesel */
                            request.setAttribute("dieselActual", aux.getDiesel());
                        }
                    } catch (Exception ex) {
                        ex.getCause();
                    }

                    /* obtener indicador mensual (UTM, IPC) */
                    try {
                        /* grafico de 12 elementos */
                        Collection<IndicadorEconomicoMensualBean> iemList = ieDAO.getIEM12();
                        request.setAttribute("iemList", iemList);

                        for (IndicadorEconomicoMensualBean aux : iemList) {
                            /* valor utm actual */
                            request.setAttribute("utmActual", aux.getUtm());
                            /* valopr ipc actual */
                            request.setAttribute("ipcActual", aux.getIpc());
                        }
                    } catch (Exception ex) {
                        ex.getCause();
                    }

                    /* obtener ipc anual */
                    try {
                        Collection<IndicadorEconomicoMensualBean> ipcList = ieDAO.getIPC12();
                        request.setAttribute("ipcList", ipcList);
                    } catch (Exception ex) {
                        ex.getCause();
                    }
                    
                    /* marcar pestaña de menu */
                    request.setAttribute("indGrafActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/indicadorEconomico/indicadorEconomico.jsp").forward(request, response);
                }
            } catch (Exception sesionException) {
                /* enviar a la vista de login */
                System.err.println("no ha iniciado session");
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
