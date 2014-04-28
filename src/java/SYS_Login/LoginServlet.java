package SYS_Login;

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
import SYS_user.UserBean;
import SYS_user.UserDAO;

/**
 *
 * @author patricio alberto
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

            UserDAO userDAO = new UserDAO();
            userDAO.setConexion(conexion);

            ///////////////////////////////////
            // RECIBIR Y COMPROBAR PARAMETROS
            ///////////////////////////////////

            String btnLogin = request.getParameter("btnLogin");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (btnLogin == null) {
                /* mostrar login */
                request.getRequestDispatcher("/login/login.jsp").forward(request, response);
            } else {
                /* encriptar password */
                String pwdCrypted = StringMD.getStringMessageDigest(password, StringMD.MD5);

                /* comprobar si existe usuario */
                try {
                    UserBean user = userDAO.findByUserPass(username, pwdCrypted);

                    if (user != null) {
                        /* crear session */
                        HttpSession session = request.getSession(true);

                        /* asignar 10000 segundos de expiracion ante inactividad */
                        session.setMaxInactiveInterval(10000);

                        /* asignar valores a session */
                        session.setAttribute("idUserX", "" + user.getIdUser());
                        session.setAttribute("userTypeX", "" + user.getUserType());                        
                        session.setAttribute("usernameX", user.getUsername());
                                               
                        System.out.println(user.getUsername());

                        /* send redirect */
                        response.sendRedirect("UserMainServlet");
                    } else {
                        request.setAttribute("msgErrorLogin", "Error al ingresar nombre de usuario o contraseña.");
                        request.getRequestDispatcher("/login/login.jsp").forward(request, response);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    request.setAttribute("msgErrorLogin", "Error de conexión, intente nuevamente.");
                    request.getRequestDispatcher("/login/login.jsp").forward(request, response);
                }
            }
        } catch (Exception connectionException) {
            request.setAttribute("msgErrorLogin", "Error de conexión, intente nuevamente.");
            request.getRequestDispatcher("/login/login.jsp").forward(request, response);
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
