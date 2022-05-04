/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungtamtienganh;

import BLL.DiemThiBLL;
import BLL.PhieuDuThiBLL;
import DTO.PhieuDuThiDTO;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Win 10
 */
public class TraCuu extends javax.swing.JPanel {

    /**
     * Creates new form TraCuu
     */
    private DefaultTableModel modelPhieuDuThi;
    private List<PhieuDuThiDTO> listPhieuDuThi;

    private DefaultTableModel modelKetQuaTraCuu;
    private List<String> listKetQuaTraCuu;

    public TraCuu() {
        this.setBounds(0, 0, 844, 620);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        initComponents();

        modelPhieuDuThi = (DefaultTableModel) tblPhieuDuThi.getModel();
        listPhieuDuThi = new ArrayList<>();

        modelKetQuaTraCuu = (DefaultTableModel) tblKetQua.getModel();
        listKetQuaTraCuu = new ArrayList<>();

        ShowPhieuDuThi();
        TimKiem();

        tblPhieuDuThi.setRowHeight(20);
        tblPhieuDuThi.getColumnModel().getColumn(1).setPreferredWidth(200);

        tblKetQua.setRowHeight(20);
        tblKetQua.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblKetQua.getColumnModel().getColumn(1).setPreferredWidth(40);
        tblKetQua.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblKetQua.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblKetQua.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblKetQua.getColumnModel().getColumn(5).setPreferredWidth(10);
        tblKetQua.getColumnModel().getColumn(6).setPreferredWidth(10);
        tblKetQua.getColumnModel().getColumn(7).setPreferredWidth(10);
        tblKetQua.getColumnModel().getColumn(8).setPreferredWidth(10);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //căn giữa cho ô
        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        tblPhieuDuThi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuDuThi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblPhieuDuThi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        tblKetQua.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tblKetQua.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

    }
    
    public void TimKiem() {//tìm kiếm theo họ tên
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblPhieuDuThi.getModel());
        tblPhieuDuThi.setRowSorter(rowSorter);
        txtHoTen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                String text = txtHoTen.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                String text = txtHoTen.getText();
                if (text.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    void ShowPhieuDuThi() {
        listPhieuDuThi = new PhieuDuThiBLL().loadData();
        modelPhieuDuThi.setRowCount(0);
        int i = 1;
        for (PhieuDuThiDTO phieuDuThi : listPhieuDuThi) {
            modelPhieuDuThi.addRow(new Object[]{
                i, phieuDuThi.getHoten(), phieuDuThi.getSdt(), phieuDuThi.getTrinhdo(),});
            i++;
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuDuThi = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKetQua = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        btnTraCuu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Tra cứu thông tin");

        tblPhieuDuThi.setModel(new javax.swing.table.DefaultTableModel(
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
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuDuThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuDuThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuDuThi);

        tblKetQua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Khóa thi", "Tên phòng", "Số báo danh", "Họ tên", "Số điện thoại", "Nghe", "Nói", "Đọc", "Viết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblKetQua);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Họ Tên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Số điện thoại");

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnTraCuu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTraCuu.setText("Tra cứu");
        btnTraCuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraCuuActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Bảng kết quả");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                        .addComponent(btnTraCuu))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel4)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnTraCuu)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTraCuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraCuuActionPerformed
        // TODO add your handling code here:

        String hoten = txtHoTen.getText();
        String sdt = txtSdt.getText();
        if (hoten.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin!");
        } else {
            DiemThiBLL tracuu = new DiemThiBLL();
            listKetQuaTraCuu = tracuu.traCuuThongTin(hoten, sdt);
            modelKetQuaTraCuu.setRowCount(0);
            if (listKetQuaTraCuu.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tạm thời chưa có kết quả!");
            }

            for (String dong_ketqua : listKetQuaTraCuu) {
                String split[] = dong_ketqua.split("/");
                modelKetQuaTraCuu.addRow(new Object[]{
                    split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], split[8]
                });
            }
        }

        txtHoTen.setText("");
        txtSdt.setText("");
    }//GEN-LAST:event_btnTraCuuActionPerformed

    private void tblPhieuDuThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuDuThiMouseClicked
        // TODO add your handling code here:
        int row = tblPhieuDuThi.getSelectedRow();
//        int index = (int) modelPhieuDuThi.getValueAt(0, 0);
        txtHoTen.setText((String) (modelPhieuDuThi.getValueAt(row, 1)));
        txtSdt.setText((String) (modelPhieuDuThi.getValueAt(row, 2)));
    }//GEN-LAST:event_tblPhieuDuThiMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblKetQua;
    private javax.swing.JTable tblPhieuDuThi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables
}
