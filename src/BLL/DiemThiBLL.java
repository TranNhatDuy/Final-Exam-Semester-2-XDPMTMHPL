/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DiemThiDAL;
import DTO.DiemThiDTO;
import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public class DiemThiBLL {

    public static ArrayList<DiemThiDTO> loadDataDiemThi(String khoathi, String phongthi) {
        return new DiemThiDAL().loadDataDiemThi(khoathi, phongthi);
    }
    public boolean capNhatDiemThi(DiemThiDTO diemThiDTO){
        return new DiemThiDAL().capNhatDiemThi(diemThiDTO);
    }
    public static ArrayList<String> traCuuThongTin(String hoten, String sdt) {
        return new DiemThiDAL().traCuuThongTin(hoten, sdt);
    }

}
