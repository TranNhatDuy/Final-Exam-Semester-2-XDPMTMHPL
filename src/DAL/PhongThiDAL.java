/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.GiaoVienDTO;
import DTO.PhieuDuThiDTO;
import DTO.PhongThiDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Win 10
 */
public class PhongThiDAL {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<GiaoVienDTO> loadDataGiaoVien() {
        ArrayList<GiaoVienDTO> giaovienList = new ArrayList<>();
        String query = "SELECT * FROM giaovien";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                GiaoVienDTO gv1 = new GiaoVienDTO(0, query);
                gv1.setMaGV(rs.getInt("MaGV"));
                gv1.setTenGV(rs.getString("TenGV"));
                giaovienList.add(gv1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return giaovienList;
    }

    public ArrayList<PhongThiDTO> loadDataPhongThi() {
        ArrayList<PhongThiDTO> phongThiList = new ArrayList<>();
        String query = "SELECT * FROM phongthi";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                PhongThiDTO phong = new PhongThiDTO(query, query, query, query, 0, 0, 0);
                phong.setKhoathi(rs.getString("KhoaThi"));
                phong.setNgaythi(rs.getString("NgayThi"));
                phong.setGiothi(rs.getString("GioThi"));
                phong.setTenphong(rs.getString("TenPhong"));
                phong.setSoluong(rs.getInt("SoLuong"));
                phong.setGv1(rs.getInt("GV1"));
                phong.setGv2(rs.getInt("GV2"));
                phongThiList.add(phong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phongThiList;
    }

    public GiaoVienDTO selectGiaoVien(int id) {
        GiaoVienDTO gv = new GiaoVienDTO(0, "name");

        String query = "SELECT * FROM giaovien WHERE MaGV = ?";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                gv = new GiaoVienDTO(rs.getInt("MaGV"), rs.getString("TenGV"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gv;
    }

    public ArrayList<GiaoVienDTO> chonGiaoVienKhongPhaiLaGiamThi(String ngaythi) {
        ArrayList<GiaoVienDTO> listGiaoVienGacThi = new ArrayList<>();
        String query = "SELECT DISTINCT giaovien.MaGV AS MaGV, giaovien.TenGV AS TenGV FROM giaovien,phongthi "
                + "WHERE MAGV NOT IN ( SELECT phongthi.GV1 FROM phongthi WHERE phongthi.NgayThi = '" + ngaythi + "' ) "
                + "AND MaGV NOT IN ( SELECT phongthi.GV2 FROM phongthi WHERE phongthi.NgayThi = '" + ngaythi + "' ) "
                + "AND phongthi.NgayThi = '" + ngaythi + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                GiaoVienDTO gv1 = new GiaoVienDTO(0, query);
                gv1.setMaGV(rs.getInt("MaGV"));
                gv1.setTenGV(rs.getString("TenGV"));
                listGiaoVienGacThi.add(gv1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGiaoVienGacThi;
    }

    public boolean addPhongThi(PhongThiDTO phongthi) {
        String query = "INSERT INTO `phongthi`(`KhoaThi`, `NgayThi`, `GioThi`, `TenPhong`, `SoLuong`, `GV1`, `GV2`) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            ps.setString(1, phongthi.getKhoathi());
            ps.setString(2, phongthi.getNgaythi());
            ps.setString(3, phongthi.getGiothi());
            ps.setString(4, phongthi.getTenphong());
            ps.setInt(5, phongthi.getSoluong());
            ps.setInt(6, phongthi.getGv1());
            ps.setInt(7, phongthi.getGv2());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Lưu phòng thi thành công!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean traKhoaThi(String khoathi) {
        String query = "SELECT * FROM khoathi WHERE MaKhoaThi = '" + khoathi + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                String str = rs.getString("MaKhoaThi");
                if (str.equals(khoathi)) {
                    return true; //đã có khóa thi
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; //chưa có khóa thi
    }

    public boolean traPhongThi(String maphong) {
        String query = "SELECT * FROM tenphongthi WHERE MaPhong = '" + maphong + "'";
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

    public boolean addKhoaThi(String khoathi, PhongThiDTO phongThiDTO) {
        boolean checkKhoaThi = traKhoaThi(khoathi);
        boolean checkPhongThi = traPhongThi(phongThiDTO.getTenphong());
        if (checkKhoaThi == false) { //chưa có khóa thi
            String query = "INSERT INTO khoathi(MaKhoaThi) VALUES(?)";
            try {
                ps = new MySQLConnect().conn.prepareStatement(query);
                ps.setString(1, khoathi);
                if (ps.executeUpdate() > 0) {
                    if (checkPhongThi == false) {
                        String query1 = "INSERT INTO tenphongthi(MaPhong) VALUES(?)";
                        try {
                            ps = new MySQLConnect().conn.prepareStatement(query1);
                            ps.setString(1, phongThiDTO.getTenphong());
                            if(ps.executeUpdate() > 0){
                                addPhongThi(phongThiDTO);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        addPhongThi(phongThiDTO);
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (checkKhoaThi == true) {
            if(checkPhongThi == false){
                String query1 = "INSERT INTO tenphongthi(MaPhong) VALUES(?)";
                        try {
                            ps = new MySQLConnect().conn.prepareStatement(query1);
                            ps.setString(1, phongThiDTO.getTenphong());
                            if(ps.executeUpdate() > 0){
                                addPhongThi(phongThiDTO);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            }
            else{
                addPhongThi(phongThiDTO);
            }
        }
        return false;
    }

    public int demPhongThi(String trinhdo, String khoathi) {
        int count = 0;
        String query = "SELECT COUNT(*) AS rowcount FROM phongthi WHERE TenPhong LIKE '" + trinhdo + "%' AND KhoaThi = '" + khoathi + "'";
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

    public boolean timNgayThi(String ngaythi) {
        String query = "SELECT * FROM phongthi WHERE NgayThi = '" + ngaythi + "'";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true; //tìm được
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; //chưa có 
    }

}
