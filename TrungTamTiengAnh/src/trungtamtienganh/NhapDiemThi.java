/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungtamtienganh;

import BLL.DiemThiBLL;
import BLL.XepPhongBLL;
import DTO.DiemThiDTO;
import DTO.PhieuDuThiDTO;
import DTO.XepPhongDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Win 10
 */
public class NhapDiemThi extends javax.swing.JPanel {

    /**
     * Creates new form XepPhong
     */
    private DefaultTableModel modelNhapDiem;
    private List<DiemThiDTO> listDiemThi;

    private DefaultTableModel modelXepPhong;
    private List<XepPhongDTO> listThiSinhPhongThi;

    ArrayList<PhieuDuThiDTO> listChuaThem = new ArrayList<>();
//    private List<XepPhongDTO> listThiSinhPhongThi;

    public NhapDiemThi() {
        this.setBounds(0, 0, 844, 620);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        initComponents();

        modelNhapDiem = (DefaultTableModel) tblNhapDiem.getModel();
        listDiemThi = new ArrayList<>();
        ShowDiemThiDau();
//        showThiSinhChuaThem();

        modelXepPhong = (DefaultTableModel) tblSBD.getModel();
        listThiSinhPhongThi = new ArrayList<>();
        ShowThiSinhPhongThiDau();

        LocalDate today = LocalDate.now();
        String day = String.valueOf(today.getDayOfMonth());
        String month = String.valueOf(today.getMonthValue());
        String year = String.valueOf(today.getYear());
        String date = day + "-" + month + "-" + year;

        ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi("A2");
        cbNgayThi.removeAllItems();
        for (String string : listNgayThi) {
            cbNgayThi.addItem(string);
        }

        String ngaythi = cbNgayThi.getItemAt(0);
        ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi("A2", ngaythi);
        cbKhoaThi.removeAllItems();
        for (String string : listKhoaThi) {
            cbKhoaThi.addItem(string);
        }

        String khoathi = cbKhoaThi.getItemAt(0);
        ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi("A2", ngaythi, khoathi);
        cbPhongThi.removeAllItems();
        for (String string : listPhongThi) {
            cbPhongThi.addItem(string);
        }

        thiSinhPhongThi();
        setComboboxTrinhDo();
        tblNhapDiem.getColumnModel().getColumn(0).setPreferredWidth(10); //set chiều dài cho cột
        tblNhapDiem.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblNhapDiem.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblNhapDiem.getColumnModel().getColumn(3).setPreferredWidth(30);
        tblNhapDiem.getColumnModel().getColumn(4).setPreferredWidth(25);
        tblNhapDiem.getColumnModel().getColumn(5).setPreferredWidth(25);
        tblNhapDiem.getColumnModel().getColumn(6).setPreferredWidth(25);
        tblNhapDiem.getColumnModel().getColumn(7).setPreferredWidth(25);
        tblNhapDiem.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tblNhapDiem.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //căn giữa cho ô
        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        tblNhapDiem.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tblNhapDiem.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        //căn giữa cho cột
//        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//        tblNhapDiem.setDefaultRenderer(String.class, centerRenderer);
        tblSBD.setRowHeight(20);
        tblSBD.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblSBD.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblSBD.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblSBD.getColumnModel().getColumn(3).setPreferredWidth(230);
        tblSBD.getColumnModel().getColumn(4).setPreferredWidth(20);

        tblSBD.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSBD.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSBD.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblSBD.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    }

    void ShowDiemThiDau() {
        String trinhdo = "A2";
        ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi(trinhdo);
        String ngaythi = listNgayThi.get(0);
        ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi(trinhdo, ngaythi);
        String khoathi = listKhoaThi.get(0);
        ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi(trinhdo, ngaythi, khoathi);
        String phongthi = listPhongThi.get(0);

        modelNhapDiem.setRowCount(0);
        int i = 1;
        for (DiemThiDTO diemthiDTO : listDiemThi) {

            modelNhapDiem.addRow(new Object[]{
                i, diemthiDTO.getKhoathi(), diemthiDTO.getPhongthi(), diemthiDTO.getSbd(), diemthiDTO.getNghe(), diemthiDTO.getNoi(),
                diemthiDTO.getDoc(), diemthiDTO.getViet()
            });
            i++;
        }
    }

