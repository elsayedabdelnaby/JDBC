/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss.db;

import com.ss.db.tables.Tours;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author elsayed
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection connection       = null;
        CallableStatement statement = null;
        ResultSet result            = null;
        String SQL                  = "{ call getToursByPrice(?)}";
        try {
            // to call procudure in DB use CallableStatement
            connection  = DBUtil.getConnection(DBType.MYSQL);
            statement   = DBUtil.getCallableStatement(connection, SQL);
            statement.setDouble(1, 2000);// set the value of condition
            result      = statement.executeQuery();
            Tours.displayData(result);
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

}
