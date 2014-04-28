/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_indicadorEconomicoEstadistico;

import SYS_indicadorEconomicoMensual.IndicadorEconomicoMensualBean;
import SYS_indicadorEconomicoSemanal.IndicadorEconomicoSemanalBean;
import SYS_indicadorEconomicoDiario.IndicadorEconomicoDiarioBean;
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
import SYS_user.UserBean;

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
}
