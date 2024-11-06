/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package InterFace;

import Proccess.LoaiSP;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author haidu
 */
public class frmSPTheoLoai extends javax.swing.JFrame {

     LoaiSP sp = new LoaiSP();
    DefaultTableModel tableModel = new DefaultTableModel();

    // Hàm đổ dữ liệu vào ComboBox
    public final void ShowDataCombo() {
        ResultSet result = null;
        try {
            result = sp.ShowLoaiSP(); // Lấy dữ liệu từ phương thức ShowLoaiSP trong lớp LoaiSP
            while (result.next()) {
                // Thêm dữ liệu vào ComboBox
                cboLoaiSP.addItem(result.getString("Maloai"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra màn hình console nếu có lỗi
        } finally {
            // Đóng ResultSet khi hoàn thành để tránh rò rỉ tài nguyên
            try {
                if (result != null) result.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        }
    public final void ShowData(String ml) {
        ResultSet result = null;
        result = sp.ShowSPTheoloai(ml); // Gọi hàm ShowSPTheoloai() với mã loại sản phẩm (ml)

        try {
            ClearData(); // Xóa dữ liệu cũ trong bảng
            while (result.next()) {
                String rows[] = new String[4];
                rows[0] = result.getString(1); // MaSP
                rows[1] = result.getString(2); // TenSP
                rows[2] = result.getString(3); // Dongia
                rows[3] = result.getString(4); // Tenloai
                tableModel.addRow(rows); // Thêm hàng mới vào tableModel
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy dữ liệu sản phẩm: " + e.getMessage());
        }
    }

    public void ClearData() {
    tableModel.setRowCount(0); // Đặt số hàng của tableModel về 0 để xóa toàn bộ dữ liệu
}
    
    public frmSPTheoLoai() {
        initComponents();
        ShowDataCombo(); // Đổ dữ liệu vào ComboBox khi khởi tạo JFrame
        // Khởi tạo tiêu đề các cột của bảng
        String[] colsName = {"Mã SP", "Tên SP", "Giá bán", "Loại"};

        // Đặt tiêu đề cột cho tableModel
        tableModel.setColumnIdentifiers(colsName);

        // Kết nối jTableSanpham với tableModel
        JtableLoaiSP.setModel(tableModel);
    }
    private void cboLoaiSPItemStateChanged(java.awt.event.ItemEvent evt) {
    String ml=cboLoaiSP.getSelectedItem().toString();
    ShowData( ml);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtableLoaiSP = new javax.swing.JTable();
        btThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SẢN PHẨM THEO LOẠI");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Chọn loại sản phẩm: ");

        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        JtableLoaiSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên Sản Phẩm", "Đơn giá", "Loại SP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JtableLoaiSP);

        btThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btThoat.setText("THOÁT");
        btThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(btThoat)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btThoat)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
        // TODO add your handling code here:
        String selectedLoai = (String) cboLoaiSP.getSelectedItem();
    if (selectedLoai != null) {
        ShowData(selectedLoai);
    }
          
    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void btThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btThoatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Thiết lập giao diện người dùng và khởi chạy ứng dụng
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new frmSPTheoLoai().setVisible(true); // Giả sử MainFrame là JFrame chính của bạn
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JtableLoaiSP;
    private javax.swing.JButton btThoat;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
