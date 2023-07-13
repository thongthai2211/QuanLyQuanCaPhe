/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import DAO.DoUong_DAO;
import DAO.KhoDoUong_DAO;
import POJO.DoUong;
import POJO.KhoDoUong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;




/**
 *
 * @author Admin
 */
public class QuanLyOrder extends javax.swing.JFrame implements ActionListener, MouseListener {

    /**
     * Creates new form QuanLyOrder
     */
    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
    DoUong_DAO doUongDAO = new DoUong_DAO();
    DefaultTableModel dtm = new DefaultTableModel();
    public QuanLyOrder() {
        initComponents();
        this.setTitle("Quản lý Danh Mục Orders");
        this.setLocationRelativeTo(null);
        String []tieuDe = {"Mã Đồ Uống", "Tên Đồ Uống", "Giá"};
        dtm.setColumnIdentifiers(tieuDe);
        tb_danhSachDoUong.setModel(dtm);
        
        // Tạo các tiêu đề cho bảng tb_Order
        Object[] columnIdentifiers = {"Mã đồ uống", "Tên đồ uống", "Số lượng", "Đơn giá", "Thành tiền"};
        // Khai báo biến toàn cục model
        DefaultTableModel model = new DefaultTableModel(columnIdentifiers, 0);
        // Thiết lập mô hình dữ liệu cho bảng tb_Order
        tb_Order.setModel(model);        
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
    public synchronized void addMouseListener(MouseListener l) {
        tb_danhSachDoUong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int row = tb_danhSachDoUong.getSelectedRow();
                DefaultTableModel model=(DefaultTableModel) tb_danhSachDoUong.getModel();
                txt_DoUong.setText(model.getValueAt(row, 1).toString());
            }    
        }
        );
    }
    public void loadDuLieuTextBox(){
        int row = this.tb_danhSachDoUong.getSelectedRow();
        this.txt_DoUong.setText((String)(tb_danhSachDoUong.getValueAt(row, 1).toString()));
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
    
   

    
    
    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    
    public void luuOrderVaoCSDL() {
        try {
            // Tạo kết nối tới CSDL QL_CaFe
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=QL_CaFe", "sa", "thaint2002");
            
            // Tạo PreparedStatement để thêm dữ liệu vào bảng ChiTiet_HoaDon và tự động tạo giá trị cho cột MaHD
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ChiTiet_HoaDon (MaHD, MaDoUong, TenDoUong, Gia, SoLuong, ThanhTien) VALUES ((SELECT ISNULL(MIN(MaHD), 0) + 1 FROM HoaDon), ?, ?, ?, ?, ?)");

            // Lặp qua các hàng trong bảng tb_Order và thêm dữ liệu vào PreparedStatement
            for (int i = 0; i < tb_Order.getRowCount(); i++) {
                int maDoUong = Integer.parseInt(tb_Order.getValueAt(i, 0).toString());
                String tenDoUong = tb_Order.getValueAt(i, 1).toString();
                int soLuong = Integer.parseInt(tb_Order.getValueAt(i, 2).toString());
                double gia = Double.parseDouble(tb_Order.getValueAt(i, 3).toString());
                double thanhTien = Double.parseDouble(tb_Order.getValueAt(i, 4).toString());
                stmt.setInt(1, maDoUong);
                stmt.setString(2, tenDoUong);
                stmt.setDouble(3, gia);
                stmt.setInt(4, soLuong);
                stmt.setDouble(5, thanhTien);
                stmt.executeUpdate();
            }

            // Đóng kết nối và PreparedStatement
            stmt.close();            
            conn.close();

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!");
        } catch (SQLException ex) {
            // Nếu có lỗi xảy ra, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }
    
    
    public void thanhToan() {
        
        // Tính tổng tiền và kiểm tra nếu chưa nhập tiền khách thì không được thanh toán
        double tongTien = 0;
        for (int i = 0; i < tb_Order.getRowCount(); i++) {
            double thanhTien = Double.parseDouble(tb_Order.getValueAt(i, 4).toString());
            tongTien += thanhTien;
        }
        if (txt_tienKhachDua.getText().isEmpty() || !isNumeric(txt_tienKhachDua.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách đưa!");
            return;
        }
        // Kiểm tra tiền khách đưa có đủ để thanh toán không
        double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText());
        if (tienKhachDua < tongTien) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ để thanh toán!");
            return;
        }

        // Tính tiền thối lại
        double tienThoiLai = tienKhachDua - tongTien;
        txt_tienThoiLai.setText(String.format("%.0f", tienThoiLai));

        // Hiển thị thông báo
        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");

        luuOrderVaoCSDL();

        
        
        // Xóa hết dữ liệu trong bảng và các textfield
        DefaultTableModel model = (DefaultTableModel) tb_Order.getModel();
        model.setRowCount(0);
        txt_tongTien.setText("");
        txt_tienKhachDua.setText("");
        txt_tienThoiLai.setText("");
    }




    
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
    // </editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnTroLai = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Order = new javax.swing.JTable();
        spnSoLuong = new javax.swing.JSpinner();
        btn_themDoUong = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_tongTien = new javax.swing.JTextField();
        btn_thanhToan = new javax.swing.JButton();
        btn_xoaDoUong = new javax.swing.JButton();
        txt_DoUong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_tienKhachDua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tienThoiLai = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_danhSachDoUong = new javax.swing.JTable();
        txt_timKiemTheoTen = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Order");

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
                .addGap(29, 29, 29)
                .addComponent(btnTroLai)
                .addGap(121, 121, 121)
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

        tb_Order.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tb_Order);

        spnSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spnSoLuong.setValue(1);

        btn_themDoUong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_themDoUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/new product.png"))); // NOI18N
        btn_themDoUong.setText("Thêm");
        btn_themDoUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDoUongActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tổng tiền");

        btn_thanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_thanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add to cart.png"))); // NOI18N
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });

        btn_xoaDoUong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_xoaDoUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        btn_xoaDoUong.setText("Xóa");
        btn_xoaDoUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaDoUongActionPerformed(evt);
            }
        });

        txt_DoUong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Số tiền khách đưa");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tiền thối lại");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(txt_DoUong))
                        .addGap(40, 40, 40)
                        .addComponent(btn_themDoUong)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoaDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_tienKhachDua)
                            .addComponent(txt_tienThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(btn_thanhToan)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_DoUong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_themDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_xoaDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_tienThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(txt_timKiemTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_timKiemTheoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void btnTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiActionPerformed
        QuanLyChung qlc = new QuanLyChung();
        qlc.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnTroLaiActionPerformed

    private void tb_danhSachDoUongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_danhSachDoUongMouseClicked
        loadDuLieuTextBox();
    }//GEN-LAST:event_tb_danhSachDoUongMouseClicked

    private void btn_themDoUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDoUongActionPerformed
        DefaultTableModel model = (DefaultTableModel)tb_Order.getModel();
        int row = tb_danhSachDoUong.getSelectedRow();
        if(row == -1) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn một món đồ uống để thêm vào đơn hàng");
            return;
        }
        String ma = tb_danhSachDoUong.getValueAt(row, 0).toString();
        String ten = tb_danhSachDoUong.getValueAt(row, 1).toString();
        int soLuong = (int)spnSoLuong.getValue();
        float donGia = Float.parseFloat(tb_danhSachDoUong.getValueAt(row, 2).toString());
        float thanhTien = soLuong * donGia;
        model.addRow(new Object[]{ma, ten, soLuong, donGia, thanhTien});
        
        // Tính tổng tiền của tất cả các đồ uống trong tb_Order
        float tongTien = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            tongTien += Float.parseFloat(model.getValueAt(i, 4).toString());
        }
        txt_tongTien.setText(String.valueOf(tongTien));
    }//GEN-LAST:event_btn_themDoUongActionPerformed

    private void btn_xoaDoUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaDoUongActionPerformed
        // Lấy chỉ số dòng được chọn trong bảng danh sách đồ uống
        int row = tb_Order.getSelectedRow();
        if (row >= 0) { // Nếu có dòng được chọn
            // Xóa dòng đó khỏi bảng danh sách đồ uống
            DefaultTableModel model = (DefaultTableModel)tb_Order.getModel();
            model.removeRow(row);
        } else { // Nếu không có dòng được chọn
            // Hiển thị thông báo cho người dùng biết
            JOptionPane.showMessageDialog(this, "Hãy chọn một đồ uống để xóa");
        }
    }//GEN-LAST:event_btn_xoaDoUongActionPerformed

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed

        thanhToan();
