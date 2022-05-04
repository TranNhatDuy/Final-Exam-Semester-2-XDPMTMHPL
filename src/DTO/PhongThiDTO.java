/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Win 10
 */
public class PhongThiDTO {
    String khoathi;
    String ngaythi;
    String giothi;
    String tenphong;
    int soluong;
    int gv1;
    int gv2;

    public PhongThiDTO(String khoathi, String ngaythi, String giothi, String tenphong, int soluong, int gv1, int gv2) {
        this.khoathi = khoathi;
        this.ngaythi = ngaythi;
        this.giothi = giothi;
        this.tenphong = tenphong;
        this.soluong = soluong;
        this.gv1 = gv1;
        this.gv2 = gv2;
    }

    public String getKhoathi() {
        return khoathi;
    }

    public void setKhoathi(String khoathi) {
        this.khoathi = khoathi;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getGiothi() {
        return giothi;
    }

    public void setGiothi(String giothi) {
        this.giothi = giothi;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGv1() {
        return gv1;
    }

    public void setGv1(int gv1) {
        this.gv1 = gv1;
    }

    public int getGv2() {
        return gv2;
    }

    public void setGv2(int gv2) {
        this.gv2 = gv2;
    }
    
}
