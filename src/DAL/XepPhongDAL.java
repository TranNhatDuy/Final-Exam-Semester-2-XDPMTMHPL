/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DiemThiDTO;
import DTO.PhieuDuThiDTO;
import DTO.XepPhongDTO;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Win 10
 */
public class XepPhongDAL {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
//=======================lấy danh sách khóa thi, trình độ, phòng thi, tên sinh viên, số báo danh
    String s0 = "SELECT DISTINCT KhoaThi, MaPhongThi, SBD, HoTen FROM dssvthi, phieuduthi, tenphongthi, phongthi, khoathi WHERE phieuduthi.TrinhDo = 'A2' "
            + "AND dssvthi.MaPhongThi = 'A2P01' "
            + "AND phongthi.KhoaThi = 'A2T12020' "
            + "AND phieuduthi.STT=dssvthi.STT "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong=dssvthi.MaPhongThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong";

//=======================lấy số phòng thi theo khóa thi và trình độ
    String s1 = "SELECT DISTINCT KhoaThi, phongthi.TenPhong FROM phieuduthi, tenphongthi, phongthi, khoathi WHERE phieuduthi.TrinhDo = 'A2' AND phongthi.KhoaThi = 'A2T12020' "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong";

//=======================chọn khóa thi theo trình độ 
    String s2 = "SELECT DISTINCT KhoaThi, phongthi.TenPhong FROM tenphongthi, phongthi, khoathi WHERE KhoaThi.MaKhoaThi LIKE 'A2%' "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong";

//=======================chọn phòng thi theo khóa thi
    String s3 = "SELECT DISTINCT KhoaThi, phongthi.TenPhong FROM tenphongthi, phongthi, khoathi WHERE KhoaThi.MaKhoaThi = 'A2T92021' "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong";

//=======================chọn những thí sinh chưa được thêm số báo danh cũng chưa được thêm vào phòng thi theo trình độ
    String s4 = "SELECT DISTINCT HoTen, STT FROM phieuduthi "
            + "WHERE phieuduthi.STT NOT IN ( SELECT phieuduthi.STT FROM dssvthi, phieuduthi, tenphongthi, phongthi, khoathi WHERE phieuduthi.TrinhDo = 'A2' "
            + "AND dssvthi.MaPhongThi = 'A2P01' "
            + "AND phongthi.KhoaThi = 'A2T12020' "
            + "AND phieuduthi.STT=dssvthi.STT "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong=dssvthi.MaPhongThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong )"
            + "AND TrinhDo = 'A2'";
    
    public ArrayList<PhieuDuThiDTO> loadDataByTrinhDo(String trinhdo) {
        ArrayList<PhieuDuThiDTO> phieuduthi = new ArrayList<>();
        String query = "SELECT * FROM phieuduthi WHERE TrinhDo = '" + trinhdo + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                PhieuDuThiDTO p1 = new PhieuDuThiDTO(query, query, query, 0);
                p1.setHoten(rs.getString("HoTen"));
                p1.setSdt(rs.getString("Sdt"));
                p1.setTrinhdo(rs.getString("TrinhDo"));
                p1.setStt(rs.getInt("STT"));
                phieuduthi.add(p1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuduthi;
    }
    
    public ArrayList<PhieuDuThiDTO> loadThiSinhChuaThemVaoPhongThi(String trinhdo, String phongthi, String khoathi) {
        ArrayList<PhieuDuThiDTO> listchuathem = new ArrayList<>();
        String query = "SELECT DISTINCT HoTen, Sdt, TrinhDo, STT FROM phieuduthi "
            + "WHERE phieuduthi.STT NOT IN ( SELECT phieuduthi.STT FROM dssvthi, phieuduthi, tenphongthi, phongthi, khoathi WHERE phieuduthi.TrinhDo = '"+ trinhdo +"' "
            + "AND dssvthi.MaPhongThi LIKE '"+ trinhdo +"P%' "
            + "AND phongthi.KhoaThi = '"+ khoathi +"' "
            + "AND phieuduthi.STT=dssvthi.STT "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong=dssvthi.MaPhongThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong )"
            + "AND TrinhDo = '"+ trinhdo +"'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                PhieuDuThiDTO p1 = new PhieuDuThiDTO(query, query, query, 0);
                p1.setHoten(rs.getString("HoTen"));
                p1.setSdt(rs.getString("Sdt"));
                p1.setTrinhdo(rs.getString("TrinhDo"));
                p1.setStt(rs.getInt("STT"));
                listchuathem.add(p1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listchuathem;
    }
    
    public ArrayList<String> loadKhoaThi(String trinhdo, String ngaythi){
        String query = "SELECT DISTINCT KhoaThi FROM tenphongthi, phongthi, khoathi WHERE KhoaThi.MaKhoaThi LIKE '"+ trinhdo +"%' AND NgayThi = '"+ ngaythi +"' "
                + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
                + "AND tenphongthi.MaPhong = phongthi.TenPhong";
        ArrayList<String> listKhoaThi = new ArrayList<>();
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String khoathi = rs.getString("KhoaThi");
                listKhoaThi.add(khoathi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKhoaThi;
    }
    
    public ArrayList<String> loadNgayThi(String trinhdo){
        String query = "SELECT DISTINCT NgayThi FROM tenphongthi, phongthi, khoathi WHERE KhoaThi.MaKhoaThi LIKE '"+ trinhdo +"%' "
                + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
                + "AND tenphongthi.MaPhong = phongthi.TenPhong";
        ArrayList<String> listNgayThi = new ArrayList<>();
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ngaythi = rs.getString("NgayThi");
                listNgayThi.add(ngaythi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNgayThi;
    }
    
    public ArrayList<String> loadPhongThi(String trinhdo, String ngaythi, String khoathi){
        String query = "SELECT DISTINCT TenPhong FROM tenphongthi, phongthi, khoathi WHERE KhoaThi.MaKhoaThi LIKE '"+ trinhdo +"%' "
                + "AND NgayThi = '"+ ngaythi +"' "
                + "AND KhoaThi = '"+ khoathi +"' AND khoathi.MaKhoaThi=phongthi.KhoaThi "
                + "AND tenphongthi.MaPhong = phongthi.TenPhong";
//        System.err.println(query);
        ArrayList<String> listPhongThi = new ArrayList<>();
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String phongthi = rs.getString("TenPhong");
                listPhongThi.add(phongthi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPhongThi;
    }
    
    public int demThiSinhCuaPhongThi(String phongthi) { //đếm số thí sinh có trong phòng thi (lấy tham số từ cbTrinhDo)
        int count = 0;
//        String phongthi = String trinhdo;
        String query = "SELECT COUNT(*) AS rowcount FROM dssvthi WHERE MaPhongThi LIKE '"+ phongthi +"P%'";
//        String query = "SELECT COUNT(*) AS rowcount FROM dssvthi WHERE MaPhongThi = '" + phongthi + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("rowcount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public boolean traPhongThi(String maphong) {
        String query = "SELECT DISTINCT FROM dssvthi WHERE MaPhong = '" + maphong + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true; //đã có phòng
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; //chưa có phòng
    }
    
    public ArrayList<XepPhongDTO> loadThiSinhPhongThi(String trinhdo, String maphongthi, String khoathi) {
        ArrayList<XepPhongDTO> listThiSinhPhongThi = new ArrayList<>();
        String query = "SELECT DISTINCT dssvthi.KhoaThi, MaPhongThi, SBD, HoTen FROM dssvthi, phieuduthi, tenphongthi, phongthi, khoathi WHERE phieuduthi.TrinhDo = '"+ trinhdo +"' "
            + "AND dssvthi.MaPhongThi = '"+ maphongthi +"' "
            + "AND dssvthi.KhoaThi = '"+ khoathi +"' "
            + "AND phieuduthi.STT=dssvthi.STT "
            + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
            + "AND tenphongthi.MaPhong=dssvthi.MaPhongThi "
            + "AND tenphongthi.MaPhong = phongthi.TenPhong";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String getkhoathi = rs.getString("KhoaThi");
                String phongthi = rs.getString("MaPhongThi");
                String sbd = rs.getString("SBD");
//                int stt = rs.getInt("STT");
                String hoten = rs.getString("HoTen");
//                String[] list = {phongthi, sbd, hoten};
                XepPhongDTO xepPhongDTO = new XepPhongDTO(getkhoathi, phongthi, sbd, hoten);
                listThiSinhPhongThi.add(xepPhongDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThiSinhPhongThi;
    }
    public int demThiSinhChuaDuocThemVaoPhong(String trinhdo, String khoathi) { //đếm số thí sinh chưa được thêm vào phòng (lấy từ tham số từ cbTrinhDo)
        int count = 0;
        String query = "SELECT COUNT(STT) AS rowcount FROM phieuduthi WHERE phieuduthi.STT "
                + "NOT IN ( SELECT phieuduthi.STT FROM dssvthi, phieuduthi, tenphongthi, phongthi, khoathi "
                + "WHERE phieuduthi.TrinhDo ='"+ trinhdo +"' "
                + "AND dssvthi.MaPhongThi LIKE '"+ trinhdo +"P%' "
                + "AND phongthi.KhoaThi = '"+ khoathi +"' "
                + "AND phieuduthi.STT=dssvthi.STT "
                + "AND khoathi.MaKhoaThi=phongthi.KhoaThi "
                + "AND tenphongthi.MaPhong=dssvthi.MaPhongThi "
                + "AND tenphongthi.MaPhong = phongthi.TenPhong ) "
                + "AND TrinhDo = '"+ trinhdo +"'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("rowcount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    public boolean addXepPhong(XepPhongDTO xepphong) {
        String query = "INSERT INTO `dssvthi`(`KhoaThi`, `MaPhongThi`, `SBD`, `STT`) VALUES (?,?,?,?)";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            ps.setString(1, xepphong.getKhoaThi());
            ps.setString(2, xepphong.getTenPhong());
            ps.setString(3, xepphong.getSBD());
            ps.setInt(4, xepphong.getStt());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addDiemThi(DiemThiDTO diemthi) {
        String query = "INSERT INTO `diemthi`(`KhoaThi`, `PhongThi`, `Sbd`, `Nghe`, `Noi`, `Doc`, `Viet`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            ps.setString(1, diemthi.getKhoathi());
            ps.setString(2, diemthi.getPhongthi());
            ps.setString(3, diemthi.getSbd());
            ps.setDouble(4, diemthi.getNghe());
            ps.setDouble(5, diemthi.getNoi());
            ps.setDouble(6, diemthi.getDoc());
            ps.setDouble(7, diemthi.getViet());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