    void ShowThiSinhPhongThiDau() {
        ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi("A2");
        cbNgayThi.addItem(listNgayThi.get(0));
        String ngaythi = cbNgayThi.getItemAt(0);
        ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi("A2", ngaythi);
        cbKhoaThi.addItem(listKhoaThi.get(0));
        String khoathi = cbKhoaThi.getItemAt(0);
        ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi("A2", ngaythi, khoathi);
        cbPhongThi.addItem(listPhongThi.get(0));
        String phongthi = cbPhongThi.getItemAt(0);
//        String phongthi = cbPhongThi.getItemAt();
//        String khoathi = cbKhoaThi.getItemAt(0);
        listThiSinhPhongThi = new XepPhongBLL().loadThiSinhPhongThi("A2", phongthi, khoathi);
        modelXepPhong.setRowCount(0);
        for (XepPhongDTO xepPhongDTO : listThiSinhPhongThi) {
            modelXepPhong.addRow(new Object[]{
                khoathi, ngaythi, xepPhongDTO.getTenPhong(), xepPhongDTO.getHoTen(), xepPhongDTO.getSBD()
            });
        }
    }

    void ShowThiSinhTrongPhongThi(String trinhdo) {
        ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi(trinhdo);
        String ngaythi = listNgayThi.get(0);

        ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi(trinhdo, ngaythi);
        String khoathi = listKhoaThi.get(0);

        ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi(trinhdo, ngaythi, khoathi);
        String phongthi = listPhongThi.get(0);

        listThiSinhPhongThi = new XepPhongBLL().loadThiSinhPhongThi(trinhdo, phongthi, khoathi);
        modelXepPhong.setRowCount(0);
        for (XepPhongDTO xepPhongDTO : listThiSinhPhongThi) {
            modelXepPhong.addRow(new Object[]{
                khoathi, ngaythi, xepPhongDTO.getTenPhong(), xepPhongDTO.getHoTen(), xepPhongDTO.getSBD()

            });
        }
//        for (int i = 0; i < listThiSinhPhongThi.size(); i++) {
//            System.out.println(listThiSinhPhongThi.get(i).getHoTen() + " - " + listThiSinhPhongThi.get(i).getSBD());
//        }

    }

