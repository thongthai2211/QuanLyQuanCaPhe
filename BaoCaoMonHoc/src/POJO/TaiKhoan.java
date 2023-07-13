/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author HELLO TRƯỜNG AN
 */
public class TaiKhoan {
    private String maTK;
    private String maNV;
    private String tenTK;
    private String matKhau;

    public TaiKhoan(String maTK, String maNV, String tenTK, String matKhau) {
        this.maTK = maTK;
        this.maNV = maNV;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }

    public TaiKhoan() {
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
