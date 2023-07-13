/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author DELL
 */
public class KhoDoUong {
    private int maDoUong;
    private String tenDoUong;
    private int soLuong;

    public KhoDoUong(int maDoUong, String tenDoUong, int soLuong) {
        this.maDoUong = maDoUong;
        this.tenDoUong = tenDoUong;
        this.soLuong = soLuong;
    }

    public KhoDoUong() {
    }

    public int getMaDoUong() {
        return maDoUong;
    }

    public void setMaDoUong(int maDoUong) {
        this.maDoUong = maDoUong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

