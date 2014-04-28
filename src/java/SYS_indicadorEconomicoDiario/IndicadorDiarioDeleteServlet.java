/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoDiario;

import Helpers.Message;
import Helpers.MessageList;
import SYS_indicadorEconomicoEstadistico.IndicadorEconomicoDAO;
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
@WebServlet(name = "IndicadorDiarioDeleteServlet", urlPatterns = {"/IndicadorDiarioDeleteServlet"})
public class IndicadorDiarioDeleteServlet extends HttpServlet {

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

            IndicadorDiarioDAO idDAO = new IndicadorDiarioDAO();
            idDAO.setConexion(conexion);

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

                if (userTypeX > 4) {
                    /* acceso prohibido */
                    request.getRequestDispatcher("/ForbiddenServlet").forward(request, response);
                } else {
                    /* establecer variables de session a jsp */
                    request.setAttribute("idUserX", idUserX);
                    request.setAttribute("usernameX", usernameX);

                    ////////////////////////////////////
                    // RECIBIR Y COMPROBAR PARAMETROS
                    ////////////////////////////////////

                    String btnDelRow = request.getParameter("btnDelRow");
                    String btnDelCol = request.getParameter("btnDelCol");

                    /* establecer atributos de session */
                    session.setAttribute("redirectDel", "indicadorDiario");

                    //////////////////////////
                    // ELIMINAR POR REGISTRO
                    //////////////////////////

                    if (btnDelRow != null) {
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            idDAO.indicadorDiarioDelete(id);
                            session.setAttribute("msgDel", "Un registro ha sido eliminado.");
                        } catch (Exception ex) {
                            ex.getCause();
                            session.setAttribute("msgErrorConstraint", "Hubo un problema y no puede ejecutarse la instrucción. Error:" + ex.getLocalizedMessage());
                        }
                    }

                    ///////////////////////////////
                    // ELIMINAR VARIOS REGISTROS
                    ///////////////////////////////                    

                    if (btnDelCol != null) {                                                
                        /* instanciar lista de mensajes */
                        Collection<Message> msgList = new ArrayList<Message>();

                        int i = 0;
                        int cont = 0;
                        String[] outerArray = request.getParameterValues("chk");
                                                
                        try {
                            while (outerArray[i] != null) {
                                try {
                                    idDAO.indicadorDiarioDelete(Integer.parseInt(outerArray[i]));
                                    cont++;
                                } catch (Exception ex) {
                                    msgList.add(MessageList.addMessage("No se pudo eliminar el registro con ID: " + outerArray[i]));
                                    ex.getCause();
                                }
                                i++;
                            }
                        } catch (Exception ex) {
                        }

                        if (cont == 1) {
                            session.setAttribute("msgDel", "Un registro ha sido eliminado.");
                        } else if (cont > 1) {
                            session.setAttribute("msgDel", cont + " registros han sido eliminados.");
                        }

                        /* establecer lista de mensajes */
                        if (!msgList.isEmpty()) {
                            session.setAttribute("msgListErrorConstraint", msgList);
                        }
                    }

                    /* send redirect */
                    response.sendRedirect("IndicadorDiarioMainServlet");
                }
            } catch (Exception sessionException) {
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
