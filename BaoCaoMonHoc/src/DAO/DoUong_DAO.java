/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.DoUong;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import DAO.DBConnect;
/**
 *
 * @author Admin
 */
public class DoUong_DAO {
    public static ArrayList<DoUong> LayThongTinDoUong() {
         ArrayList<DoUong> dsDoUong = new  ArrayList<DoUong>();
        try {
            DBConnect.getInstance().connect();
            Connection con =DBConnect.getConnection();
            String SQL ="SELECT * FROM DoUong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()) {
                int maDoUong = rs.getInt(1);
                String tenDoUong = rs.getString(2);
                float gia = rs.getFloat(3);
                DoUong du = new DoUong(maDoUong, tenDoUong, gia);
                dsDoUong.add(du);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsDoUong;
    }
    public boolean ThemDoUong(DoUong du) {
        // TODO Auto-generated method stub
        DBConnect.getInstance();
        Connection con = DBConnect.getConnection();
        PreparedStatement statement = null;
        String SQL ="INSERT INTO DoUong VALUES(?,?,?)";
        int n = 0;
        try {
            statement = con.prepareStatement(SQL);
            statement.setInt(1, du.getMaDoUong());
            statement.setString(2, du.getTenDoUong());
            statement.setFloat(3, du.getGia());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            }catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return n>0;
    }
    public boolean DeleteDoUong (String maDU) {
        DBConnect.getInstance();
        Connection con=DBConnect.getConnection();
        PreparedStatement statement=null;
        int n =0;
        try {
            statement=con.prepareStatement("delete from DoUong where MaDoUong=? ");
            statement.setString(1,maDU);
            n=statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            }catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return n>0;
    }
    public boolean SuaDoUong(DoUong du) {
        // TODO Auto-generated method stub
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "UPDATE DoUong SET TenDoUong = ?, Gia = ? WHERE MaDoUong = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(3, du.getMaDoUong());
            ps.setString(1, du.getTenDoUong());
            ps.setFloat(2, du.getGia());
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;        
    }
}
