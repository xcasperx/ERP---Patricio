/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoDiario;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author patricio
 */
public class IndicadorDiarioDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<IndicadorEconomicoDiarioBean> indicadorDiarioGetAll() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoDiarioBean> list = new ArrayList<IndicadorEconomicoDiarioBean>();

        try {
            String sql = "select * from indicador_diario indi, user us where indi.id_user = us.id_user order by id_indicador desc ";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                IndicadorEconomicoDiarioBean reg = new IndicadorEconomicoDiarioBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setUf(result.getFloat("uf"));
                reg.setDolar(result.getFloat("usd"));
                reg.setEuro(result.getFloat("eur"));
                reg.setPublicTime(result.getString("create_time"));
                reg.setUpdateTime(result.getString("update_time"));
                reg.setUsername(result.getString("username"));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, indicadorDiarioGetAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioGetAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioGetAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioGetAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioGetAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, indicadorDiarioGetAll() : " + ex);
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

    public IndicadorEconomicoDiarioBean findByIdDiario(int id) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        IndicadorEconomicoDiarioBean reg = null;

        try {
            String sql = "select * from indicador_diario where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                reg = new IndicadorEconomicoDiarioBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setUf(result.getFloat("uf"));
                reg.setDolar(result.getFloat("usd"));
                reg.setEuro(result.getFloat("eur"));
                reg.setPublicTime(result.getString("create_time"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, findByIdDiario() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, findByIdDiario() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, findByIdDiario() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, findByIdDiario() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, findByIdDiario() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, findByIdDiario() : " + ex);
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

    public String indicadorDiarioGetLastDate() throws ParseException {

        PreparedStatement sentence = null;
        ResultSet result = null;

        String dateDup = null;

        try {
            String sql = "select MAX(id_indicador), create_time from indicador_diario group by id_indicador";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                dateDup = result.getString("create_time");
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, indicadorDiarioGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioGetLastDate() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioGetLastDate() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioGetLastDate() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, indicadorDiarioGetLastDate() : " + ex);
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

    public void indicadorDiarioInsert(IndicadorEconomicoDiarioBean indicador) {

        PreparedStatement sentence = null;

        try {
            String sql = "insert into indicador_diario (uf, eur, usd, id_user) values (?, ?, ?, ?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getUf());
            sentence.setFloat(2, indicador.getEuro());
            sentence.setFloat(3, indicador.getDolar());
            sentence.setInt(4, indicador.getIdUser());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioInsert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioInsert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioInsert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioInsert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioInsert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioInsert() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void indicadorDiarioUpdate(IndicadorEconomicoDiarioBean indicador) {

        PreparedStatement sentence = null;

        try {
            String sql = "update indicador_diario set uf = ?, eur = ?, usd = ?, id_user = ?, create_time = ? where id_indicador = ? ";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getUf());
            sentence.setFloat(2, indicador.getEuro());
            sentence.setFloat(3, indicador.getDolar());
            sentence.setInt(4, indicador.getIdUser());
            sentence.setString(5, indicador.getPublicTime());
            sentence.setInt(6, indicador.getId());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioUpdate : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioUpdate : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioUpdate : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioUpdate : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioUpdate : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioUpdate : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void indicadorDiarioDelete(int id) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from indicador_diario where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioDelete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioDelete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioDelete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioDelete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioDelete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioDelete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
