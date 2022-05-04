/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungtamtienganh;

import BLL.PhieuDuThiBLL;
import BLL.PhongThiBLL;
import DAL.PhieuDuThiDAL;
import DAL.PhongThiDAL;
import DTO.GiaoVienDTO;
import DTO.PhieuDuThiDTO;
import DTO.PhongThiDTO;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Win 10
 */
public class PhongThi extends javax.swing.JPanel {

    private DefaultTableModel modelPhongThi;
    private List<PhongThiDTO> listPhongThi;
    ArrayList<GiaoVienDTO> giaoVienList1 = new ArrayList<>();
    ArrayList<GiaoVienDTO> giaoVienList2 = new ArrayList<>();

    /**
     * Creates new form PhongThi
     */
    public PhongThi() {
        this.setBounds(0, 0, 844, 620);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        initComponents();

        btnChonNgayThi.setDate(Date.from(Instant.now()));
//        setComboBoxGiaoVienDau();
        setComboBoxGiaoVien();
        setKhoaThiDau();
        setTrinhdo();
        SLDK();

//        setSelectGiaoVien();
        modelPhongThi = (DefaultTableModel) tblPhongThi.getModel();
        listPhongThi = new ArrayList<>();
        showPhongThi();

        tblPhongThi.getColumnModel().getColumn(0).setPreferredWidth(20); //set chiều dài cho cột
        tblPhongThi.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblPhongThi.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblPhongThi.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblPhongThi.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblPhongThi.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblPhongThi.setRowHeight(20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //căn giữa cho ô
        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        tblPhongThi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhongThi.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblPhongThi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblPhongThi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    void showPhongThi() {
        listPhongThi = new PhongThiBLL().loadDataPhongThi();
        modelPhongThi.setRowCount(0);

        for (PhongThiDTO phong : listPhongThi) {
            GiaoVienDTO gv1 = new PhongThiBLL().selectGiaoVien(phong.getGv1());
            GiaoVienDTO gv2 = new PhongThiBLL().selectGiaoVien(phong.getGv2());
            modelPhongThi.addRow(new Object[]{
                phong.getKhoathi(), phong.getNgaythi(), phong.getGiothi(), phong.getTenphong(), gv1.getTenGV(), gv2.getTenGV()
            });
        }
    }

    void SLDK() {
//        ArrayList<PhieuDuThiDTO> list = new ArrayList<PhieuDuThiDTO>();
//        list = new PhieuDuThiBLL().loadData();
//        lblA2.setText(String.valueOf(list.size()) + " thí sinh " + ")");
        int countA2 = new PhieuDuThiBLL().demPhieuDuThi("A2");
        int countB1 = new PhieuDuThiBLL().demPhieuDuThi("B1");
        lblA2.setText(String.valueOf(countA2) + " thí sinh " + ")");
        lblB1.setText(String.valueOf(countB1) + " thí sinh " + ")");

        int phongA2 = countA2 / 30 + 1;
        int phongB1 = countB1 / 30 + 1;
        lblPhongA2.setText(String.valueOf(phongA2) + " phòng");
        lblPhongB1.setText(String.valueOf(phongB1) + " phòng");
    }
    int itemAt;

    void setTrinhdo() {
        cbTrinhDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of generated methods, choose Tools | Templates.
                String select = (String) cbTrinhDo.getSelectedItem();
                if (select.equals("A2")) {
                    itemAt = 0;
                    setKhoaThi(itemAt);
                } else if (select.equals("B1")) {
                    itemAt = 1;
                    setKhoaThi(itemAt);
                }
            }
        });
    }

    void setKhoaThi(int index) {
        String trinhdo;
        trinhdo = cbTrinhDo.getItemAt(index);
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();
        String khoathi = trinhdo + "T" + String.valueOf(month) + String.valueOf(year);
        lblKhoaThi.setText(khoathi);
    }

    void setKhoaThiDau() {
        String trinhdo;
        trinhdo = cbTrinhDo.getItemAt(0);
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();
        String khoathi = trinhdo + "T" + String.valueOf(month) + String.valueOf(year);
        lblKhoaThi.setText(khoathi);
    }

    void setComboBoxGiaoVienDau() {
//        cbGV1.removeAllItems();
//        cbGV2.removeAllItems();
        ArrayList<GiaoVienDTO> giaoVienList;
        PhongThiDAL getGV = new PhongThiDAL();
        giaoVienList2 = getGV.loadDataGiaoVien();
        giaoVienList = getGV.loadDataGiaoVien();
        for (GiaoVienDTO gv : giaoVienList) {
            String item = gv.getTenGV() + " - " + gv.getMaGV();
            cbGV1.addItem(item);
        }
        if (cbGV2.getItemCount() > 0) {
            cbGV2.removeAllItems();
            for (int i = 1; i < giaoVienList.size(); i++) {
                String item = giaoVienList.get(i).getTenGV() + " - " + giaoVienList.get(i).getMaGV();
                cbGV2.addItem(item);
            }
        } else {
            for (int i = 1; i < giaoVienList.size(); i++) {
                String item = giaoVienList.get(i).getTenGV() + " - " + giaoVienList.get(i).getMaGV();
                cbGV2.addItem(item);
            }
//            System.out.println("=========");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhongThi = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblKhoaThi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnChonNgayThi = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cbGio = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbPhut = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbTrinhDo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        spinnerSL = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbGV1 = new javax.swing.JComboBox<>();
        cbGV2 = new javax.swing.JComboBox<>();
        btnLuuPhongThi = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblA2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblB1 = new javax.swing.JLabel();
        lblPhongA2 = new javax.swing.JLabel();
        lblPhongB1 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setPreferredSize(new java.awt.Dimension(844, 620));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Tạo phòng thi");

        tblPhongThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Khóa thi", "Ngày thi", "Giờ thi", "Tên phòng", "Giám thị 1", "Giám thị 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPhongThi);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Khóa thi:");

        lblKhoaThi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblKhoaThi.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Ngày thi:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Giờ thi:");

        cbGio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "8", "9", "10", "11", "12", "13", "14" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText(":");

        cbPhut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbPhut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Trình độ:");

        cbTrinhDo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A2", "B1" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Số lượng:");

        spinnerSL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        spinnerSL.setValue(30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Giám thị 1:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Giám thị 2:");

        cbGV1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cbGV2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnLuuPhongThi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLuuPhongThi.setText("Lưu");
        btnLuuPhongThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuPhongThiActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel10.setText("( Số thí sinh đăng ký A2:");

        lblA2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblA2.setText("jLabel11");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel11.setText("( Số thí sinh đăng ký B1:");

        lblB1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lblB1.setText("jLabel12");

        lblPhongA2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPhongA2.setText("jLabel12");

        lblPhongB1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPhongB1.setText("jLabel12");

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setToolTipText("");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(366, 366, 366))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbGV1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbGV2, 0, 191, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(lblB1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhongB1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblA2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhongA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(btnChonNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXacNhan)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnLuuPhongThi)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(spinnerSL))))))
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6)
                                .addComponent(cbTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btnChonNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnXacNhan)
                            .addGap(6, 6, 6)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(cbPhut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(lblKhoaThi)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbGV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbGV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(22, 22, 22)
                        .addComponent(btnLuuPhongThi))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblA2)
                            .addComponent(lblPhongA2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblB1)
                            .addComponent(lblPhongB1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuPhongThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuPhongThiActionPerformed
        // TODO add your handling code here:
        PhongThiBLL phongthiBLL = new PhongThiBLL();
        //add khóa thi

        //add phòng thi
        String khoathi = lblKhoaThi.getText(); //khóa thi
        String trinhdo = (String) cbTrinhDo.getSelectedItem();
        int sophongthi = phongthiBLL.demPhongThi(trinhdo, khoathi) + 1;
        String thutuphong = "";
        if (sophongthi < 10) {
            thutuphong = "0" + String.valueOf(sophongthi);
        }
        String tenphong = trinhdo + "P" + thutuphong; //tên phòng

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ngaythi = sdf.format(btnChonNgayThi.getDate()); //ngày thi
        String gio = (String) cbGio.getSelectedItem();
        String phut = (String) cbPhut.getSelectedItem();
        String giothi = "";
        if (Integer.parseInt(gio) < 12) {
            giothi = String.valueOf(gio) + ":" + phut + " am";
        } else if (Integer.parseInt(gio) >= 12) {
            giothi = String.valueOf(gio) + ":" + phut + " pm"; //giờ thi
        }

        int soluong = 30; //số lượng
        String gt1 = (String) cbGV1.getSelectedItem();
        String[] ms1 = gt1.split(" ");
//        char c1 = gt1.charAt(gt1.length() - 2);
        String gt2 = (String) cbGV2.getSelectedItem();
        String[] ms2 = gt2.split(" ");
//        char c2 = gt2.charAt(gt2.length() - 2);
        int maGT1 = Integer.parseInt(ms1[ms1.length - 1]); //giám thị 1
        int maGT2 = Integer.parseInt(ms2[ms2.length - 1]); //giám thị 2
        if (maGT1 == maGT2) {
            JOptionPane.showMessageDialog(null, "Hai giám thị không được trùng!");
        } else {
            PhongThiDTO phongThiDTO = new PhongThiDTO(khoathi, ngaythi, giothi, tenphong, soluong, maGT1, maGT2);
            phongthiBLL.addKhoaThi(khoathi, phongThiDTO);
            showPhongThi();

        }
//        System.out.println("khóa thi: " + khoathi + "\n"
//                + "ngày thi: " + ngaythi + "\n"
//                + "giờ thi: " + giothi + "\n"
//                + "tên phòng: " + tenphong + "\n"
//                + "số lượng: " + soluong + "\n"
//                + "giám thị 1: " + maGT1 + "\n"
//                + "giám thị 2: " + maGT2 + "\n");
    }//GEN-LAST:event_btnLuuPhongThiActionPerformed

    boolean check2;

    void setComboBoxGiaoVien() {
        setComboBoxGiaoVienDau();
        check2 = false;

        PhongThiDAL getGV = new PhongThiDAL();
        cbGV1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //To change body of generated methods, choose Tools | Templates.
                cbGV2.removeAllItems();
                for (GiaoVienDTO giaoVienDTO : giaoVienList2) {
                    String item = giaoVienDTO.getTenGV() + " - " + giaoVienDTO.getMaGV();
                    cbGV2.addItem(item);  //1
                }
                int select = cbGV1.getSelectedIndex();
                if (select == -1) {
                    select = 0;
                }
//                System.out.println("select: " + select);
//                System.out.println("gv1: " + giaoVienList1.size());
//                System.out.println("gv2: " + giaoVienList2.size());

//====================
                cbGV2.removeAllItems();
                for (int i = 0; i < giaoVienList2.size(); i++) {
                    String item;

                    item = giaoVienList2.get(i).getTenGV() + " - " + giaoVienList2.get(i).getMaGV();
                    if (i == select && select == (giaoVienList2.size() - 1)) {
                        break;
                    }
                    if (i == select) {
                        i = i + 1;
                        int a = i;
                        item = giaoVienList2.get(a).getTenGV() + " - " + giaoVienList2.get(a).getMaGV();
                    } else if (i < select) {
                        item = giaoVienList2.get(i).getTenGV() + " - " + giaoVienList2.get(i).getMaGV();
                    }
                    cbGV2.addItem(item);
//                    System.out.println("item: " + item);
//                    System.out.println("gv2: hello" + giaoVienList2.size());
//                    System.out.println("gv2: " + giaoVienList2.get(i).getTenGV() + " - " + giaoVienList2.get(i).getMaGV());
                }
                System.out.println(giaoVienList2.size());
//====================                
            }
        });

    }

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String chonngay = sdf.format(btnChonNgayThi.getDate());
        String split[] = chonngay.split("-");
        lblKhoaThi.setText(cbTrinhDo.getSelectedItem() + split[1] + split[2]);
        boolean check = new PhongThiBLL().timNgayThi(chonngay);
        cbGV1.removeAllItems();
        cbGV2.removeAllItems();
        if (check == true) {
            cbGV2.removeAllItems();
            cbGV1.removeAllItems();
            giaoVienList1 = new PhongThiBLL().chonGiaoVienKhongPhaiLaGiamThi(chonngay);
            for (GiaoVienDTO giaoVienDTO : giaoVienList1) {
                String item = giaoVienDTO.getTenGV() + " - " + giaoVienDTO.getMaGV();
                cbGV1.addItem(item);

            }

            giaoVienList2 = giaoVienList1;
            for (int i = (giaoVienList2.size() + cbGV2.getItemCount()); i > 0; i--) {

                if (i == giaoVienList2.size()) {
                    for (int j = 0; j < giaoVienList2.size(); j++) {
                        String item = giaoVienList2.get(j).getTenGV() + " - " + giaoVienList2.get(j).getMaGV();
                        cbGV2.addItem(item);
                    }
                } else if (i > giaoVienList2.size()) {
                    cbGV2.removeAllItems();
                }
            }
//            for (GiaoVienDTO giaoVienDTO : giaoVienList2) {
//                String item = giaoVienDTO.getTenGV() + " - " + giaoVienDTO.getMaGV();
//                cbGV2.addItem(item);
//            }
//            System.out.println("size: " + giaoVienList2.size());
//            System.out.println("cbgv2: " + cbGV2.getItemCount());
        } else if (check == false) {
            PhongThiDAL getGV = new PhongThiDAL();
            giaoVienList2 = getGV.loadDataGiaoVien();
            giaoVienList1 = getGV.loadDataGiaoVien();
            cbGV1.removeAllItems();
            cbGV2.removeAllItems();
            setComboBoxGiaoVienDau();
//            System.out.println("check = " + check + "size list 2:" + giaoVienList2.size());
        }
        
    }//GEN-LAST:event_btnXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser btnChonNgayThi;
    private javax.swing.JButton btnLuuPhongThi;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbGV1;
    private javax.swing.JComboBox<String> cbGV2;
    private javax.swing.JComboBox<String> cbGio;
    private javax.swing.JComboBox<String> cbPhut;
    private javax.swing.JComboBox<String> cbTrinhDo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblA2;
    private javax.swing.JLabel lblB1;
    private javax.swing.JLabel lblKhoaThi;
    private javax.swing.JLabel lblPhongA2;
    private javax.swing.JLabel lblPhongB1;
    private javax.swing.JSpinner spinnerSL;
    private javax.swing.JTable tblPhongThi;
    // End of variables declaration//GEN-END:variables

}