/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ss.db;

import com.ss.beans.Admin;
import com.ss.db.tables.AdminManager;
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
        Admin admin = new Admin();
        admin = AdminManager.getAdmin(1);
        if (admin != null) {
            System.out.print("Admin Id = " + admin.getAdminId());
            System.out.print("Admin Name = " + admin.getUserName());
            System.out.print("Admin Password = " + admin.getPassword());
        }
    }

}
