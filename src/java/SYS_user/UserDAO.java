/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SYS_user;

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
public class UserDAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<UserBean> getAll() {

        PreparedStatement sentence = null;
        ResultSet result = null;

        Collection<UserBean> list = new ArrayList<UserBean>();

        try {
            String sql = "select * from user";

            sentence = conexion.prepareStatement(sql);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instaciar objeto */
                UserBean reg = new UserBean();
                /* obtener resultset */
                reg.setIdUser(result.getInt("id_user"));
                reg.setUsername(result.getString("username"));
                reg.setEmail(result.getString("email"));
                reg.setUserType(result.getInt("user_type"));
                reg.setCreateTime(result.getString("create_time"));
                /* agregar a la lista */
                list.add(reg);
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, getAll() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, getAll() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, getAll() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, getAll() : " + ex);
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

    public UserBean findByUserPass(String username, String pwdCrypted) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        UserBean reg = null;

        try {
            String sql = "select * from user where username = ? or email = ? and password = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, username);
            sentence.setString(2, username);
            sentence.setString(3, pwdCrypted);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new UserBean();
                /* obtener resultset */
                reg.setIdUser(result.getInt("id_user"));
                reg.setUsername(result.getString("username"));
                reg.setEmail(result.getString("email"));
                reg.setCreateTime(result.getString("create_time"));
                reg.setUserType(result.getInt("user_type"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, findByUserPass() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, findByUserPass() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, findByUserPass() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, findByUserPass() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, findByUserPass() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, findByUserPass() : " + ex);
        } finally {
            /* liberar recursos */
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

    public UserBean findById(int id) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        UserBean reg = null;

        try {
            String sql = "select * from user where id_user = ? ";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            result = sentence.executeQuery();

            while (result.next()) {
                /* instanciar objeto */
                reg = new UserBean();
                /* obtener resultset */
                reg.setIdUser(result.getInt("id_user"));
                reg.setUsername(result.getString("username"));
                reg.setEmail(result.getString("email"));
                reg.setUserType(result.getInt("user_type"));
                reg.setCreateTime(result.getString("create_time"));
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, findById() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, findById() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, findById() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, findById() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, findById() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, findById() : " + ex);
        } finally {
            /* liberar recursos */
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

    public boolean validateDuplicateUsername(UserBean reg) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        boolean find = false;

        try {
            String sql = "select * from user where id_user <> ? and username = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdUser());
            sentence.setString(2, reg.getUsername());

            result = sentence.executeQuery();

            while (result.next()) {
                find = true;
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, validateDuplicateUsername() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, validateDuplicateUsername() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, validateDuplicateUsername() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, validateDuplicateUsername() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, validateDuplicateUsername() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, validateDuplicateUsername() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return find;
    }

    public boolean validateDuplicateEmail(UserBean reg) {

        PreparedStatement sentence = null;
        ResultSet result = null;

        boolean find = false;

        try {

            String sql = "select * from user where id_user <> ? and email = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, reg.getIdUser());
            sentence.setString(2, reg.getEmail());

            result = sentence.executeQuery();

            while (result.next()) {
                find = true;
            }

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, validateDuplicateEmail() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, validateDuplicateEmail() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, validateDuplicateEmail() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, validateDuplicateEmail() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, validateDuplicateEmail() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, validateDuplicateEmail() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                result.close();
            } catch (Exception noGestionar) {
            }
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
        return find;
    }

    public void insert(UserBean user) {

        PreparedStatement sentence = null;

        try {
            String sql = "insert into user (username, email, password, user_type) values (?, ?, ?, ?)";

            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, user.getUsername());
            sentence.setString(2, user.getEmail());
            sentence.setString(3, user.getPassword());
            sentence.setInt(4, user.getUserType());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, insert() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, insert() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, insert() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, insert() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void update(UserBean user) {

        PreparedStatement sentence = null;

        try {
            String sql = "update user set username = ?, email = ?, user_type = ? where id_user = ? ";

            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, user.getUsername());
            sentence.setString(2, user.getEmail());
            sentence.setInt(3, user.getUserType());
            sentence.setInt(4, user.getIdUser());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("MySQL Syntax Exception en UserDAO, update() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, update() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, update() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, update() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, update() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void updatePassword(UserBean user) {

        PreparedStatement sentence = null;

        try {
            String sql = "update user set username = ?, email = ?, user_type = ?, password = ? where id_user = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setString(1, user.getUsername());
            sentence.setString(2, user.getEmail());
            sentence.setInt(3, user.getUserType());
            sentence.setString(4, user.getPassword());
            sentence.setInt(5, user.getIdUser());

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, updatePassword() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, updatePassword() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, updatePassword() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, updatePassword() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, updatePassword() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, updatePassword() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }

    public void delete(int id) {

        PreparedStatement sentence = null;

        try {
            String sql = "delete from user where id_user = ?";

            sentence = conexion.prepareStatement(sql);

            sentence.setInt(1, id);

            sentence.executeUpdate();

        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Error de sintaxis en UserDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Syntax Exception en UserDAO, delete() : " + ex);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("MySQL Excepción de integridad en UserDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción de integridad en UserDAO, delete() : " + ex);
        } catch (SQLException ex) {
            System.out.println("MySQL Excepción inesperada en UserDAO, delete() : " + ex);
            throw new RuntimeException("MySQL Excepción inesperada en UserDAO, delete() : " + ex);
        } finally {
            /* liberar recursos */
            try {
                sentence.close();
            } catch (Exception noGestionar) {
            }
        }
    }
}
