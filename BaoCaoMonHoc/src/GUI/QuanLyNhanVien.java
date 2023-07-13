/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import POJO.NhanVien;
import DAO.NhanVien_DAO;
import DAO.TaiKhoan_DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
/**
 *
 * @author Admin
 */
public class QuanLyNhanVien extends javax.swing.JFrame implements ActionListener, MouseListener{
    NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
    public NhanVien_DAO nv_dao;
    DefaultTableModel dtm = new DefaultTableModel();
    List <NhanVien> listnv = new ArrayList<>();
    public QuanLyNhanVien() {
        initComponents();
        this.setTitle("Quản Lý Nhân Viên");
        addMouseListener(this);
        this.setLocationRelativeTo(null);
        String []tieuDe = {"Mã Nhân Viên", "Tên Nhân Viên", "Chức Vụ","Giới Tính","Điện Thoại","Lương"};
        dtm.setColumnIdentifiers(tieuDe);
        tb_danhSachNhanVien.setModel(dtm);
        loadDataNhanVien();
    }

    // <editor-fold defaultstate="collapsed" desc="Methods">
    public void loadDataNhanVien(){
        List <NhanVien> listnv = NhanVien_DAO.LayThongTinNhanVien();
        DefaultTableModel modelDU= (DefaultTableModel) tb_danhSachNhanVien.getModel();
        modelDU.setRowCount(0);
        for (NhanVien nv: listnv) {
          modelDU.addRow(new Object[] {
           nv.getMaNhanVien(),nv.getHoTenNV(),nv.getChucVu(),nv.getGioiTinh(),nv.getDienThoai(),nv.getLuong()
          });
        }
    }
    
