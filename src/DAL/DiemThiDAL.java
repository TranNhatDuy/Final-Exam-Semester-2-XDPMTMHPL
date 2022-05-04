/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DiemThiDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Win 10
 */
public class DiemThiDAL {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<DiemThiDTO> loadDataDiemThi(String khoathi, String phongthi) {
        ArrayList<DiemThiDTO> listDiemThi = new ArrayList<>();
        String query = "SELECT * FROM diemthi WHERE KhoaThi = '" + khoathi + "' AND PhongThi = '" + phongthi + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DiemThiDTO diem = new DiemThiDTO(khoathi, phongthi, query, Double.NaN, Double.NaN, Double.NaN, Double.NaN);
                diem.setKhoathi(rs.getString("KhoaThi"));
                diem.setPhongthi(rs.getString("PhongThi"));
                diem.setSbd(rs.getString("Sbd"));
                diem.setNghe(rs.getDouble("Nghe"));
                diem.setNoi(rs.getDouble("Noi"));
                diem.setDoc(rs.getDouble("Doc"));
                diem.setViet(rs.getDouble("Viet"));
                listDiemThi.add(diem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDiemThi;
    }
//    UPDATE `diemthi` SET `Nghe` = '9.0', `Noi` = '8.5', `Doc` = '7.8', `Viet` = '9.8' WHERE `diemthi`.`Sbd` = 'B1011';

    public boolean capNhatDiemThi(DiemThiDTO diemThiDTO) {
        String query = "UPDATE `diemthi` SET `Nghe` = ?, `Noi` = ?, `Doc` = ?, `Viet` = ? WHERE `diemthi`.`Sbd` = ?";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            ps.setDouble(1, diemThiDTO.getNghe());
            ps.setDouble(2, diemThiDTO.getNoi());
            ps.setDouble(3, diemThiDTO.getDoc());
            ps.setDouble(4, diemThiDTO.getViet());
            ps.setString(5, diemThiDTO.getSbd());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> traCuuThongTin(String hoten, String sdt) {
        String str = null;
        ArrayList<String> listThongTin = new ArrayList<>();
        String query = "SELECT dssvthi.KhoaThi, dssvthi.MaPhongThi, dssvthi.SBD, phieuduthi.HoTen, phieuduthi.Sdt, diemthi.Nghe, diemthi.Noi, diemthi.Doc, diemthi.Viet "
                + "FROM dssvthi, phieuduthi, phongthi, diemthi "
                + "WHERE dssvthi.STT = phieuduthi.STT "
                + "AND dssvthi.SBD = diemthi.Sbd "
                + "AND dssvthi.MaPhongThi = phongthi.TenPhong "
                + "AND dssvthi.KhoaThi = phongthi.KhoaThi "
                + "AND phieuduthi.HoTen = '" + hoten + "' "
                + "AND phieuduthi.Sdt = '" + sdt + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
//                string[0] = rs.getString("KhoaThi");
//                string[1] = rs.getString("MaPhongThi");
//                string[2] = rs.getString("SBD");
//                string[3] = rs.getString("HoTen");
//                string[4] = rs.getString("Sdt");
//                string[5] = rs.getString("Nghe");
//                string[6] = rs.getString("Noi");
//                string[7] = rs.getString("Doc");
//                string[8] = rs.getString("Viet");
                str = rs.getString("KhoaThi") + "/" +
                        rs.getString("MaPhongThi") + "/" +
                        rs.getString("SBD") + "/" +
                        rs.getString("HoTen")+ "/" +
                        rs.getString("Sdt")+ "/" +
                        String.valueOf(rs.getDouble("Nghe"))+ "/" +
                        String.valueOf(rs.getDouble("Noi"))+ "/" +
                        String.valueOf(rs.getDouble("Doc"))+ "/" +
                        String.valueOf(rs.getDouble("Viet"));
                listThongTin.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThongTin;
    }
}
