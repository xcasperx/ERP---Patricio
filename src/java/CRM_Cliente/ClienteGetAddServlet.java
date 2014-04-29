/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_Cliente;

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
@WebServlet(name = "ClienteGetAddServlet", urlPatterns = {"/ClienteGetAddServlet"})
public class ClienteGetAddServlet extends HttpServlet {

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
                    String rut = (String) session.getAttribute("rut");
                    String dv = (String) session.getAttribute("dv");
                    String nombre = (String) session.getAttribute("nombre");
                    String apellido = (String) session.getAttribute("apellido");
                    String giro = (String) session.getAttribute("giro");
                    String fecNac = (String) session.getAttribute("fecNac");
                    String direccion = (String) session.getAttribute("direccion");
                    String idCiudad = (String) session.getAttribute("idCiudad");
                    String telFijo = (String) session.getAttribute("telFijo");
                    String telMovil = (String) session.getAttribute("telMovil");
                    String email = (String) session.getAttribute("email");

                    /* obtener mensajes de session */
                    String msgErrorRut = (String) session.getAttribute("msgErrorRut");
                    String msgErrorNombre = (String) session.getAttribute("msgErrorNombre");
                    String msgErrorApellido = (String) session.getAttribute("msgErrorApellido");
                    String msgErrorGiro = (String) session.getAttribute("msgErrorGiro");
                    String msgErrorDireccion = (String) session.getAttribute("msgErrorDireccion");
                    String msgErrorFecNac = (String) session.getAttribute("msgErrorFecNac");
                    String msgErrorTelFijo = (String) session.getAttribute("msgErrorTelFijo");
                    String msgErrorTelMovil = (String) session.getAttribute("msgErrorTelMovil");
                    String msgErrorEmail = (String) session.getAttribute("msgErrorEmail");
                    String msgErrorAdd = (String) session.getAttribute("msgErrorAdd");
                    String msgOk = (String) session.getAttribute("msgOk");

                    /* limpiar variables de sesion */
                    session.setAttribute("redirectAdd", null);
                    session.setAttribute("rut", null);
                    session.setAttribute("dv", null);
                    session.setAttribute("nombre", null);
                    session.setAttribute("apellido", null);
                    session.setAttribute("giro", null);
                    session.setAttribute("fecNac", null);
                    session.setAttribute("direccion", null);
                    session.setAttribute("idCiudad", null);
                    session.setAttribute("telFijo", null);
                    session.setAttribute("telMovil", null);
                    session.setAttribute("msgErrorNombre", null);
                    session.setAttribute("msgErrorApellido", null);
                    session.setAttribute("msgErrorGiro", null);
                    session.setAttribute("msgErrorDireccion", null);
                    session.setAttribute("msgErrorFecNac", null);
                    session.setAttribute("msgErrorTelFijo", null);
                    session.setAttribute("msgErrorTelMovil", null);
                    session.setAttribute("msgErrorEmail", null);
                    session.setAttribute("msgErrorAdd", null);
                    session.setAttribute("msgOk", null);

                    /* instanciar lista de mensajes */
                    Collection<Message> msgList = new ArrayList<Message>();

                    /* comprobar redirect */
                    if (redirect == null || redirect.trim().equals("")) {
                        request.setAttribute("msg", "Ingrese un nuevo cliente.");

                    } else if (redirect.equals("cliente")) {
                        /* establecer atributos de session */
                        request.setAttribute("rut", rut);
                        request.setAttribute("dv", dv);
                        request.setAttribute("nombre", nombre);
                        request.setAttribute("apellido", apellido);
                        request.setAttribute("giro", giro);
                        request.setAttribute("fecNac", fecNac);
                        request.setAttribute("direccion", direccion);
                        request.setAttribute("idCiudad", Integer.parseInt(idCiudad));
                        request.setAttribute("telFijo", telFijo);
                        request.setAttribute("telMovil", telMovil);
                        request.setAttribute("email", email);                        

                        /* comprobar rut */
                        if (msgErrorRut == null || msgErrorRut.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorRut", true);
                            msgList.add(MessageList.addMessage(msgErrorRut));
                        }

                        /* comprobar nombre */
                        if (msgErrorNombre == null || msgErrorNombre.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorNombre", true);
                            msgList.add(MessageList.addMessage(msgErrorNombre));
                        }

                        /* comprobar apellido */
                        if (msgErrorApellido == null || msgErrorApellido.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorApellido", true);
                            msgList.add(MessageList.addMessage(msgErrorApellido));
                        }

                        /* comprobar giro */
                        if (msgErrorGiro == null || msgErrorGiro.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorGiro", true);
                            msgList.add(MessageList.addMessage(msgErrorGiro));
                        }

                        /* comprobar direccion */
                        if (msgErrorDireccion == null || msgErrorDireccion.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorDireccion", true);
                            msgList.add(MessageList.addMessage(msgErrorDireccion));
                        }

                        /* comprobar fecha de nacimiento */
                        if (msgErrorFecNac == null || msgErrorFecNac.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorFecNac", true);
                            msgList.add(MessageList.addMessage(msgErrorFecNac));
                        }

                        /* comprobar telefono fijo */
                        if (msgErrorTelFijo == null || msgErrorTelFijo.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorTelFijo", true);
                            msgList.add(MessageList.addMessage(msgErrorTelFijo));
                        }

                        /* comprobar telefono movil */
                        if (msgErrorTelMovil == null || msgErrorTelMovil.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorTelMovil", true);
                            msgList.add(MessageList.addMessage(msgErrorTelMovil));
                        }

                        /* comprobar email */
                        if (msgErrorEmail == null || msgErrorEmail.trim().equals("")) {
                        } else {
                            request.setAttribute("msgErrorEmail", true);
                            msgList.add(MessageList.addMessage(msgErrorEmail));
                        }

                        /* comprobar add */
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
                    request.setAttribute("clienteActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/GRL_persona/personaAdd.jsp").forward(request, response);
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