//        // Tính tổng tiền và kiểm tra nếu chưa nhập tiền khách thì không được thanh toán
//        double tongTien = 0;
//        for (int i = 0; i < tb_Order.getRowCount(); i++) {
//            int maDoUong = (int) tb_Order.getValueAt(i, 0);
//            int soLuong = (int) tb_Order.getValueAt(i, 2);
//            double thanhTien = Double.parseDouble(tb_Order.getValueAt(i, 4).toString());
//            tongTien += thanhTien;
//
//            // Kiểm tra số lượng sản phẩm có đủ để thanh toán không
//            if (KhoDoUong_DAO.checkSoLuongKhoDoUong(maDoUong, soLuong) == false || soLuong < 0) {
//                JOptionPane.showMessageDialog(this, "Sản phẩm " + maDoUong + " đã hết hàng hoặc số lượng không đủ hoặc không hợp lệ. Vui lòng chọn sản phẩm khác!");
//                return;
//            }
//        }
//
//        if (txt_tienKhachDua.getText().isEmpty() || !isNumeric(txt_tienKhachDua.getText())) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách đưa!");
//            return;
//        }
//
//        // Kiểm tra tiền khách đưa có đủ để thanh toán không
//        double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText());
//        if (tienKhachDua < tongTien) {
//            JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ để thanh toán!");
//            return;
//        }
//
//        // Tính tiền thối lại
//        double tienThoiLai = tienKhachDua - tongTien;
//        txt_tienThoiLai.setText(String.format("%.0f", tienThoiLai));
//
//        // Hiển thị thông báo
//        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
//
//        // Giảm số lượng sản phẩm trong cơ sở dữ liệu
//        for (int i = 0; i < tb_Order.getRowCount(); i++) {
//            int maDoUong = (int) tb_Order.getValueAt(i, 0);
//            int soLuong = (int) tb_Order.getValueAt(i, 2);
//            KhoDoUong_DAO.giamSoLuongKhoDoUong(maDoUong, soLuong);
//        }
//
//        // Xóa hết dữ liệu trong bảng và các textfield
//        DefaultTableModel model = (DefaultTableModel) tb_Order.getModel();
//        model.setRowCount(0);
//        txt_tongTien.setText("");
//        txt_tienKhachDua.setText("");
//        txt_tienThoiLai.setText("");
    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        searchDoUong();
    }//GEN-LAST:event_btnTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTroLai;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_themDoUong;
    private javax.swing.JButton btn_xoaDoUong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tb_Order;
    private javax.swing.JTable tb_danhSachDoUong;
    private javax.swing.JTextField txt_DoUong;
    private javax.swing.JTextField txt_tienKhachDua;
    private javax.swing.JTextField txt_tienThoiLai;
    private javax.swing.JTextField txt_timKiemTheoTen;
    private javax.swing.JTextField txt_tongTien;
    // End of variables declaration//GEN-END:variables
}
