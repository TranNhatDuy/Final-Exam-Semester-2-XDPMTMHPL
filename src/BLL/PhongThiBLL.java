/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.PhongThiDAL;
import DTO.GiaoVienDTO;
import DTO.PhongThiDTO;
import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public class PhongThiBLL {
    public static ArrayList<PhongThiDTO> loadDataPhongThi() {
        return new PhongThiDAL().loadDataPhongThi();
    }
    public GiaoVienDTO selectGiaoVien(int id){
        return new PhongThiDAL().selectGiaoVien(id);
    }
    public static ArrayList<GiaoVienDTO> chonGiaoVienKhongPhaiLaGiamThi(String ngaythi){
        return new PhongThiDAL().chonGiaoVienKhongPhaiLaGiamThi(ngaythi);
    }
    public static boolean addKhoaThi(String khoathi, PhongThiDTO phongThiDTO) {
        return new PhongThiDAL().addKhoaThi(khoathi, phongThiDTO);
    }
    
    public int demPhongThi(String trinhdo, String khoathi){
        return new PhongThiDAL().demPhongThi(trinhdo, khoathi);
    }
    public static boolean timNgayThi(String ngaythi) {
        return new PhongThiDAL().timNgayThi(ngaythi);
    }
}
