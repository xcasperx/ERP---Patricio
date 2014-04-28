/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoSemanal;

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
public class IndicadorSemanalDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<IndicadorEconomicoSemanalBean> indicadorSemanalGetAll() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoSemanalBean> list = new ArrayList<IndicadorEconomicoSemanalBean>();

        try {
            String sql = "select * from indicador_semanal inse, user us where inse.id_user = us.id_user order by id_indicador desc";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                IndicadorEconomicoSemanalBean reg = new IndicadorEconomicoSemanalBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setBencina93(result.getFloat("bencina93"));
                reg.setBencina95(result.getFloat("bencina95"));
                reg.setBencina97(result.getFloat("bencina97"));
                reg.setDiesel(result.getFloat("diesel"));
                reg.setPublicTime(result.getString("create_time"));
                reg.setUpdateTime(result.getString("update_time"));
                reg.setIdUser(result.getInt("id_user"));
                reg.setUsername(result.getString("username"));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, indicadorSemanalGetAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalGetAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalGetAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalGetAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalGetAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, indicadorSemanalGetAll() : " + ex);
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

    public IndicadorEconomicoSemanalBean findByIdSemanal(int id) {


        PreparedStatement sentence = null;
        ResultSet result = null;

        IndicadorEconomicoSemanalBean reg = null;

        try {
            String sql = "select * from indicador_semanal where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                reg = new IndicadorEconomicoSemanalBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setBencina93(result.getFloat("bencina93"));
                reg.setBencina95(result.getFloat("bencina95"));
                reg.setBencina97(result.getFloat("bencina97"));
                reg.setDiesel(result.getFloat("diesel"));
                reg.setPublicTime(result.getString("create_time"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, findByIdSemanal() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, findByIdSemanal() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, findByIdSemanal() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, findByIdSemanal() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, findByIdSemanal() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, findByIdSemanal() : " + ex);
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
        return reg;
    }

    public String indicadorSemanalGetLastDate() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        String dateDup = null;

        try {
            String sql = "select MAX(id_indicador), create_time from indicador_semanal group by id_indicador";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                dateDup = result.getString("create_time");
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, indicadorSemanalGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalGetLastDate() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalGetLastDate() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, indicadorSemanalGetLastDate() : " + ex);
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
        return dateDup;
    }

    public void indicadorSemanalUpdate(IndicadorEconomicoSemanalBean indicador) {


        PreparedStatement sentence = null;

        try {
            String sql = "update indicador_semanal set bencina93 = ?, bencina95 = ?, bencina97 = ?, diesel = ?, create_time = ?, id_user = ? where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getBencina93());
            sentence.setFloat(2, indicador.getBencina95());
            sentence.setFloat(3, indicador.getBencina97());
            sentence.setFloat(4, indicador.getDiesel());
            sentence.setString(5, indicador.getPublicTime());
            sentence.setInt(6, indicador.getIdUser());
            sentence.setInt(7, indicador.getId());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalUpdate : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalUpdate : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalUpdate : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalUpdate : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalUpdate : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalUpdate : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void indicadorSemanalInsert(IndicadorEconomicoSemanalBean indicador) {

        PreparedStatement sentence = null;

        try {
            String sql = "insert into indicador_semanal (bencina93, bencina95, bencina97, diesel) values (?, ?, ?, ?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getBencina93());
            sentence.setFloat(2, indicador.getBencina95());
            sentence.setFloat(3, indicador.getBencina97());
            sentence.setFloat(4, indicador.getDiesel());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalInsert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalInsert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalInsert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalInsert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalInsert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalInsert() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void indicadorSemanalDelete(int id) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from indicador_semanal where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalDelete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorSemanalDelete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalDelete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorSemanalDelete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalDelete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorSemanalDelete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
