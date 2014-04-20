/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Helpers.StringMD;
import java.io.IOException;
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
@WebServlet(name = "UserAddServlet", urlPatterns = {"/UserAddServlet"})
public class UserAddServlet extends HttpServlet {

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

                    /* obtener parametros de la vista */
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String userType = request.getParameter("userType");
                    String pwd1 = request.getParameter("pwd1");
                    String pwd2 = request.getParameter("pwd2");

                    /* establecer variables de sesion */
                    session.setAttribute("redirectAdd", "user");
                    session.setAttribute("username", username);
                    session.setAttribute("email", email);
                    session.setAttribute("userType", userType);

                    /* instanciar user */
                    UserBean user = new UserBean();

                    /* flag de error */
                    boolean error = false;

                    /* comprobar username */
                    if (username == null || username.trim().equals("")) {
                        session.setAttribute("msgErrorUsername", "Debe ingresar username.");
                        error = true;
                    } else {
                        user.setUsername(username);
                    }

                    /* comprobar email */
                    if (email == null || email.trim().equals("")) {
                        session.setAttribute("msgErrorEmail", "Debe ingresar Email.");
                        error = true;
                    } else {
                        user.setEmail(email);
                    }

                    /* comprobar type */
                    if (userType == null || userType.trim().equals("")) {
                        error = true;
                    } else {
                        user.setUserType(Integer.parseInt(userType));
                    }
                    /* comprobar pwd1 */
                    if (pwd1 == null || pwd1.trim().equals("")) {
                        session.setAttribute("msgErrorPwd1", "Debe ingresar contraseña.");
                        error = true;
                    } else {
                        user.setPwd1(pwd1);
                        /* comprobar pwd2 */
                        if (pwd2 == null || pwd2.trim().equals("")) {
                            session.setAttribute("msgErrorPwd1", "Debe ingresar contraseña.");
                            error = true;
                        } else {
                            user.setPwd2(pwd2);
                            /* comprobar coincidencias */
                            if (!pwd1.equals(pwd2)) {
                                session.setAttribute("msgErrorPwd1", "Las contraseñas no coinciden");
                                error = true;
                            }
                            /* comprobar largo de caracteres */
                            if (pwd1.length() < 6 || pwd2.length() < 6) {
                                session.setAttribute("msgErrorPwd2", "La contraseña debe contener al menos 6 caracteres.");
                                error = true;
                            }
                            /*encriptar password en hash MD5 */
                            if (!error) {
                                user.setPassword(StringMD.getStringMessageDigest(pwd1, StringMD.MD5));
                            }
                        }
                    }

                    //////////////////////
                    // LOGICA DE NEGOCIO
                    //////////////////////

                    /* comprobar username duplicado */
                    try {
                        boolean find = userDAO.validateDuplicateUsername(user);
                        if (find) {
                            session.setAttribute("msgErrorUsername", "El username ingresado ya se encuentra registrado.");
                            error = true;
                        }
                    } catch (Exception ex) {
                        ex.getCause();
                        error = true;
                    }

                    /* comprobar email duplicado */
                    try {
                        boolean find = userDAO.validateDuplicateEmail(user);
                        if (find) {
                            session.setAttribute("msgErrorEmail", "El Email ingresado ya se encuentra registrado.");
                            error = true;
                        }
                    } catch (Exception ex) {
                        ex.getCause();
                        error = true;
                    }

                     /* insertar registro */
                    if (!error) {
                        try {                           
                            userDAO.insert(user);
                            session.setAttribute("msgOk", "Registro ingresado exitosamente.");
                        } catch (Exception ex) {
                            session.setAttribute("msgErrorDup", "Registro duplicado, verifique campos ingresados.");
                        }
                    }

                    /* send redirect */
                    response.sendRedirect("UserGetAddServlet");
                }
            } catch (Exception sesionException) {                
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
