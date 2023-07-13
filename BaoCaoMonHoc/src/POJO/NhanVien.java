/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private int maNhanVien;
    private String hoTenNV;
    private String chucVu;
    private String gioiTinh;
    private int dienThoai;
    private int Luong;

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String hoTenNV, String chucVu, String gioiTinh, int dienThoai, int Luong) {
        this.maNhanVien = maNhanVien;
        this.hoTenNV = hoTenNV;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.dienThoai = dienThoai;
        this.Luong = Luong;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(int dienThoai) {
        this.dienThoai = dienThoai;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }
    
}
