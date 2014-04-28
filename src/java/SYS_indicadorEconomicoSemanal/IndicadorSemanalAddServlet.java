/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoSemanal;

import Helpers.Format;
import SYS_indicadorEconomicoEstadistico.IndicadorEconomicoDAO;
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
@WebServlet(name = "IndicadorSemanalAddServlet", urlPatterns = {"/IndicadorSemanalAddServlet"})
public class IndicadorSemanalAddServlet extends HttpServlet {

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

                    String suf = request.getParameter("uf");
                    String seuro = request.getParameter("euro");
                    String sdolar = request.getParameter("dolar");

                    String bencina93 = request.getParameter("bencina93");
                    String bencina95 = request.getParameter("bencina95");
                    String bencina97 = request.getParameter("bencina97");
                    String diesel = request.getParameter("diesel");

                    /* establecer variables de sesion */
                    session.setAttribute("redirectAdd", "indicadorSemanal");
                    session.setAttribute("bencina93", bencina93);
                    session.setAttribute("bencina95", bencina95);
                    session.setAttribute("bencina97", bencina97);
                    session.setAttribute("diesel", diesel);

                    /* instanciar indicador */
                    IndicadorEconomicoSemanalBean indicador = new IndicadorEconomicoSemanalBean();

                    /* flag de error */
                    boolean error = false;

                    /* comprobar bencina 93 */
                    if (bencina93 == null || bencina93.trim().equals("")) {
                        session.setAttribute("msgErrorBencina93", "Debe ingresar el precio de la bencina 93.");
                        error = true;
                    } else {
                        try {
                            indicador.setBencina93(Float.parseFloat(bencina93));
                        } catch (NumberFormatException n) {
                            session.setAttribute("msgErrorBencina93", "El precio de la bencina 93 debe ser numérico.");
                            error = true;
                        }
                    }

                    /* comprobar bencina 95 */
                    if (bencina95 == null || bencina95.trim().equals("")) {
                        session.setAttribute("msgErrorBencina95", "Debe ingresar el precio de la bencina 95.");
                        error = true;
                    } else {
                        try {
                            indicador.setBencina95(Float.parseFloat(bencina95));
                        } catch (NumberFormatException n) {
                            session.setAttribute("msgErrorBencina95", "El precio de la bencina 95 debe ser numérico.");
                            error = true;
                        }
                    }

                    /* comprobar bencina 97 */
                    if (bencina97 == null || bencina97.trim().equals("")) {
                        session.setAttribute("msgErrorBencina97", "Debe ingresar el precio de la bencina 97.");
                        error = true;
                    } else {
                        try {
                            indicador.setBencina97(Float.parseFloat(bencina97));
                        } catch (NumberFormatException n) {
                            session.setAttribute("msgErrorBencina97", "El precio de la bencina 97 debe ser numérico.");
                            error = true;
                        }
                    }

                    /* comprobar diesel */
                    if (diesel == null || diesel.trim().equals("")) {
                        session.setAttribute("msgErrorDiesel", "Debe ingresar el precio del Diesel.");
                        error = true;
                    } else {
                        try {
                            indicador.setDiesel(Float.parseFloat(diesel));
                        } catch (NumberFormatException n) {
                            session.setAttribute("msgErrorDiesel", "El precio del Diesel debe ser numérico.");
                            error = true;
                        }
                    }

                    ///////////////////////
                    // LOGICA DE NEGOCIO
                    ///////////////////////

                    /* comprobar fecha duplicada - Atencion: un indicador no puede ser ingresado dos veces el mismo dia */
                    try {
                        String dateDup = isDAO.indicadorSemanalGetLastDate();

                        /* comparar ultima fecha con fecha actual */
                        if (Format.currentDateYYMMDDDD().equals(Format.dateYYYYMMDD(dateDup))) {
                            session.setAttribute("msgErrorAdd", "El indicador para hoy ya fue ingresado.");
                            error = true;
                        }
                    } catch (Exception ex) {
                        session.setAttribute("msgErrorAdd", "Ha ocurrido un problema y no puede comprobar las fechas. Error:" + ex.getLocalizedMessage());
                        error = true;
                        ex.getCause();
                    }

                    /* actualizar registro */
                    if (!error) {
                        try {
                            isDAO.indicadorSemanalInsert(indicador);
                            session.setAttribute("msgOk", "Registro ingresado exitosamente.");
                        } catch (Exception ex) {
                            session.setAttribute("msgErrorAdd", "Ha ocurrido un problema y no puede ingresar el nuevo registro. Error:" + ex.getLocalizedMessage());
                            ex.getCause();
                        }
                    }

                    /* send redirect */
                    response.sendRedirect("/ERP/IndicadorSemanalGetAddServlet");
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
