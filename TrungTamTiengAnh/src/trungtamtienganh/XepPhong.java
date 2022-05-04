/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungtamtienganh;

import BLL.XepPhongBLL;
import DTO.DiemThiDTO;
import DTO.PhieuDuThiDTO;
import DTO.XepPhongDTO;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
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
public class XepPhong extends javax.swing.JPanel {

    /**
     * Creates new form XepPhong
     */
    private DefaultTableModel modelPhieuDuThi;
    private List<PhieuDuThiDTO> listPhieuDuThi;

    private DefaultTableModel modelXepPhong;
    private List<XepPhongDTO> listThiSinhPhongThi;

    ArrayList<PhieuDuThiDTO> listChuaThem = new ArrayList<>();
//    private List<XepPhongDTO> listThiSinhPhongThi;

    public XepPhong() {
        this.setBounds(0, 0, 844, 620);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        initComponents();

        modelPhieuDuThi = (DefaultTableModel) tblDangKi.getModel();
        listPhieuDuThi = new ArrayList<>();
//        ShowPhieuDuThi();
        showThiSinhChuaThem();

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
        tblDangKi.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblDangKi.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblDangKi.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblDangKi.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblDangKi.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        tblSBD.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblSBD.getColumnModel().getColumn(1).setPreferredWidth(90);
        tblSBD.getColumnModel().getColumn(2).setPreferredWidth(180);
        tblSBD.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblSBD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        tblDangKi.setRowHeight(20);
        tblSBD.setRowHeight(20);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //căn giữa cho ô
        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        tblSBD.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSBD.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSBD.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblDangKi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblDangKi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblDangKi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        //căn giữa cho cột
//        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//        tblSBD.setDefaultRenderer(String.class, centerRenderer);
        
    }

    void ShowPhieuDuThi() {
        String trinhdo = (String) cbTrinhDo.getSelectedItem();
//        String phongthi = (String) cbPhongThi.getSelectedItem();
//        String khoathi = (String) cbKhoaThi.getSelectedItem();
//        listChuaThem = new XepPhongBLL().;

        listPhieuDuThi = new XepPhongBLL().loadDataByTrinhDo(trinhdo);
        modelPhieuDuThi.setRowCount(0);
        int i = 1;
        for (PhieuDuThiDTO phieuDuThi : listPhieuDuThi) {

            modelPhieuDuThi.addRow(new Object[]{
                i, phieuDuThi.getHoten(), phieuDuThi.getSdt(), phieuDuThi.getTrinhdo()
            });
            i++;
        }
    }

    void showThiSinhChuaThem() { //hiển thị lên table danh sách đăng kí
//        String trinhdo = (String) cbTrinhDo.getSelectedItem();
//        String trinhdo = cbTrinhDo.getItemAt(0);
        String trinhdo = "A2";
        ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi(trinhdo);
        String ngaythi = listNgayThi.get(0);
        ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi(trinhdo, ngaythi);
        String khoathi = listKhoaThi.get(0);
        ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi(trinhdo, ngaythi, khoathi);
        String phongthi = listPhongThi.get(0);
//        String str_phongthi = phongthi.substring(2);
//        String phongthi = (String) cbPhongThi.getSelectedItem();
//        String phongthi = cbPhongThi.getItemAt(0);
//        String khoathi = (String) cbKhoaThi.getSelectedItem();
//        String khoathi = cbKhoaThi.getItemAt(0);

        listChuaThem = new XepPhongBLL().loadThiSinhChuaThemVaoPhongThi(trinhdo, phongthi, khoathi);

        listPhieuDuThi = new XepPhongBLL().loadDataByTrinhDo(trinhdo);
        modelPhieuDuThi.setRowCount(0);
        int i = 1;
        for (PhieuDuThiDTO phieuDuThi : listChuaThem) {

            modelPhieuDuThi.addRow(new Object[]{
                i, phieuDuThi.getHoten(), phieuDuThi.getSdt(), phieuDuThi.getTrinhdo()
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
                xepPhongDTO.getKhoaThi(), xepPhongDTO.getTenPhong(), xepPhongDTO.getHoTen(), xepPhongDTO.getSBD()
            });
        }
    }

