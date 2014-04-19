/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

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
 * @author patricio alberto
 */
@WebServlet(name = "AdminAddServlet", urlPatterns = {"/AdminAddServlet"})
public class AdminAddServlet extends HttpServlet {

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

            AdminDAO adminDAO = new AdminDAO();
            adminDAO.setConexion(conexion);

            ///////////////////////
            // COMPROBAR SESSION
            ///////////////////////
            try {
                /* recuperar sesion */
                HttpSession session = request.getSession(false);

                /* obtener parametros de session */
                int idAdmin = Integer.parseInt((String) session.getAttribute("idAdmin"));
                int access = Integer.parseInt((String) session.getAttribute("access"));
                String user = (String) session.getAttribute("admin");

                /* comprobar permisos de usuario */
                if (access != 777) {
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {
                    // SUPERUSUARIO

                    /* obtener los valores de session y asignar valores a la jsp */
                    request.setAttribute("userJsp", user);
                    request.setAttribute("access", access);

                    ////////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETROS
                    ////////////////////////////////////

                    /* obtener parametros de la vista */
                    String username = request.getParameter("username");
                    String email = request.getParameter("email");
                    String type = request.getParameter("type_admin");
                    String pwd1 = request.getParameter("pwd1");
                    String pwd2 = request.getParameter("pwd2");

                    /* establecer variables de sesion */
                    session.setAttribute("redirectAdd", "admin");
                    session.setAttribute("username", username);
                    session.setAttribute("email", email);
                    session.setAttribute("type", type);

                    /* instanciar admin */
                    Admin admin = new Admin();

                    /* flag de error */
                    boolean error = false;

                    /* comprobar username */
                    if (username == null || username.trim().equals("")) {
                        session.setAttribute("msgErrorUsername", "Debe ingresar username.");
                        error = true;
                    } else {
                        /* comprobar username duplicado */
                        admin.setUsername(username);
                        try {
                            boolean find = adminDAO.validateDuplicateUsername(admin);
                            if (find) {
                                session.setAttribute("msgErrorUsername", "El username ingresado ya se encuentra registrado.");
                                error = true;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            error = true;
                        }
                    }
                    /* comprobar email */
                    if (email == null || email.trim().equals("")) {
                        session.setAttribute("msgErrorEmail", "Debe ingresar Email.");
                        error = true;
                    } else {
                        /* comprobar email duplicado */
                        admin.setEmail(email);
                        try {
                            boolean find = adminDAO.validateDuplicateEmail(admin);
                            if (find) {
                                session.setAttribute("msgErrorEmail", "El Email ingresado ya se encuentra registrado.");
                                error = true;
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            error = true;
                        }
                    }

                    /* comprobar type */
                    if (type == null || type.trim().equals("")) {
                        error = true;
                    } else {
                        admin.setTypeAdmin(Integer.parseInt(type));
                    }
                    /* comprobar pwd1 */
                    if (pwd1 == null || pwd1.trim().equals("")) {
                        session.setAttribute("msgErrorPwd1", "Debe ingresar contraseña.");
                        error = true;
                    } else {
                        admin.setPwd1(pwd1);
                        /* comprobar pwd2 */
                        if (pwd2 == null || pwd2.trim().equals("")) {
                            session.setAttribute("msgErrorPwd1", "Debe ingresar contraseña.");
                            error = true;
                        } else {
                            admin.setPwd2(pwd2);
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
                                admin.setPassword(StringMD.getStringMessageDigest(pwd1, StringMD.MD5));
                            }
                        }
                    }

                    //////////////////////
                    // LOGICA DE NEGOCIO
                    //////////////////////
                    if (!error) {
                        try {
                            /* insertar registro */
                            adminDAO.insert(admin);
                            session.setAttribute("msgOk", "Registro ingresado exitosamente.");
                        } catch (Exception duplicateException) {
                            session.setAttribute("msgErrorDup", "Registro duplicado, verifique campos ingresados.");
                        }
                    }

                    /* send redirect */
                    response.sendRedirect("AdminGetAddServlet");
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
