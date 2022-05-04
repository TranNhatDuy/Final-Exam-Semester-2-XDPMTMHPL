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
public class PhieuDuThiDTO {
    String hoten, sdt, trinhdo;
    int stt;

    public PhieuDuThiDTO(String hoten, String sdt, String trinhdo, int stt) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.trinhdo = trinhdo;
        this.stt = stt;
    }

    public PhieuDuThiDTO(String hoten, String sdt, String trinhdo) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.trinhdo = trinhdo;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    

    
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTrinhdo() {
        return trinhdo;
    }

    public void setTrinhdo(String trinhdo) {
        this.trinhdo = trinhdo;
    }
    
}
