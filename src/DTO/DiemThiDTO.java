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
public class DiemThiDTO {
    String khoathi, phongthi, Sbd;
    Double nghe, noi, doc, viet;

    public DiemThiDTO(String khoathi, String phongthi, String Sbd, Double nghe, Double noi, Double doc, Double viet) {
        this.khoathi = khoathi;
        this.phongthi = phongthi;
        this.Sbd = Sbd;
        this.nghe = nghe;
        this.noi = noi;
        this.doc = doc;
        this.viet = viet;
    }

    public String getKhoathi() {
        return khoathi;
    }

    public void setKhoathi(String khoathi) {
        this.khoathi = khoathi;
    }

    public String getPhongthi() {
        return phongthi;
    }

    public void setPhongthi(String phongthi) {
        this.phongthi = phongthi;
    }

    public String getSbd() {
        return Sbd;
    }

    public void setSbd(String Sbd) {
        this.Sbd = Sbd;
    }

    public Double getNghe() {
        return nghe;
    }

    public void setNghe(Double nghe) {
        this.nghe = nghe;
    }

    public Double getNoi() {
        return noi;
    }

    public void setNoi(Double noi) {
        this.noi = noi;
    }

    public Double getDoc() {
        return doc;
    }

    public void setDoc(Double doc) {
        this.doc = doc;
    }

    public Double getViet() {
        return viet;
    }

    public void setViet(Double viet) {
        this.viet = viet;
    }
    
}
