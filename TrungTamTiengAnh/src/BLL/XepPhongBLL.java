/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.XepPhongDAL;
import DTO.DiemThiDTO;
import DTO.PhieuDuThiDTO;
import DTO.XepPhongDTO;
import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public class XepPhongBLL {
    public static ArrayList<PhieuDuThiDTO> loadDataByTrinhDo(String trinhdo) {
        return new XepPhongDAL().loadDataByTrinhDo(trinhdo);
    }
    public static ArrayList<String> loadNgayThi(String trinhdo) {
        return new XepPhongDAL().loadNgayThi(trinhdo);
    }
    public static ArrayList<String> loadKhoaThi(String trinhdo, String ngaythi) {
        return new XepPhongDAL().loadKhoaThi(trinhdo, ngaythi);
    }
    public static ArrayList<String> loadPhongThi(String trinhdo, String ngaythi, String khoathi) {
        return new XepPhongDAL().loadPhongThi(trinhdo, ngaythi, khoathi);
    }
    public int demThiSinhCuaPhongThi(String phongthi){ //lấy tham số từ cbTrinhDo truyền vào
        return new XepPhongDAL().demThiSinhCuaPhongThi(phongthi);
    }
    public boolean traPhongThi(String phongthi){
        return new XepPhongDAL().traPhongThi(phongthi);
    }
    public static ArrayList<XepPhongDTO> loadThiSinhPhongThi(String trinhdo, String maphongthi, String khoathi) {
        return new XepPhongDAL().loadThiSinhPhongThi(trinhdo, maphongthi, khoathi);
    }
    public static ArrayList<PhieuDuThiDTO> loadThiSinhChuaThemVaoPhongThi(String trinhdo, String maphongthi, String khoathi) {
        return new XepPhongDAL().loadThiSinhChuaThemVaoPhongThi(trinhdo, maphongthi, khoathi);
    }
    public int demThiSinhChuaDuocThemVaoPhong(String trinhdo, String khoathi){
        return new XepPhongDAL().demThiSinhChuaDuocThemVaoPhong(trinhdo, khoathi);
    }
    public boolean addXepPhong(XepPhongDTO xepphong){
        return new XepPhongDAL().addXepPhong(xepphong);
    }
    public boolean addDiemThi(DiemThiDTO diemThiDTO){
        return new XepPhongDAL().addDiemThi(diemThiDTO);
    } 
}