    void ShowThiSinhTrongPhongThi(String trinhdo) {
//        String phongthi = cbPhongThi.getItemAt(0);
//        String khoathi = cbKhoaThi.getItemAt(0);
        ArrayList<String> listNgayThi = new XepPhongBLL().loadNgayThi(trinhdo);
//        cbNgayThi.addItem(listNgayThi.get(0));
//        String ngaythi = cbNgayThi.getItemAt(0);
        String ngaythi = listNgayThi.get(0);
        ArrayList<String> listKhoaThi = new XepPhongBLL().loadKhoaThi(trinhdo, ngaythi);
//        cbKhoaThi.addItem(listKhoaThi.get(0));
//        String khoathi = cbKhoaThi.getItemAt(0);
        String khoathi = listKhoaThi.get(0);
        ArrayList<String> listPhongThi = new XepPhongBLL().loadPhongThi(trinhdo, ngaythi, khoathi);
//        cbPhongThi.addItem(listPhongThi.get(0));
//        String phongthi = cbPhongThi.getItemAt(0);
        String phongthi = listPhongThi.get(0);
//        String phongthi = cbPhongThi.getItemAt();
//        String khoathi = cbKhoaThi.getItemAt(0);

        listThiSinhPhongThi = new XepPhongBLL().loadThiSinhPhongThi(trinhdo, phongthi, khoathi);
        modelXepPhong.setRowCount(0);
        for (XepPhongDTO xepPhongDTO : listThiSinhPhongThi) {
            modelXepPhong.addRow(new Object[]{
                xepPhongDTO.getKhoaThi(), xepPhongDTO.getTenPhong(), xepPhongDTO.getHoTen(), xepPhongDTO.getSBD()

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
                String khoathi = (String) cbKhoaThi.getSelectedItem();
                String phongthi = (String) cbPhongThi.getSelectedItem();
                listThiSinhPhongThi = new XepPhongBLL().loadThiSinhPhongThi(trinhdo, phongthi, khoathi);
                modelXepPhong.setRowCount(0);
                for (XepPhongDTO xepPhongDTO : listThiSinhPhongThi) {
                    modelXepPhong.addRow(new Object[]{
                        xepPhongDTO.getKhoaThi(), xepPhongDTO.getTenPhong(), xepPhongDTO.getHoTen(), xepPhongDTO.getSBD()
                    });
                }
//                String trinhdo = (String) cbTrinhDo.getSelectedItem();
//                String a = (String) cbPhongThi.getSelectedItem();
//                String b = (String) cbKhoaThi.getSelectedItem();
                ArrayList<PhieuDuThiDTO> listPhieuDuThi = new XepPhongBLL().loadThiSinhChuaThemVaoPhongThi(trinhdo, phongthi, khoathi);
                modelPhieuDuThi.setRowCount(0);
                int i = 1;
                for (PhieuDuThiDTO phieuDuThi : listPhieuDuThi) {
                    modelPhieuDuThi.addRow(new Object[]{
                        i, phieuDuThi.getHoten(), phieuDuThi.getSdt(), phieuDuThi.getTrinhdo()
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
                listPhieuDuThi = new XepPhongBLL().loadThiSinhChuaThemVaoPhongThi(trinhdo, a, b);
                modelPhieuDuThi.setRowCount(0);
                int i = 1;
                for (PhieuDuThiDTO phieuDuThi : listPhieuDuThi) {
                    modelPhieuDuThi.addRow(new Object[]{
                        i, phieuDuThi.getHoten(), phieuDuThi.getSdt(), phieuDuThi.getTrinhdo()
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
        tblDangKi = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbNgayThi = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Xếp phòng thi");

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Khóa thi", "Phòng thi", "Họ tên", "Số báo danh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSBD);

        btnXepPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXepPhong.setText("Xếp phòng");
        btnXepPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXepPhongActionPerformed(evt);
            }
        });

        tblDangKi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Họ tên", "Số điện thoại", "Trình độ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDangKi);

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel5.setText("(Danh sách đăng kí)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Ngày thi:");

        cbNgayThi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                        .addComponent(btnXepPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXepPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXepPhongActionPerformed
        // TODO add your handling code here:
        String phongthi = (String) cbPhongThi.getSelectedItem();
        String khoathi = (String) cbKhoaThi.getSelectedItem();
        String trinhdo = (String) cbTrinhDo.getSelectedItem();
        String ngaythi = (String) cbNgayThi.getSelectedItem();

        ArrayList<PhieuDuThiDTO> listPhieuDuThi = new XepPhongBLL().loadThiSinhChuaThemVaoPhongThi(trinhdo, phongthi, khoathi);
        int demThiSinhChuaDuocThemVaoPhong = new XepPhongBLL().demThiSinhChuaDuocThemVaoPhong(trinhdo, khoathi);
        int demThiSinhTrongPhong = new XepPhongBLL().demThiSinhCuaPhongThi(trinhdo);
//        System.out.println("demThiSinhChuaDuocThemVaoPhong: " + demThiSinhChuaDuocThemVaoPhong);
//        System.out.println("demThiSinhTrongPhong: " + demThiSinhTrongPhong);
        String sbd = "";
        if (listPhieuDuThi.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Đã xếp hết thí sinh vào phòng thi");
        } else {
            for (int i = 0; i < 1; i++) { //listPhieuDuThi.size()
                if (i == 30) {
                    JOptionPane.showMessageDialog(null, "Đã xếp đủ thí sinh vào phòng: " + phongthi);
                    break;
                }
                int stt = listPhieuDuThi.get(i).getStt();
                if ((demThiSinhTrongPhong + 1) < 10) {
                    sbd = trinhdo + "00" + String.valueOf(demThiSinhTrongPhong + 1);
                } else if ((demThiSinhTrongPhong + 1) <= 99) {
                    sbd = trinhdo + "0" + String.valueOf(demThiSinhTrongPhong + 1);
                } else {
                    sbd = trinhdo + String.valueOf(demThiSinhTrongPhong + 1);
                }
                XepPhongDTO xepPhongDTO = new XepPhongDTO(khoathi, phongthi, sbd, stt);
                XepPhongBLL a = new XepPhongBLL();
                a.addXepPhong(xepPhongDTO);
                DiemThiDTO diemthi = new DiemThiDTO(khoathi, phongthi, sbd, 0.0, 0.0, 0.0, 0.0);
                a.addDiemThi(diemthi);
//            System.out.println("Phòng thi: " + phongthi + " - " + "Họ tên: " + listPhieuDuThi.get(i).getHoten() + " - " + "STT: " + stt
//                    + "SBD: " + sbd);
                demThiSinhTrongPhong++;

            }
            JOptionPane.showMessageDialog(null, "Xếp phòng thành công!");
            cbTrinhDo.setSelectedIndex(0);
            cbNgayThi.setSelectedIndex(0);
            cbKhoaThi.setSelectedIndex(0);
            cbPhongThi.setSelectedIndex(0);
            ShowThiSinhPhongThiDau();
            showThiSinhChuaThem();
        }
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDangKi;
    private javax.swing.JTable tblSBD;
    // End of variables declaration//GEN-END:variables

//    SELECT DISTINCT HoTen, STT FROM phieuduthi
//WHERE phieuduthi.STT NOT IN (
//SELECT  phieuduthi.HoTen, phieuduthi.STT FROM dssvthi, phieuduthi, tenphongthi, phongthi, khoathi WHERE phieuduthi.TrinhDo = 'A2' 
//	AND dssvthi.MaPhongThi = 'A2P01' 
//	AND phongthi.KhoaThi = 'A2T12020'
//	AND phieuduthi.STT=dssvthi.STT
//	AND khoathi.MaKhoaThi=phongthi.KhoaThi
//	AND tenphongthi.MaPhong=dssvthi.MaPhongThi 
//	AND tenphongthi.MaPhong = phongthi.TenPhong
//)
}
