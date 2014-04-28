/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_user;

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
@WebServlet(name = "UserGetServlet", urlPatterns = {"/UserGetServlet"})
public class UserGetServlet extends HttpServlet {

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

            UserDAO userDAO = new UserDAO();
            userDAO.setConexion(conexion);

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
                    String username = (String) session.getAttribute("username");
                    String email = (String) session.getAttribute("email");
                    String userType = (String) session.getAttribute("userType");

                    /* obtener mensajes de session */
                    String msgErrorUsername = (String) session.getAttribute("msgErrorUsername");
                    String msgErrorEmail = (String) session.getAttribute("msgErrorEmail");
                    String msgErrorPwd1 = (String) session.getAttribute("msgErrorPwd1");
                    String msgErrorPwd2 = (String) session.getAttribute("msgErrorPwd2");
                    String msgErrorUpdate = (String) session.getAttribute("msgErrorUpdate");
                    String msgOk = (String) session.getAttribute("msgOk");

                    /* limpiar variables de sesion */
                    session.setAttribute("redirectUpdate", null);
                    session.setAttribute("username", null);
                    session.setAttribute("email", null);
                    session.setAttribute("userType", null);
                    session.setAttribute("msgErrorUsername", null);
                    session.setAttribute("msgErrorEmail", null);
                    session.setAttribute("msgErrorPwd1", null);
                    session.setAttribute("msgErrorPwd2", null);
                    session.setAttribute("msgErrorUpdate", null);
                    session.setAttribute("msgOk", null);

                    /* instanciar lista de mensajes */
                    Collection<Message> msgList = new ArrayList<Message>();

                    /* obtener user por id */
                    try {
                        UserBean reg = userDAO.findById(Integer.parseInt(sid));
                        if (reg != null) {
                            /* establecer atributos de reg */
                            request.setAttribute("id", reg.getIdUser());

                            /* comprobar redirect */
                            if (redirect == null || redirect.trim().equals("")) {
                                /* establecer atributos de reg */
                                request.setAttribute("msg", "El registro ha sido encontrado!");
                                request.setAttribute("username", reg.getUsername());
                                request.setAttribute("email", reg.getEmail());
                                request.setAttribute("userType", reg.getUserType());

                            } else if (redirect.equals("user")) {
                                /* establecer atributos de session */
                                request.setAttribute("username", username);
                                request.setAttribute("email", email);
                                try {
                                    request.setAttribute("userType", Integer.parseInt(userType));
                                } catch (NumberFormatException n) {
                                }

                                /* mensaje de error por username */
                                if (msgErrorUsername == null || msgErrorUsername.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorUsername", true);
                                    msgList.add(MessageList.addMessage(msgErrorUsername));
                                }

                                /* mensaje de error por email */
                                if (msgErrorEmail == null || msgErrorEmail.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorEmail", true);
                                    msgList.add(MessageList.addMessage(msgErrorEmail));
                                }

                                /* mensaje de error por pwd1 */
                                if (msgErrorPwd1 == null || msgErrorPwd1.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorPwd1", true);
                                    msgList.add(MessageList.addMessage(msgErrorPwd1));
                                }

                                /* mensaje de error pwd2 */
                                if (msgErrorPwd2 == null || msgErrorPwd2.trim().equals("")) {
                                } else {
                                    request.setAttribute("msgErrorPwd2", true);
                                    msgList.add(MessageList.addMessage(msgErrorPwd2));
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
                            msgList.add(MessageList.addMessage("No se encontró el registro."));
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

                    /* marcar item de menu */
                    request.setAttribute("userActive", "active");

                    /* despachar a la vista */
                    request.getRequestDispatcher("/user/userUpdate.jsp").forward(request, response);
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