    void thiSinhPhongThi() {
        cbPhongThi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trinhdo = (String) cbTrinhDo.getSelectedItem();
//                ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi(trinhdo);
//                String ngaythi = listNgayThi.get(0);
                String ngaythi = (String) cbNgayThi.getSelectedItem();
                String khoathi = (String) cbKhoaThi.getSelectedItem();
                String phongthi = (String) cbPhongThi.getSelectedItem();
                listThiSinhPhongThi = new XepPhongBLL().loadThiSinhPhongThi(trinhdo, phongthi, khoathi);
                modelXepPhong.setRowCount(0);
                for (XepPhongDTO xepPhongDTO : listThiSinhPhongThi) {
                    modelXepPhong.addRow(new Object[]{
                        khoathi, ngaythi, xepPhongDTO.getTenPhong(), xepPhongDTO.getHoTen(), xepPhongDTO.getSBD()
                    });
                }
//                String trinhdo = (String) cbTrinhDo.getSelectedItem();
                String a = (String) cbPhongThi.getSelectedItem();
                String b = (String) cbKhoaThi.getSelectedItem();
                ArrayList<DiemThiDTO> listDiem = new DiemThiBLL().loadDataDiemThi(b, a);
                modelNhapDiem.setRowCount(0);
                int i = 1;
                for (DiemThiDTO diem : listDiem) {
                    modelNhapDiem.addRow(new Object[]{
                        i, diem.getKhoathi(), diem.getPhongthi(), diem.getSbd(), diem.getNghe(), diem.getNoi(), diem.getDoc(), diem.getViet()
                    });
                    i++;
                }
            }
        });
    }

    void setComboboxTrinhDo() {
        cbTrinhDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trinhdo = (String) cbTrinhDo.getSelectedItem();
                String a = (String) cbPhongThi.getSelectedItem();
                String b = (String) cbKhoaThi.getSelectedItem();
                listDiemThi = new DiemThiBLL().loadDataDiemThi(b, a);
                modelNhapDiem.setRowCount(0);
                int i = 1;
                for (DiemThiDTO diem : listDiemThi) {
                    modelNhapDiem.addRow(new Object[]{
                        i, diem.getKhoathi(), diem.getPhongthi(), diem.getSbd(), diem.getNghe(), diem.getNoi(), diem.getDoc(), diem.getViet()
                    });
                    i++;
                }
                cbNgayThi.removeAllItems();
                ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi(trinhdo);
                for (String date : listNgayThi) {
                    cbNgayThi.addItem(date);
                }

                String ngaythi = cbNgayThi.getItemAt(0);
                cbKhoaThi.removeAllItems();
                ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi(trinhdo, ngaythi);
                for (String khoathi : listKhoaThi) {
                    cbKhoaThi.addItem(khoathi);
                }

                String khoathi = cbKhoaThi.getItemAt(0);
                cbPhongThi.removeAllItems();
                ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi(trinhdo, ngaythi, khoathi);
                for (String phongthi : listPhongThi) {
                    cbPhongThi.addItem(phongthi);
                }
                ShowThiSinhTrongPhongThi(trinhdo);
            }

        });
        cbNgayThi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trinhdo = (String) cbTrinhDo.getSelectedItem();
                String ngaythi_selected = (String) cbNgayThi.getSelectedItem();
                cbKhoaThi.removeAllItems();

                ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi(trinhdo, ngaythi_selected);
                for (String khoathi : listKhoaThi) {
                    cbKhoaThi.addItem(khoathi);
                }
                String khoathi_selected = cbKhoaThi.getItemAt(0);

                cbPhongThi.removeAllItems();
                ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi(trinhdo, ngaythi_selected, khoathi_selected);
                for (String phongthi : listPhongThi) {
                    cbPhongThi.addItem(phongthi);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbTrinhDo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbKhoaThi = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbPhongThi = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSBD = new javax.swing.JTable();
        btnXepPhong = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhapDiem = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbNgayThi = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nhập điểm thi");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Trình độ:");

        cbTrinhDo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A2", "B1" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Khóa thi:");

        cbKhoaThi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Phòng thi:");

        cbPhongThi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tblSBD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Khóa thi", "Ngày thi", "Phòng thi", "Họ tên", "Số báo danh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSBD);

        btnXepPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXepPhong.setText("Cập nhật điểm");
        btnXepPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXepPhongActionPerformed(evt);
            }
        });

        tblNhapDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Khóa thi", "Phòng thi", "Số báo danh", "Nghe", "Nói", "Đọc", "Viết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblNhapDiem);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Ngày thi:");

        cbNgayThi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXepPhong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addComponent(cbTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cbKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cbPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnXepPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXepPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXepPhongActionPerformed
        // TODO add your handling code here:
        int column = 0;
        int a = tblNhapDiem.getRowCount();
        if (a == -1) {
            a = 200;
        }
        boolean check = false;
        for (int i = 0; i < a; i++) {
            String khoathi = tblNhapDiem.getModel().getValueAt(i, 1).toString();
            String phongthi = tblNhapDiem.getModel().getValueAt(i, 2).toString();
            String sbd = tblNhapDiem.getModel().getValueAt(i, 3).toString();
            Double nghe = Double.valueOf(tblNhapDiem.getModel().getValueAt(i, 4).toString());
            Double noi = Double.valueOf(tblNhapDiem.getModel().getValueAt(i, 5).toString());
            Double doc = Double.valueOf(tblNhapDiem.getModel().getValueAt(i, 6).toString());
            Double viet = Double.valueOf(tblNhapDiem.getModel().getValueAt(i, 7).toString());
            
            if(nghe > 10 || nghe < 0 || noi > 10 || noi < 0 || doc > 10 || doc < 0 || viet > 10 || viet < 0 ){
                JOptionPane.showMessageDialog(null, "Nhập điểm sai tại số báo danh: " + sbd);
                check = false;
                break;
            }
            
            DiemThiDTO diem = new DiemThiDTO(khoathi, phongthi, sbd, nghe, noi, doc, viet);
            check = new DiemThiBLL().capNhatDiemThi(diem);
            if(check == false){
                break;
            }
        }
        if(check == true){
            JOptionPane.showMessageDialog(null, "Cập nhật điểm thành công!");
        }
//        String stt = tblNhapDiem.getModel().getValueAt(0, 0).toString();
//        String khoathi = tblNhapDiem.getModel().getValueAt(0, 1).toString();
//        String phongthi = tblNhapDiem.getModel().getValueAt(0, 2).toString();
//        String sbd = tblNhapDiem.getModel().getValueAt(0, 3).toString();
//        String nghe = tblNhapDiem.getModel().getValueAt(0, 4).toString();
//        String noi = tblNhapDiem.getModel().getValueAt(0, 5).toString();
//        String doc = tblNhapDiem.getModel().getValueAt(0, 6).toString();
//        String viet = tblNhapDiem.getModel().getValueAt(0, 7).toString();
//
//        System.out.println("STT: " + stt + " - "
//                + "Khóa thi: " + khoathi + " - "
//                + "Phòng thi: " + phongthi + " - "
//                + "Số báo danh: " + sbd + " - "
//                + "Nghe: " + nghe + " - "
//                + "Nói: " + noi + " - "
//                + "Đọc: " + doc + " - "
//                + "Viết: " + viet);
    }//GEN-LAST:event_btnXepPhongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXepPhong;
    private javax.swing.JComboBox<String> cbKhoaThi;
    private javax.swing.JComboBox<String> cbNgayThi;
    private javax.swing.JComboBox<String> cbPhongThi;
    private javax.swing.JComboBox<String> cbTrinhDo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNhapDiem;
    private javax.swing.JTable tblSBD;
    // End of variables declaration//GEN-END:variables

}
