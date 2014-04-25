/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indicadorEconomico;

import Helpers.Format;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import user.UserBean;

/**
 *
 * @author patricio
 */
public class IndicadorEconomicoDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<IndicadorEconomicoDiarioBean> getIED14() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoDiarioBean> list = new ArrayList<IndicadorEconomicoDiarioBean>();

        try {
            String sql = "select * from indicador_diario order by id_indicador asc limit 14";

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

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIED14() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIED14() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIED14() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIED14() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIED14() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIED14() : " + ex);
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

    public Collection<IndicadorEconomicoSemanalBean> getIES14() {
        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoSemanalBean> list = new ArrayList<IndicadorEconomicoSemanalBean>();

        try {
            String sql = "select * from indicador_semanal order by id_indicador asc limit 14";

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

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIES14() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIES14() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIES14() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIES14() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIES14() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIES14() : " + ex);
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

    public Collection<IndicadorEconomicoSemanalBean> getIES365Days() throws ParseException {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoSemanalBean> list = new ArrayList<IndicadorEconomicoSemanalBean>();

        try {
            String sql = "select * from indicador_semanal order by id_indicador asc limit 52";

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
                reg.setPublicTime(Format.dateDD(result.getString("create_time")));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIES14() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIES14() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIES14() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIES14() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIES14() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIES14() : " + ex);
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

    public Collection<IndicadorEconomicoMensualBean> getIEM12() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoMensualBean> list = new ArrayList<IndicadorEconomicoMensualBean>();

        try {
            String sql = "select id_indicador, utm, ipc, UNIX_TIMESTAMP(create_time) as create_time from indicador_mensual order by id_indicador asc limit 12";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                IndicadorEconomicoMensualBean reg = new IndicadorEconomicoMensualBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setUtm(result.getFloat("utm"));
                reg.setIpc(result.getFloat("ipc"));
                reg.setPublicTime(result.getString("create_time"));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIEM14() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIEM14() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIEM14() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIEM14() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIEM14() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIEM14() : " + ex);
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

    public Collection<IndicadorEconomicoMensualBean> getIPC12() throws ParseException {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoMensualBean> list = new ArrayList<IndicadorEconomicoMensualBean>();

        try {
            String sql = "select * from indicador_mensual order by id_indicador asc limit 12";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                IndicadorEconomicoMensualBean reg = new IndicadorEconomicoMensualBean();
                /* obtener resultset */
                reg.setId(result.getInt("id_indicador"));
                reg.setUtm(result.getFloat("utm"));
                reg.setIpc(result.getFloat("ipc"));
                reg.setPublicTime(Format.dateMonth(result.getString("create_time")));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIEM14() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIEM14() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIEM14() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIEM14() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIEM14() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIEM14() : " + ex);
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

    public Collection<IndicadorEconomicoDiarioBean> getIED365Days() throws ParseException {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoDiarioBean> list = new ArrayList<IndicadorEconomicoDiarioBean>();

        try {
            String sql = "select * from indicador_diario order by id_indicador asc limit 365";

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
                reg.setPublicTime(Format.dateYYYYMMDD(result.getString("create_time")));

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIED30Days() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIED30Days() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIED30Days() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIED30Days() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIED30Days() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIED30Days() : " + ex);
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

    public Collection<IndicadorEconomicoDiarioBean> getIED365DaysUnixTime() throws ParseException {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoDiarioBean> list = new ArrayList<IndicadorEconomicoDiarioBean>();

        try {
            String sql = "select id_indicador, uf, usd, eur, UNIX_TIMESTAMP(create_time) as create_time from indicador_diario order by id_indicador asc limit 365";

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

                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en IndicadorEconomicoDAO, getIED30Days() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, getIED30Days() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIED30Days() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, getIED30Days() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, getIED30Days() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada enIndicadorEconomicoDAO, getIED30Days() : " + ex);
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

    public Collection<IndicadorEconomicoDiarioBean> indicadorDiarioGetAll() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoDiarioBean> list = new ArrayList<IndicadorEconomicoDiarioBean>();

        try {
            String sql = "select * from indicador_diario";

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
            String sql = "insert into indicador_diario (uf, eur, usd) values (?, ?, ?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getUf());
            sentence.setFloat(2, indicador.getEuro());
            sentence.setFloat(3, indicador.getDolar());

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
            String sql = "update indicador_diario set uf = ?, eur = ?, usd = ? where id_indicador = ? ";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getUf());
            sentence.setFloat(2, indicador.getEuro());
            sentence.setFloat(3, indicador.getDolar());
            sentence.setInt(4, indicador.getId());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioUpdate() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en IndicadorEconomicoDAO, indicadorDiarioUpdate() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioUpdate() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en IndicadorEconomicoDAO, indicadorDiarioUpdate() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioUpdate() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en IndicadorEconomicoDAO, indicadorDiarioUpdate() : " + ex);
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

    public Collection<IndicadorEconomicoSemanalBean> indicadorSemanalGetAll() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<IndicadorEconomicoSemanalBean> list = new ArrayList<IndicadorEconomicoSemanalBean>();

        try {
            String sql = "select * from indicador_semanal";

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
            String sql = "update indicador_semanal set bencina93 = ?, bencina95 = ?, bencina97 = ?, diesel = ? where id_indicador = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setFloat(1, indicador.getBencina93());
            sentence.setFloat(2, indicador.getBencina95());
            sentence.setFloat(3, indicador.getBencina97());
            sentence.setFloat(4, indicador.getDiesel());
            sentence.setInt(5, indicador.getId());

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
