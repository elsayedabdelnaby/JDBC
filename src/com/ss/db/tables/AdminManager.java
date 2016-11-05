/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss.db.tables;

import com.ss.beans.Admin;
import com.ss.db.ConnectionManager;
import com.ss.db.DBType;
import com.ss.db.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.NumberFormat;

/**
 *
 * @author elsayed
 */
public class AdminManager {

    private static Connection connection = ConnectionManager.getInstance().getConnection();

    public static void displayData(ResultSet rs) throws SQLException {
        String SQL = "SELECT * FROM admins ";
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = DBUtil.getPreparedStatement(connection, SQL);
            result = statement.executeQuery();

            while (result.next()) {
                StringBuffer buffer = new StringBuffer();

                buffer.append("Admin " + rs.getInt("id") + ": ");
                buffer.append(rs.getString("name"));
                double price = rs.getDouble("password");
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String formattedPrice = nf.format(price);
                buffer.append(" (" + formattedPrice + ")");
                System.out.println(buffer.toString());
            }
        } catch (SQLException e) {
            DBUtil.processException(e);

        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static int getRowsNumber(ResultSet rs) throws SQLException {
        rs.last();
        int rowsNumber = rs.getRow();
        rs.first();
        return rowsNumber;
    }

    public static Admin getAdmin(int adminId) throws SQLException {
        String SQL = "SELECT * FROM admins where id = ?";

        PreparedStatement statement = null;
        ResultSet result = null;
        Admin admin = null;
        try {
            statement = DBUtil.getPreparedStatement(connection, SQL);
            statement.setInt(1, adminId);
            result = statement.executeQuery();
            if (result.next()) {
                admin = new Admin();
                admin.setAdminId(adminId);
                admin.setUserName(result.getString("username"));
                admin.setPassword(result.getString("password"));
            }
        } catch (SQLException e) {
            DBUtil.processException(e);
            admin = null;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return admin;
    }

    public static boolean insert(Admin admin) throws SQLException {
        String SQL = "INSERT INTO admins (username, password) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet keys = null;
        boolean is_insert = false;

        try {
            statement = DBUtil.getPreparedStatement(connection, SQL);
            statement.setString(1, admin.getUserName());
            statement.setString(2, admin.getPassword());
            int affected = statement.executeUpdate();// return number of rows affected
            if (affected == 1) {
                keys = statement.getGeneratedKeys();
                keys.next();
                admin.setAdminId(keys.getInt(1));
                is_insert = true;
            }

        } catch (SQLException e) {
            DBUtil.processException(e);
            is_insert = false;
        } finally {
            if (keys != null) {
                keys.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return is_insert;
    }

    public static boolean update(Admin admin) throws SQLException {
        String SQL = "UPDATE admins SET username = ?, password = ? WHERE id = ?";
        PreparedStatement statement = null;
        boolean is_update = false;
        try {
            statement = DBUtil.getPreparedStatement(connection, SQL);
            statement.setString(1, admin.getUserName());
            statement.setString(2, admin.getPassword());
            statement.setInt(3, admin.getAdminId());
            int affected = statement.executeUpdate();// return number of rows affected
            if (affected == 1) {
                is_update = true;
            }

        } catch (SQLException e) {
            DBUtil.processException(e);
            is_update = false;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return is_update;
    }

    public static boolean delete(int adminId) throws SQLException {
        String SQL = "DELETE FROM admins WHERE id = ?";
        PreparedStatement statement = null;
        boolean is_delete = false;
        try {
            statement = DBUtil.getPreparedStatement(connection, SQL);
            statement.setInt(1, adminId);
            int affected = statement.executeUpdate();// return number of rows affected
            if (affected == 1) {
                is_delete = true;
            }

        } catch (SQLException e) {
            DBUtil.processException(e);
            is_delete = false;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return is_delete;
    }

}
