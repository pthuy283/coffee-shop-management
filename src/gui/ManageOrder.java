/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import server.DBHelper;

/**
 *
 * @author huypt
 */
public class ManageOrder extends javax.swing.JFrame {

    DBHelper db = new DBHelper();
    Connection con = db.getCon();
    PreparedStatement ps;
    ResultSet rs, rsIDOrder, rsIDProduct, rsEmp, rsPromotions, rsIDCus, rsSearch1, rsSearch2;
    DefaultTableModel tblModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    Vector row, rowSearch, vecIDOrder, vecIDProduct, vecEmp, vecPromotions, vecIDCus;

    public ManageOrder() {
        initComponents();
        //set icon cho jframe
        ImageIcon img = new ImageIcon("image//Order.png");
        this.setIconImage(img.getImage());
        btnReset.setSize(30, 30);
        new SetImage().setImageButton(btnReset, "image//refresh_256.png");
        btnSearch.setSize(30, 30);
        new SetImage().setImageButton(btnSearch, "image//search-512.png");
        tblModel.addColumn("Mã đơn hàng");
        tblModel.addColumn("Sản phẩm");
        tblModel.addColumn("Khách hàng");
        tblModel.addColumn("Số lượng");
        tblModel.addColumn("CTKM");
        tblModel.addColumn("Thời gian");
        tblModel.addColumn("Ngày");
        tblModel.addColumn("Nhân viên");
        tblOrder.setModel(tblModel);
        reloadTable();
        reloadCombobox();
    }

