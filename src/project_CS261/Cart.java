/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_CS261;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kaushalendra
 */
public class Cart {

    private int TotalValue;

    public int getTotalValue() {
        return TotalValue;
    }

    public void setTotalValue(int TotalValue) {
        this.TotalValue = TotalValue;
    }

    public Cart() {
        this.TotalValue = 0;
    }

    public int table_update(DefaultTableModel model, JTable tblCartItems) {
        try {

            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cart Where Username = \"" + User.getNameOfUser() + "\";");
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                String seller = rs.getString("ShopUsername");
                int total = quantity * price;
                TotalValue += total;
                model.insertRow(tblCartItems.getRowCount(), new Object[]{
                    id,
                    productName,
                    price,
                    quantity,
                    total,
                    seller
                });
            }
            stmt.close();
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(Search_Results.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TotalValue;
    }

    public boolean deleteItem(DefaultTableModel model, JTable tblCartItems) {

        int selectedIndex = tblCartItems.getSelectedRow();

        try {
            int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
            int value = Integer.parseInt(model.getValueAt(selectedIndex, 4).toString());
            TotalValue = TotalValue - value;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the Item", "Warning", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                Connection con = DatabaseConnection.getConnection();
                Statement stmt = con.createStatement();
                String queryDelete = "Delete from Cart where id=" + id + ";";
                stmt.executeUpdate(queryDelete);
                stmt.close();
                model.removeRow(tblCartItems.getSelectedRow());

            }
        } catch (SQLException ex) {
            Logger.getLogger(Productcart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean addItemCart(DefaultTableModel model, JTable tblCartItems, JCheckBox chckBoxCustomAddress, JCheckBox chckboxHomeAddres, JCheckBox chckboxOfficeAddress, JTextField txtAddress, JTextField txtComments, JTextField txtState, JTextField txtCIty, JTextField txtpincode) {

        int numberOfRows = tblCartItems.getRowCount();

        if (numberOfRows == 0) {
//            JOptionPane.showMessageDialog(Productcart.this, "No Items in Cart");
            return false;
        } else {

            for (int i = 0; i < numberOfRows; i++) {

                String UserUsername = User.getNameOfUser();
                String productName = (String) tblCartItems.getValueAt(i, 0);
                int price = (int) tblCartItems.getValueAt(i, 1);
                int quantity = (int) tblCartItems.getValueAt(i, 2);
                String seller = (String) tblCartItems.getValueAt(i, 4);
                String comments = txtComments.getText();
                User user = new User(User.getNameOfUser());
                String address = " ";
                String state = " ";
                String city = " ";
                int pincode = 0;

                address = user.getHomeAddress();
                state = user.getState();
                city = user.getCity();
                pincode = user.getPincode();
                if (chckboxHomeAddres.isSelected()) {
                    address = user.getHomeAddress();
                    state = user.getState();
                    city = user.getCity();
                    pincode = user.getPincode();
                } else if (chckBoxCustomAddress.isSelected()) {
                    address = user.getOfficeAddress();
                    state = user.getState();
                    city = user.getCity();
                    pincode = user.getPincode();

                } else if (chckBoxCustomAddress.isSelected()) {
                    address = txtAddress.getText();
                    state = txtState.getText();
                    city = txtCIty.getText();
                    pincode = Integer.parseInt(txtpincode.getText());
                }

                String queryAddOrder = "INSERT INTO orders(UserUsername,Shopusername,productname,Price,Quantity,Comments,Address,State,City,Pincode) VALUES("
                        + "\"" + UserUsername + "\","
                        + "\"" + seller + "\","
                        + "\"" + productName + "\","
                        + "\"" + price + "\","
                        + "\"" + quantity + "\","
                        + "\"" + comments + "\","
                        + "\"" + address + "\","
                        + "\"" + state + "\","
                        + "\"" + city + "\","
                        + "\"" + pincode + "\""
                        + ");";

                try {
                    Connection con = DatabaseConnection.getConnection();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(queryAddOrder);
                    stmt.close();

                } catch (SQLException ex) {
                    Logger.getLogger(Productcart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            JOptionPane.showMessageDialog(Productcart.this, "Order Placed Successfully");
        }
        return true;
    }

    
        public boolean AddToCart(JLabel txtName,JLabel txtBrand,JLabel txtCostPrice,JComboBox<String> txtQuantity,JLabel txtSeller)
        {
            String username = User.NameOfUser;
            String productName = txtName.getText();
            int costPrice = Integer.parseInt(txtCostPrice.getText());
            int quantity =  Integer.parseInt(txtQuantity.getItemAt(txtQuantity.getSelectedIndex()));         
            String brand = txtBrand.getText();
            String shopUsername = txtSeller.getText();
            try{
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();
            String queryCart = "INSERT INTO Cart(Username,ProductName,Price,Quantity,Brand,ShopUsername) VALUES(" 
                    + "\"" + username + "\"," 
                    + "\"" + productName + "\"," 
                    + "\"" + costPrice + "\"," 
                    + "\"" + quantity + "\","
                    + "\"" + brand + "\","
                    + "\"" + shopUsername+ "\""
                    +");";
            stmt.executeUpdate(queryCart);
            stmt.close();
//            JOptionPane.showMessageDialog(SearchResultsPanel.this, productName + " added to cart");
            }catch (HeadlessException | SQLException ex) {
            Logger.getLogger(registerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            return true;
        }
    
    
}
