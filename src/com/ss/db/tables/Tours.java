/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss.db.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

/**
 *
 * @author elsayed
 */
public class Tours {

    public static void displayData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Tour " + rs.getInt("id") + ": ");
            buffer.append(rs.getString("name"));
            double price = rs.getDouble("price");
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            String formattedPrice = nf.format(price);
            buffer.append(" (" + formattedPrice + ")");
            System.out.println(buffer.toString());
        }
    }

    public static int getRowsNumber(ResultSet rs) throws SQLException {
        rs.last();
        int rowsNumber = rs.getRow();
        rs.first();
        return rowsNumber;
    }
    
}