    private void reloadTable() {
        tblModel.getDataVector().removeAllElements();
        try {
            ps = con.prepareStatement("Select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder "
                    + "join Products on OrderDetails.IDProduct=Products.IDProduct "
                    + "Order by OrderDetails.IDOrder DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                row = new Vector();
                row.add(rs.getString("IDOrder"));
                row.add(rs.getString("ProductName"));
                row.add(rs.getString("CusName"));
                row.add(rs.getString("Quantity"));
                row.add(rs.getString("NamePromo"));
                row.add(rs.getString("TimeOrder"));
                row.add(rs.getString("DateOrder"));
                row.add(rs.getString("Username"));
                tblModel.addRow(row);
            }
            tblOrder.setModel(tblModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi:: Không thể kết nối đến máy chủ");
        }
    }

    private void reloadCombobox() {
        cbIDOrder.removeAllItems();
        cbProductName.removeAllItems();
        cbEmpName.removeAllItems();
        cbPromotions.removeAllItems();
        cbCusName.removeAllItems();
        try {
            String url = "Select IDOrder from Orders Order by IDOrder DESC";
            ps = con.prepareStatement(url);
            rsIDOrder = ps.executeQuery();
            while (rsIDOrder.next()) {
                cbIDOrder.addItem(rsIDOrder.getString("IDOrder"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select DISTINCT ProductName from Products Order by ProductName ASC";
            ps = con.prepareStatement(url);
            rsIDProduct = ps.executeQuery();
            while (rsIDProduct.next()) {
                cbProductName.addItem(rsIDProduct.getString("ProductName"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select Username from Employees where Username != 'null'";
            ps = con.prepareStatement(url);
            rsEmp = ps.executeQuery();
            while (rsEmp.next()) {
                cbEmpName.addItem(rsEmp.getString("Username"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select NamePromo from Promotions Order by IDPromo DESC";
            ps = con.prepareStatement(url);
            rsPromotions = ps.executeQuery();
            while (rsPromotions.next()) {
                cbPromotions.addItem(rsPromotions.getString("NamePromo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        }
        try {
            String url = "Select CusName from Customers Order by CusName ASC";
            ps = con.prepareStatement(url);
            rsIDCus = ps.executeQuery();
            cbCusName.addItem("Khách vãng lai");
            while (rsIDCus.next()) {
                cbCusName.addItem(rsIDCus.getString("CusName"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cbIDOrder = new javax.swing.JComboBox();
        cbCusName = new javax.swing.JComboBox();
        cbEmpName = new javax.swing.JComboBox();
        cbProductName = new javax.swing.JComboBox();
        cbPromotions = new javax.swing.JComboBox();
        lbTimeError = new javax.swing.JLabel();
        lbDateError = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý hóa đơn");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblOrder.setAutoscrolls(false);
        tblOrder.setFocusable(false);
        tblOrder.setOpaque(false);
        tblOrder.setRequestFocusEnabled(false);
        tblOrder.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblOrder);

        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã hóa đơn:");

        jLabel2.setText("Sản phẩm:");

        jLabel3.setText("Khách hàng:");

        jLabel4.setText("Thời gian:");

        jLabel5.setText("Nhân viên:");

        jLabel6.setText("Ngày:");

        jLabel7.setText("Chương trình khuyến mãi:");

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbIDOrder.setEditable(true);

        cbCusName.setEditable(true);

        cbEmpName.setEditable(true);

        cbProductName.setEditable(true);

        cbPromotions.setEditable(true);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Thông tin tìm kiếm:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbProductName, 0, 251, Short.MAX_VALUE)
                            .addComponent(cbEmpName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbIDOrder, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCusName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPromotions, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTime)
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbTimeError)
                            .addComponent(lbDateError))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbPromotions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbCusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbIDOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTimeError)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDateError)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reloadTable();
        cbCusName.setSelectedIndex(-1);
        cbIDOrder.setSelectedIndex(-1);
        cbProductName.setSelectedIndex(-1);
        cbEmpName.setSelectedIndex(-1);
        cbPromotions.setSelectedIndex(-1);
        txtTime.setText("");
        txtDate.setText("");
        lbTimeError.setText("");
        lbDateError.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        tblModel.getDataVector().removeAllElements();
        String s1 = (String) cbIDOrder.getSelectedItem();
        String s2 = (String) cbProductName.getSelectedItem();
        String s3 = (String) cbCusName.getSelectedItem();
        String s4 = (String) cbPromotions.getSelectedItem();
        String s5 = txtDate.getText().trim();
        String s6 = txtTime.getText().trim();
        String s7 = (String) cbEmpName.getSelectedItem();
        if (!txtDate.getText().trim().equals("")) {
            while (true) {
                if (!txtDate.getText().trim().matches("([0-9]{0,2}/)?([0-9]{0,2}/)?[0-9]{4}")) {
                    lbDateError.setText("Ngày có dạng: dd/MM/yyyy, MM/yyyy hoặc yyyy.");
                    lbDateError.setForeground(Color.red);
                    tblOrder.removeAll();
                    return;
                } else {
                    lbDateError.setText("");
                    break;
                }
            }
        } else {
            lbDateError.setText("");
        }
        if (!txtTime.getText().trim().equals("")) {
            while (true) {
                if (!txtTime.getText().trim().matches("[0-9]{0,2}:?[0-9]{0,2}?")) {
                    lbTimeError.setText("Thời gian có dạng: hh hoặc hh:mm.");
                    lbTimeError.setForeground(Color.red);
                    tblOrder.removeAll();
                    return;
                } else {
                    lbTimeError.setText("");
                    break;
                }
            }
        } else {
            lbTimeError.setText("");
        }
        String ss = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder "
                + "join Products on OrderDetails.IDProduct=Products.IDProduct "
                + "where OrderDetails.IDOrder LIKE ? and Products.ProductName LIKE ? "
                + "and OrderDetails.CusName LIKE ? and OrderDetails.NamePromo LIKE ? "
                + "and Orders.DateOrder LIKE ? and Orders.TimeOrder LIKE ? and Orders.Username LIKE ? Order by OrderDetails.IDOrder DESC";
        try {
            ps = con.prepareStatement(ss);
            if (cbIDOrder.getSelectedIndex() == -1) {
                ps.setString(1, "%");
            } else {
                ps.setString(1, s1);
            }
            if (cbProductName.getSelectedIndex() == -1) {
                ps.setString(2, "%");
            } else {
                ps.setString(2, s2);
            }
            if (cbCusName.getSelectedIndex() == -1) {
                ps.setString(3, "%");
            } else {
                ps.setString(3, s3);
            }
            if (cbPromotions.getSelectedIndex() == -1) {
                ps.setString(4, "%");
            } else {
                ps.setString(4, s4);
            }
            if (txtDate.getText().trim().equals("")) {
                ps.setString(5, "%");
            } else {
                ps.setString(5, "%" + s5);
            }
            if (txtTime.getText().trim().equals("")) {
                ps.setString(6, "%");
            } else {
                ps.setString(6, s6 + "%");
            }
            if (cbEmpName.getSelectedIndex() == -1) {
                ps.setString(7, "%");
            } else {
                ps.setString(7, s7);
            }
            rsSearch1 = ps.executeQuery();
            if (rsSearch1.next()) {
                ps = con.prepareStatement(ss);
                if (cbIDOrder.getSelectedIndex() == -1) {
                    ps.setString(1, "%");
                } else {
                    ps.setString(1, s1);
                }
                if (cbProductName.getSelectedIndex() == -1) {
                    ps.setString(2, "%");
                } else {
                    ps.setString(2, s2);
                }
                if (cbCusName.getSelectedIndex() == -1) {
                    ps.setString(3, "%");
                } else {
                    ps.setString(3, s3);
                }
                if (cbPromotions.getSelectedIndex() == -1) {
                    ps.setString(4, "%");
                } else {
                    ps.setString(4, s4);
                }
                if (txtDate.getText().trim().equals("")) {
                    ps.setString(5, "%");
                } else {
                    ps.setString(5, "%" + s5);
                }
                if (txtTime.getText().trim().equals("")) {
                    ps.setString(6, "%");
                } else {
                    ps.setString(6, s6 + "%");
                }
                if (cbEmpName.getSelectedIndex() == -1) {
                    ps.setString(7, "%");
                } else {
                    ps.setString(7, s7);
                }
                rsSearch2 = ps.executeQuery();
                while (rsSearch2.next()) {
                    rowSearch = new Vector();
                    rowSearch.add(rsSearch2.getString("IDOrder"));
                    rowSearch.add(rsSearch2.getString("ProductName"));
                    rowSearch.add(rsSearch2.getString("CusName"));
                    rowSearch.add(rsSearch2.getString("Quantity"));
                    rowSearch.add(rsSearch2.getString("NamePromo"));
                    rowSearch.add(rsSearch2.getString("TimeOrder"));
                    rowSearch.add(rsSearch2.getString("DateOrder"));
                    rowSearch.add(rsSearch2.getString("Username"));
                    tblModel.addRow(rowSearch);
                }
                tblOrder.setModel(tblModel);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu.");
                btnResetActionPerformed(evt);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi:: Không thể kết nối đến máy chủ");
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cbCusName;
    private javax.swing.JComboBox cbEmpName;
    private javax.swing.JComboBox cbIDOrder;
    private javax.swing.JComboBox cbProductName;
    private javax.swing.JComboBox cbPromotions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDateError;
    private javax.swing.JLabel lbTimeError;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
