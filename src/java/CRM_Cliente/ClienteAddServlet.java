/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_Cliente;

import GRL_persona.PersonaBean;
import GRL_persona.PersonaDAO;
import Helpers.Format;
import SYS_indicadorEconomicoDiario.IndicadorDiarioDAO;
import SYS_indicadorEconomicoDiario.IndicadorEconomicoDiarioBean;
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
@WebServlet(name = "ClienteAddServlet", urlPatterns = {"/ClienteAddServlet"})
public class ClienteAddServlet extends HttpServlet {

    @Resource(name = "jdbc/ERP-cliente")
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

            PersonaDAO personaDAO = new PersonaDAO();
            personaDAO.setConexion(conexion);

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

                    String stipoPersona = request.getParameter("tipoPersona");
                    String scalidadPersona = request.getParameter("radio-inline");
                    String srut = request.getParameter("rut"); // rut+dv
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String giro = request.getParameter("giro");
                    String fecNac = request.getParameter("fecNac");
                    String direccion = request.getParameter("direccion");
                    String idCiudad = request.getParameter("idCiudad");
                    String telFijo = request.getParameter("telFijo");
                    String telMovil = request.getParameter("telMovil");
                    String email = request.getParameter("email");

                    /* establecer variables de sesion */
                    session.setAttribute("redirectAdd", "cliente");
                    session.setAttribute("calidadPersona", scalidadPersona);
                    session.setAttribute("rut", srut);
                    session.setAttribute("nombre", nombre);
                    session.setAttribute("apellido", apellido);
                    session.setAttribute("giro", giro);
                    session.setAttribute("fecNac", fecNac);
                    session.setAttribute("direccion", direccion);
                    session.setAttribute("idCiudad", idCiudad);
                    session.setAttribute("telFijo", telFijo);
                    session.setAttribute("telMovil", telMovil);
                    session.setAttribute("email", email);

                    /* instanciar persona */
                    PersonaBean persona = new PersonaBean();

                    /* flag de error */
                    boolean error = false;

                    ////////////////////////////////
                    // COMPROBACIONES OBLIGATORIAS
                    ////////////////////////////////

                    /* comprobar rut */
                    if (srut == null || srut.trim().equals("")) {
                        session.setAttribute("msgErrorRut", "Debe ingresar RUT.");
                        error = true;
                    } else {
                    }

                    /* comprobar nombre */
                    if (nombre == null || nombre.trim().equals("")) {
                        session.setAttribute("msgErrorNombre", "Debe ingresar nombre.");
                        error = true;
                    } else {
                    }

                    /* comprobar direccion */
                    if (direccion == null || direccion.trim().equals("")) {
                        session.setAttribute("msgErrorDireccion", "Debe ingresar dirección.");
                        error = true;
                    } else {
                    }

                    int calidadPersona = Integer.parseInt(scalidadPersona);
                    if (calidadPersona == 2) {
                        /* comprobar apellido */
                        if (apellido == null || apellido.trim().equals("")) {
                            session.setAttribute("msgErrorApellido", "Debe ingresar apellido.");
                            error = true;
                        } else {
                            persona.setApellido(apellido);
                        }
                    }

                    ///////////////////////
                    // LOGICA DE NEGOCIO
                    ///////////////////////                                        

                    int tipoPersona = Integer.parseInt(stipoPersona);

                    /* insertar persona */
                    if (!error) {
                        try {
                            personaDAO.insert(tipoPersona);
                            session.setAttribute("msgOk", "Registro ingresado exitosamente.");
                        } catch (Exception ex) {
                            session.setAttribute("msgErrorAdd", "Ha ocurrido un problema y no puede ingresar el nuevo registro. Error:" + ex.getLocalizedMessage());
                            ex.getCause();
                        }
                    }

                    /* send redirect */
                    switch (tipoPersona) {
                        case 1:
                            response.sendRedirect("/ERP/ClienteGetAddServlet");
                            break;
                        case 2:
                            response.sendRedirect("/ERP/ProveedorGetAddServlet");
                            break;
                        case 3:
                            response.sendRedirect("/ERP/PersonalGetAddServlet");
                        default:
                            response.sendRedirect("/ERP/Error403");
                            break;
                    }                                        

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
