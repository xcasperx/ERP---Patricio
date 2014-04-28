/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoMensual;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import SYS_indicadorEconomicoDiario.IndicadorEconomicoDiarioBean;
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
public class IndicadorMensualDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<IndicadorEconomicoMensualBean> indicadorMensualGetAll() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoMensualBean> list = new ArrayList<IndicadorEconomicoMensualBean>();

        try {
            String sql = "select * from indicador_mensual inme, user us where inme.id_user = us.id_user order by id_indicador desc";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                IndicadorEconomicoMensualBean reg = new IndicadorEconomicoMensualBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setIpc(result.getFloat("ipc"));
                reg.setUtm(result.getFloat("utm"));
                reg.setPublicTime(result.getString("create_time"));
                reg.setUpdateTime(result.getString("update_time"));
                reg.setIdUser(result.getInt("id_user"));
                reg.setUsername(result.getString("username"));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, indicadorMensualGetAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualGetAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualGetAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualGetAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualGetAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, indicadorMensualGetAll() : " + ex);
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

    public IndicadorEconomicoMensualBean findByIdMensual(int id) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        IndicadorEconomicoMensualBean reg = null;

        try {
            String sql = "select * from indicador_mensual where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                reg = new IndicadorEconomicoMensualBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setIpc(result.getFloat("ipc"));
                reg.setUtm(result.getFloat("utm"));
                reg.setPublicTime(result.getString("create_time"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, findByIdMensual() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, findByIdMensual() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, findByIdMensual() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, findByIdMensual() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, findByIdMensual() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, findByIdMensual() : " + ex);
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

    public void indicadorMensualUpdate(IndicadorEconomicoMensualBean indicador) {

        PreparedStatement sentence = null;

        try {
            String sql = "update indicador_mensual set ipc = ?, utm = ?, create_time = ?, id_user = ? where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getIpc());
            sentence.setFloat(2, indicador.getUtm());
            sentence.setString(3, indicador.getPublicTime());
            sentence.setInt(4, indicador.getIdUser());
            sentence.setInt(5, indicador.getId());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualUpdate : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualUpdate : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualUpdate : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualUpdate : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualUpdate : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualUpdate : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public String indicadorMensualGetLastDate() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        String dateDup = null;

        try {
            String sql = "select MAX(id_indicador), create_time from indicador_mensual group by id_indicador";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                dateDup = result.getString("create_time");
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
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

    public void indicadorMensualInsert(IndicadorEconomicoMensualBean indicador) {

        PreparedStatement sentence = null;

        try {
            String sql = "insert into indicador_mensual (ipc, utm) values (?, ?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getIpc());
            sentence.setFloat(2, indicador.getUtm());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualGetLastDate() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void indicadorMensualDelete(int id) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from indicador_mensual where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, id);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualDelete : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorMensualDelete : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualDelete : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorMensualDelete : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualDelete : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorMensualDelete : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
