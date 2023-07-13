/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import POJO.NhanVien;
/**
 *
 * @author Admin
 */
public class NhanVien_DAO {
    public static ArrayList<NhanVien> LayThongTinNhanVien() {
         ArrayList<NhanVien> dsNhanVien = new  ArrayList<NhanVien>();
        try {
            DBConnect.getInstance().connect();
            Connection con = DBConnect.getConnection();
            String SQL ="SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()) {
                int maNhanVien = rs.getInt(1);
                String hoTenNV = rs.getString(2);
                String chucVu = rs.getString(3);
                String gioiTinh = rs.getString(4);
                int dienThoai = rs.getInt(5);
                int Luong = rs.getInt(6);
                NhanVien du = new NhanVien(maNhanVien, hoTenNV, chucVu,gioiTinh,dienThoai,Luong);
                dsNhanVien.add(du);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsNhanVien;
    }
    public boolean ThemNhanVien(NhanVien nv) {
        DBConnect.getInstance();
        Connection con = DBConnect.getConnection();
        PreparedStatement statement = null;
        String SQL ="INSERT INTO NhanVien VALUES(?,?,?,?,?,?)";
        int n = 0;
        try {
            statement = con.prepareStatement(SQL);
            statement.setInt(1, nv.getMaNhanVien());
            statement.setString(2, nv.getHoTenNV());
            statement.setString(3, nv.getChucVu());
            statement.setString(4, nv.getGioiTinh());
            statement.setInt(5, nv.getDienThoai());
            statement.setInt(6, nv.getLuong());
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
    public boolean DeleteNhanVien (String MaNV) {
        DBConnect.getInstance();
        Connection con=DBConnect.getConnection();
        PreparedStatement statement=null;
        int n =0;
        try {
            statement=con.prepareStatement("delete from NhanVien where MaNhanVien=? ");
            statement.setString(1,MaNV);
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
    public boolean SuaNhanVien(NhanVien nv) {
        // TODO Auto-generated method stub
        DBConnect.getInstance();
        Connection con = DBConnect.getConnection();
        PreparedStatement statement = null;
        String SQL ="UPDATE NHANVIEN SET TENNHANVIEN=?,CHUCVU=?,GIOITINH=?,DIENTHOAI=?,Luong=? WHERE MaNhanVien=?";
        int n = 0;
        try {
            statement = con.prepareStatement(SQL);
            statement.setInt(6, nv.getMaNhanVien());
            statement.setString(1, nv.getHoTenNV());
            statement.setString(2, nv.getChucVu());
            statement.setString(3, nv.getGioiTinh());
            statement.setInt(4, nv.getDienThoai());
            statement.setInt(5, nv.getLuong());
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
}
