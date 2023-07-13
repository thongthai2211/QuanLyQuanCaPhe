/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.DoUong;
import POJO.KhoDoUong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class KhoDoUong_DAO {
    public static List<KhoDoUong> getAllKhoDoUong() {
        List<KhoDoUong> listKhoDoUong = new ArrayList<>();
        try {
            DBConnect.getInstance().connect();
            Connection con = DBConnect.getConnection();
            String SQL = "SELECT KhoDoUong.MaDoUong, DoUong.TenDoUong, KhoDoUong.SoLuong FROM KhoDoUong JOIN DoUong ON KhoDoUong.MaDoUong = DoUong.MaDoUong";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                int maDoUong = rs.getInt(1);
                String tenDoUong = rs.getString(2);
                int soLuong = rs.getInt(3);
                KhoDoUong khodo = new KhoDoUong(maDoUong, tenDoUong, soLuong);
                listKhoDoUong.add(khodo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKhoDoUong;
    }
    
    public static boolean giamSoLuongKhoDoUong(int maDoUong, int soLuong) {
        boolean result = false;
        try {
            DBConnect.getInstance().connect();
            Connection con = DBConnect.getConnection();
            String SQL = "UPDATE KhoDoUong SET SoLuong = SoLuong - ? WHERE MaDoUong = ?";
            PreparedStatement statement = con.prepareStatement(SQL);
            statement.setInt(1, soLuong);
            statement.setInt(2, maDoUong);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int laySoLuongKhoDoUong(int maDoUong) {
        int soLuong = 0;
        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT SoLuong FROM KhoDoUong WHERE MaDoUong = ?");
            ps.setInt(1, maDoUong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }
    
    public static boolean checkSoLuongKhoDoUong(int maDoUong, int soLuong) {
        // Lấy thông tin về đồ uống từ cơ sở dữ liệu
        KhoDoUong khoDoUong = getKhoDoUongByMa(maDoUong);

        // Kiểm tra xem số lượng đồ uống trong kho có đủ để thực hiện đặt hàng hay không
        if (khoDoUong == null) {
            return false;
        } else if (khoDoUong.getSoLuong() < soLuong) {
            return false;
        } else {
            return true;
        }
    }
    public static KhoDoUong getKhoDoUongByMa(int maDoUong) {
        KhoDoUong khoDoUong = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement("SELECT * FROM KhoDoUong WHERE MaDoUong = ?");
            ps.setInt(1, maDoUong);
            rs = ps.executeQuery();

            if (rs.next()) {
                String tenDoUong = rs.getString("tenDoUong");
                int soLuong = rs.getInt("soLuong");

                khoDoUong = new KhoDoUong(maDoUong, tenDoUong, soLuong);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeConnection(conn, ps, rs);
        }

        return khoDoUong;
    } 
    public boolean ThemKhoDoUong(KhoDoUong kdu) {
        // TODO Auto-generated method stub
        DBConnect.getInstance();
        Connection con = DBConnect.getConnection();
        PreparedStatement statement = null;
        String SQL ="INSERT INTO KhoDoUong VALUES(?,?)";
        int n = 0;
        try {
            statement = con.prepareStatement(SQL);
            statement.setInt(1, kdu.getMaDoUong());
            statement.setInt(2, kdu.getSoLuong());
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
    public boolean DeleteKhoDoUong(String maKDU) {
        DBConnect.getInstance();
        Connection con=DBConnect.getConnection();
        PreparedStatement statement=null;
        int n =0;
        try {
            statement=con.prepareStatement("delete from KhoDoUong where MaDoUong=? ");
            statement.setString(1,maKDU);
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
    public boolean SuaKhoDoUong(KhoDoUong kdu) {
        // TODO Auto-generated method stub
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "UPDATE KhoDoUong SET SoLuong = ? WHERE MaDoUong = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(2, kdu.getMaDoUong());
            ps.setInt(1, kdu.getSoLuong());
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;        
    }
}

