/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBConnect {
    public static Connection con = null;
    private static DBConnect instance = new DBConnect();
    public static DBConnect getInstance() {
        return instance;
    }
    public void connect() throws SQLException {
            String url = "jdbc:sqlserver://localhost:1433;databasename=QL_CaFe";
            String user = "sa";
            String password = "thaint2002";
            con = DriverManager.getConnection(url, user, password);
    }
    public void disconnect() {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error closing database connection: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
