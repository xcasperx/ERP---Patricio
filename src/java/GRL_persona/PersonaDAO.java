/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GRL_persona;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author patricio
 */
public class PersonaDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<PersonaBean> getAll(int tipoPersona) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<PersonaBean> list = new ArrayList<PersonaBean>();                 

        try {
            String sql = "select * from persona pe, ciudad ci where pe.id_ciudad = ci.id_ciudad and tipo_persona = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, tipoPersona);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                PersonaBean reg = new PersonaBean();
                /* obtener resultset */
                reg.setRut(result.getInt("rut"));
                reg.setDv(result.getString("dv"));                
                reg.setNombre(result.getString("nombre"));
                reg.setApellido(result.getString("apellido"));
                reg.setGiro(result.getString("giro"));
                reg.setDireccion(result.getString("direccion"));
                reg.setIdCiudad(result.getInt("id_ciudad"));
                reg.setNombreCiudad(result.getString("nombre_ciudad"));
                reg.setFecNac(result.getString("fecha_nacimiento"));
                reg.setTelFijo(result.getInt("telefono"));
                //reg.setTelMovil(result.getInt("celular"));
                reg.setEmail(result.getString("email"));
                reg.setTipoPersona(result.getInt("tipo_persona"));
                reg.setCalidadPersona(result.getInt("calidad_persona"));
                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en PersonaDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en PersonaDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en PersonaDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en PersonaDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en PersonaDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada PersonaDAO, getAll() : " + ex);
        } finally {
            /* liberar los recursos */
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return list;
    }

    public void insert(int tipoPersona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
