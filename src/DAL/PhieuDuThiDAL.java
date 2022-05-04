/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.PhieuDuThiDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Win 10
 */
public class PhieuDuThiDAL {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public ArrayList<PhieuDuThiDTO> loadData() {
        ArrayList<PhieuDuThiDTO> phieuduthi = new ArrayList<>();
        String query = "SELECT * FROM phieuduthi";
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
    
    public int demPhieuDuThi(String trinhdo){
        int count = 0;
        String query = "SELECT COUNT(*) AS rowcount FROM phieuduthi WHERE TrinhDo = '"+ trinhdo +"'";
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

    public boolean addPhieuDuThi(PhieuDuThiDTO phieuDuThiDTO) {
        String query = "INSERT INTO phieuduthi(HoTen,Sdt,TrinhDo,STT) VALUES(?,?,?,?)";
        try {
            ps = new MySQLConnect().conn.prepareStatement(query);
            ps.setString(1, phieuDuThiDTO.getHoten());
            ps.setString(2, phieuDuThiDTO.getSdt());
            ps.setString(3, phieuDuThiDTO.getTrinhdo());
            ps.setString(4, null);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Lưu thành công!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

