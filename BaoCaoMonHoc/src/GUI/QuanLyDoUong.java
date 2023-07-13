/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import DAO.DoUong_DAO;
import POJO.DoUong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
public class QuanLyDoUong extends javax.swing.JFrame implements ActionListener, MouseListener {

    /**
     * Creates new form QuanLyDoUong
     */
    DoUong_DAO doUongDAO = new DoUong_DAO();
    public DoUong_DAO du_dao;
    DefaultTableModel dtm = new DefaultTableModel();
    List <DoUong> listdu = new ArrayList<>();
    public QuanLyDoUong() {
        initComponents();
        this.setTitle("Quản Lý Đồ Uống");
        this.setLocationRelativeTo(null);
        String []tieuDe = {"Mã Đồ Uống", "Tên Đồ Uống", "Giá"};
        dtm.setColumnIdentifiers(tieuDe);
        tb_danhSachDoUong.setModel(dtm);
        loadDataDoUong();
    }

    // <editor-fold defaultstate="collapsed" desc="Methods">
    public void loadDataDoUong(){
        List <DoUong> list = DoUong_DAO.LayThongTinDoUong();
        DefaultTableModel modelDU= (DefaultTableModel) tb_danhSachDoUong.getModel();
        modelDU.setRowCount(0);
        for (DoUong du: list) {
          modelDU.addRow(new Object[] {
           du.getMaDoUong(),du.getTenDoUong(),du.getGia()
          });
        }
    }
    public void actionPerformed(ActionEvent e){
    }
    
//    @Override
//    public synchronized void addMouseListener(MouseListener l) {
//        tb_danhSachDoUong.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e){
//                int row = tb_danhSachDoUong.getSelectedRow();
//                DefaultTableModel model=(DefaultTableModel) tb_danhSachDoUong.getModel();
//                txt_maDoUong.setText(model.getValueAt(row, 0).toString());
//                txt_tenDoUong.setText(model.getValueAt(row, 1).toString());
//                txt_giaTien.setText(model.getValueAt(row, 2).toString());
//            }    
//        }
//        );
//    }
    public void clearTextField(){
        txt_maDoUong.setText("");
        txt_maDoUong.setEditable(true);
        txt_tenDoUong.setText("");
        txt_giaTien.setText("");
        txt_maDoUong.requestFocus();
    }
    
    public void loadDuLieuTextBox(){
        int row = this.tb_danhSachDoUong.getSelectedRow();
        this.txt_maDoUong.setText((String)(tb_danhSachDoUong.getValueAt(row, 0).toString()));
        this.txt_maDoUong.setEditable(false);
        this.txt_tenDoUong.setText((String)(tb_danhSachDoUong.getValueAt(row, 1)));
        this.txt_giaTien.setText((String)(tb_danhSachDoUong.getValueAt(row, 2).toString()));
    }
    
    private void searchDoUong() {
        // Lấy nội dung từ JTextField txt_timKiemTheoTen
        String keyword = txt_timKiemTheoTen.getText().trim();

        // Kiểm tra keyword có chứa khoảng trắng hoặc không
        if (keyword.trim().equals("")) {
            loadDataDoUong();
            return;
        }
        // Tạo danh sách kết quả tìm kiếm
        List<DoUong> result = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tb_danhSachDoUong.getModel(); // Lấy data model của JTable
        for (int i = 0; i < model.getRowCount(); i++) {
            DoUong du = new DoUong();
            du.setMaDoUong((int) model.getValueAt(i, 0));
            du.setTenDoUong((String) model.getValueAt(i, 1));
            du.setGia((float) model.getValueAt(i, 2));

            if (String.valueOf(du.getMaDoUong()).contains(keyword.toLowerCase())
                    || du.getTenDoUong().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(du);
            }

        }

        // Hiển thị kết quả lên JTable
        model.setRowCount(0); // xoá tất cả các dòng trong JTable
        for (DoUong du : result) {
            Object[] row = {du.getMaDoUong(), du.getTenDoUong(), du.getGia()};
            model.addRow(row); // thêm dòng mới vào JTable
        }
    }
    