    public void clearTextField(){
        txtMaNV.setText("");
        txtMaNV.setEnabled(true);
        txtTenNV.setText("");
        txtChucVu.setText("");
        txtGioiTinh.setText("");
        txt_soDienThoai.setText("");
        txt_Luong.setText("");
        txtMaNV.requestFocus();
    }
    
    
    public void actionPerformed(ActionEvent e){
    }
    @Override
    public synchronized void addMouseListener(MouseListener l) {
        tb_danhSachNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = tb_danhSachNhanVien.getSelectedRow();
                DefaultTableModel model=(DefaultTableModel) tb_danhSachNhanVien.getModel();
                txtMaNV.setText(model.getValueAt(row, 0).toString());
                txtTenNV.setText(model.getValueAt(row, 1).toString());
                txtChucVu.setText(model.getValueAt(row, 2).toString());
                txtGioiTinh.setText(model.getValueAt(row, 3).toString());
                txt_soDienThoai.setText(model.getValueAt(row, 4).toString());
                txt_Luong.setText(model.getValueAt(row, 5).toString());
            }    
        }
        );
    }
    private void searchNhanVien() {
        // Lấy nội dung từ JTextField txt_timKiemTheoTen
        String keyword = txt_timKiemTheoTen.getText().trim();

        // Kiểm tra keyword có chứa khoảng trắng hoặc không
        if (keyword.trim().equals("")) {
            loadDataNhanVien();
            return;
        }
        // Tạo danh sách kết quả tìm kiếm
        List<NhanVien> result = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tb_danhSachNhanVien.getModel(); // Lấy data model của JTable
        for (int i = 0; i < model.getRowCount(); i++) {
            NhanVien nv = new NhanVien();
            nv.setMaNhanVien((int) model.getValueAt(i, 0));
            nv.setHoTenNV((String) model.getValueAt(i, 1));
            nv.setChucVu((String) model.getValueAt(i, 2));
            nv.setGioiTinh((String) model.getValueAt(i, 3));
            nv.setDienThoai((int) model.getValueAt(i, 4));
            nv.setLuong((int) model.getValueAt(i, 5));
            if (String.valueOf(nv.getMaNhanVien()).contains(keyword.toLowerCase())
                    || nv.getHoTenNV().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(nv);
            }

        }

        // Hiển thị kết quả lên JTable
        model.setRowCount(0); // xoá tất cả các dòng trong JTable
        for (NhanVien nv : result) {
            Object[] row = {nv.getMaNhanVien(), nv.getHoTenNV(), nv.getChucVu(), nv.getGioiTinh(), nv.getDienThoai(), nv.getLuong()};
            model.addRow(row); // thêm dòng mới vào JTable
        }
    }





    
    // </editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_danhSachNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnTroLai = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();
        btn_xoaNV = new javax.swing.JButton();
        btn_suaNV = new javax.swing.JButton();
        btn_themNV = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_Luong = new javax.swing.JTextField();
        txtChucVu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_timKiemTheoTen = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_danhSachNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_danhSachNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_danhSachNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_danhSachNhanVien);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nhân Viên");

        btnTroLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTroLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Go Back_50px.png"))); // NOI18N
        btnTroLai.setText("Trở lại");
        btnTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnTroLai)
                .addGap(118, 118, 118)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTroLai, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tên nhân viên:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Giới Tính:");

        btn_xoaNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_xoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        btn_xoaNV.setText("Xóa");
        btn_xoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaNVActionPerformed(evt);
            }
        });

        btn_suaNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_suaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/view edit delete product.png"))); // NOI18N
        btn_suaNV.setText("Sửa");
        btn_suaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaNVActionPerformed(evt);
            }
        });

        btn_themNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_themNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/new product.png"))); // NOI18N
        btn_themNV.setText("Thêm");
        btn_themNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themNVActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Số điện thoại:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Lương:");
        jLabel7.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Chức vụ:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(btn_themNV)
                .addGap(18, 18, 18)
                .addComponent(btn_xoaNV)
                .addGap(18, 18, 18)
                .addComponent(btn_suaNV)
                .addGap(65, 65, 65))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtChucVu)
                    .addComponent(txtMaNV)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_soDienThoai)
                    .addComponent(txt_Luong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_suaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themNV, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        btnTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(txt_timKiemTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_timKiemTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // <editor-fold defaultstate="collapsed" desc="Events">
    private void btnTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiActionPerformed
        QuanLyChung qlc = new QuanLyChung();
        qlc.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnTroLaiActionPerformed
    
    private void tb_danhSachNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_danhSachNhanVienMouseClicked
//        loadDuLieuLenTextBox();
    }//GEN-LAST:event_tb_danhSachNhanVienMouseClicked

    private void btn_themNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themNVActionPerformed
        NhanVien nv =new NhanVien();
        nv.setMaNhanVien(Integer.parseInt(txtMaNV.getText()));
        nv.setHoTenNV(txtTenNV.getText());
        nv.setChucVu(txtChucVu.getText());
        nv.setGioiTinh(txtGioiTinh.getText());
        nv.setDienThoai(Integer.parseInt(txt_soDienThoai.getText()));
        nv.setLuong(Integer.parseInt(txt_Luong.getText()));
        NhanVien_DAO nv_dao = new NhanVien_DAO(); 
        listnv.add(nv);
        if(nv_dao.ThemNhanVien(nv)) {
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        }else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        }
        loadDataNhanVien();
    }//GEN-LAST:event_btn_themNVActionPerformed

    private void btn_xoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaNVActionPerformed
        int row = tb_danhSachNhanVien.getSelectedRow();
        NhanVien_DAO nv_dao = new NhanVien_DAO(); 
        String MaNV = (String) tb_danhSachNhanVien.getValueAt(row, 0).toString();
        int action = JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa không?","Delete?",JOptionPane.YES_NO_OPTION);
        DefaultTableModel model=(DefaultTableModel) tb_danhSachNhanVien.getModel();
        if (action == JOptionPane.YES_OPTION) {
            // Xóa tài khoản của nhân viên
            TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();
            tk_dao.DeleteTaiKhoan(MaNV);

            // Xóa thông tin của nhân viên
            nv_dao.DeleteNhanVien(MaNV);

            // Cập nhật giao diện
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            model.removeRow(row);
            loadDataNhanVien();
        }
    }//GEN-LAST:event_btn_xoaNVActionPerformed

    private void btn_suaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaNVActionPerformed
        NhanVien nv=new NhanVien();
        nv.setMaNhanVien(Integer.parseInt(txtMaNV.getText()));
        nv.setHoTenNV(txtTenNV.getText());
        nv.setChucVu(txtChucVu.getText());
        nv.setGioiTinh(txtGioiTinh.getText());
        nv.setDienThoai(Integer.parseInt(txt_soDienThoai.getText()));
        nv.setLuong(Integer.parseInt(txt_Luong.getText()));

        nv_dao= new  NhanVien_DAO();

        listnv.add(nv);
        if(nv_dao.SuaNhanVien(nv)) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        }else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
        loadDataNhanVien();
    }//GEN-LAST:event_btn_suaNVActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        searchNhanVien();
        
        // Reset JTextField tìm kiếm
        clearTextField();
    }//GEN-LAST:event_btnTimKiemActionPerformed
    // </editor-fold>
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JButton btn_suaNV;
    private javax.swing.JButton btn_themNV;
    private javax.swing.JButton btn_xoaNV;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_danhSachNhanVien;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txt_Luong;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_timKiemTheoTen;
    // End of variables declaration//GEN-END:variables
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
