/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.PhieuDuThiDAL;
import DTO.PhieuDuThiDTO;
import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public class PhieuDuThiBLL {
    public static ArrayList<PhieuDuThiDTO> loadData() {
        return new PhieuDuThiDAL().loadData();
    }
    public static boolean addPhieuDuThi(PhieuDuThiDTO phieuDuThi) {
        return new PhieuDuThiDAL().addPhieuDuThi(phieuDuThi);
    }
    public static int demPhieuDuThi(String trinhdo){
        return new PhieuDuThiDAL().demPhieuDuThi(trinhdo);
    }
}