    // </editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_danhSachDoUong = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnTroLai = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_maDoUong = new javax.swing.JTextField();
        txt_tenDoUong = new javax.swing.JTextField();
        txt_giaTien = new javax.swing.JTextField();
        btn_xoaDoUong = new javax.swing.JButton();
        btn_suaDoUong = new javax.swing.JButton();
        btn_themDoUong = new javax.swing.JButton();
        txt_timKiemTheoTen = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_danhSachDoUong.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_danhSachDoUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_danhSachDoUongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_danhSachDoUong);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Đồ Uống");

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
                .addGap(28, 28, 28)
                .addComponent(btnTroLai)
                .addGap(122, 122, 122)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
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
        jLabel3.setText("Mã đồ uống:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tên đồ uống:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Giá tiền:");

        btn_xoaDoUong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_xoaDoUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        btn_xoaDoUong.setText("Xóa");
        btn_xoaDoUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaDoUongActionPerformed(evt);
            }
        });

        btn_suaDoUong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_suaDoUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/view edit delete product.png"))); // NOI18N
        btn_suaDoUong.setText("Sửa");
        btn_suaDoUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaDoUongActionPerformed(evt);
            }
        });

        btn_themDoUong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_themDoUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/new product.png"))); // NOI18N
        btn_themDoUong.setText("Thêm");
        btn_themDoUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDoUongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_giaTien, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txt_maDoUong)
                    .addComponent(txt_tenDoUong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(btn_themDoUong)
                .addGap(18, 18, 18)
                .addComponent(btn_xoaDoUong)
                .addGap(18, 18, 18)
                .addComponent(btn_suaDoUong)
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_maDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_giaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xoaDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_suaDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_timKiemTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_timKiemTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // <editor-fold defaultstate="collapsed" desc="Events">
    private void btnTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiActionPerformed
        QuanLyChung qlc = new QuanLyChung();
        qlc.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnTroLaiActionPerformed

    private void tb_danhSachDoUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_danhSachDoUongMouseClicked
        loadDuLieuTextBox();
    }//GEN-LAST:event_tb_danhSachDoUongMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        searchDoUong();
        clearTextField();
        
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btn_themDoUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDoUongActionPerformed
        // TODO add your handling code here:
        DoUong du =new DoUong();
        du.setMaDoUong(Integer.parseInt(txt_maDoUong.getText()));
        du.setTenDoUong(txt_tenDoUong.getText());
        du.setGia(Integer.parseInt(txt_giaTien.getText()));
        DoUong_DAO du_dao = new DoUong_DAO(); 
        listdu.add(du);
        if(du_dao.ThemDoUong(du)) {
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        }else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        }
        loadDataDoUong();
    }//GEN-LAST:event_btn_themDoUongActionPerformed

    private void btn_xoaDoUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaDoUongActionPerformed
        // TODO add your handling code here:
        int row = tb_danhSachDoUong.getSelectedRow();
         DoUong_DAO du_dao = new DoUong_DAO(); 
         String maDU = (String) tb_danhSachDoUong.getValueAt(row, 0).toString();
         int action =JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa không?","Delete?",JOptionPane.YES_NO_OPTION);
         DefaultTableModel model=(DefaultTableModel) tb_danhSachDoUong.getModel();
         if(action==JOptionPane.YES_OPTION) {
             du_dao.DeleteDoUong(maDU);
         }
         JOptionPane.showMessageDialog(null, "Ok");
         model.removeRow(row);
         loadDataDoUong();
    }//GEN-LAST:event_btn_xoaDoUongActionPerformed

    private void btn_suaDoUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaDoUongActionPerformed
        // TODO add your handling code here:
        int maDoUong = Integer.parseInt(txt_maDoUong.getText());
        String tenDoUong = txt_tenDoUong.getText();
        float giaTien = Float.parseFloat(txt_giaTien.getText());

        DoUong doUong = new DoUong(maDoUong, tenDoUong, giaTien);
        DoUong_DAO doUong_dao = new DoUong_DAO();

        if (doUong_dao.SuaDoUong(doUong)) {
            JOptionPane.showMessageDialog(this, "Sửa đồ uống thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi: Không thể sửa đồ uống.");
        }

        loadDataDoUong();
    }//GEN-LAST:event_btn_suaDoUongActionPerformed
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
            java.util.logging.Logger.getLogger(QuanLyDoUong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyDoUong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyDoUong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyDoUong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyDoUong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JButton btn_suaDoUong;
    private javax.swing.JButton btn_themDoUong;
    private javax.swing.JButton btn_xoaDoUong;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_danhSachDoUong;
    private javax.swing.JTextField txt_giaTien;
    private javax.swing.JTextField txt_maDoUong;
    private javax.swing.JTextField txt_tenDoUong;
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
