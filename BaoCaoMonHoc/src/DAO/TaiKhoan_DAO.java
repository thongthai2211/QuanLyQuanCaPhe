/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoan_DAO {
    private Connection conn;

    public TaiKhoan_DAO() {
        conn = DBConnect.getConnection();
    }

    // Phương thức thêm tài khoản mới vào cơ sở dữ liệu
    public boolean AddTaiKhoan(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan(MaTK, MaNhanVien, TenTK, MatKhau) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tk.getMaTK());
            ps.setString(2, tk.getMaNV());
            ps.setString(3, tk.getTenTK());
            ps.setString(4, tk.getMatKhau());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức cập nhật thông tin tài khoản
    public boolean UpdateTaiKhoan(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET MaTK = ?, TenTK = ?, MatKhau = ? WHERE MaNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tk.getMaTK());
            ps.setString(2, tk.getMaNV());
            ps.setString(3, tk.getTenTK());
            ps.setString(4, tk.getMatKhau());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa tài khoản của một nhân viên
    public boolean DeleteTaiKhoan(String MaNV) {
        String sql = "DELETE FROM TaiKhoan WHERE MaNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, MaNV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy thông tin tài khoản của một nhân viên
    public TaiKhoan getTaiKhoan(String MaNV) {
        String sql = "SELECT * FROM TaiKhoan WHERE MaNhanVien = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, MaNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(rs.getString("MaTK"));
                tk.setMaNV(rs.getString("MaNhanVien"));
                tk.setTenTK(rs.getString("TenTK"));
                tk.setMatKhau(rs.getString("MatKhau"));
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
