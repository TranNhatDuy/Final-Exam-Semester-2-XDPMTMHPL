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
public class XepPhongDTO {
    String khoaThi;
    String tenPhong;
    String SBD;
    int Stt;
    String hoTen;
    
    public String getKhoaThi() {
        return khoaThi;
    }

    public void setKhoaThi(String khoaThi) {
        this.khoaThi = khoaThi;
    }

    public XepPhongDTO(String khoaThi, String tenPhong, String SBD, String hoTen) {
        this.khoaThi = khoaThi;
        this.tenPhong = tenPhong;
        this.SBD = SBD;
        this.hoTen = hoTen;
    }
    

    public XepPhongDTO(String khoaThi, String tenPhong, String SBD, int Stt) {
        this.khoaThi = khoaThi;
        this.tenPhong = tenPhong;
        this.SBD = SBD;
        this.Stt = Stt;
    }

//    public XepPhongDTO(String tenPhong, String SBD, String hoTen) {
//        this.tenPhong = tenPhong;
//        this.SBD = SBD;
//        this.hoTen = hoTen;
//    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getSBD() {
        return SBD;
    }

    public void setSBD(String SBD) {
        this.SBD = SBD;
    }

    public int getStt() {
        return Stt;
    }

    public void setStt(int Stt) {
        this.Stt = Stt;
    }
    
}
