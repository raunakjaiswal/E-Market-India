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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kaushalendra
 */
public class Orders {
    
     public void searchCompletedOrders(JPanel contentPanel2) {
        try {
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT COUNT(DISTINCT `1516289225`.`UserPreviousOrders`.`Id`) FROM `1516289225`.`UserPreviousOrders` where UserUsername =\"" + User.getNameOfUser() + "\";");
            int count = 0;
            while (rs3.next()) {
                count = rs3.getInt("COUNT(DISTINCT `1516289225`.`UserPreviousOrders`.`Id`)");
            }
            stmt.close();
          
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt2.executeQuery("SELECT *  \n"
                    + "FROM   `1516289225`.`UserPreviousOrders`\n"
                    + "WHERE  `1516289225`.`UserPreviousOrders`.`UserUsername` = '" + User.getNameOfUser() + "';");

            int id;
            if (rs.next()) {
                id = rs.getInt("Id");
            }
            int TotalValue = 0;
            for (int i = 1; i <= count; i++) {
                TotalValue = 0;
                id = rs.getInt("Id");
                String query2 = "SELECT COUNT(`1516289225`.`UserPreviousOrders`.`Id`)\n"
                        + "FROM `1516289225`.`UserPreviousOrders`\n"
                        + "WHERE `1516289225`.`UserPreviousOrders`.`Id` = '" + id + "' and "+" `1516289225`.`UserPreviousOrders`.`UserUsername` = '" + User.getNameOfUser() + "';";
                Statement stmt3 = con.createStatement();
                ResultSet rs2 = stmt3.executeQuery(query2);
                int count2 = 0;
                while (rs2.next()) {
                    count2 = rs2.getInt("COUNT(`1516289225`.`UserPreviousOrders`.`Id`)");
                }
               Shop.products[] product = new Shop.products[count2];
                String productNames[] = new String[count2];
                int QuantityOrdered[] = new int[count2];
                String comments = rs.getString("Comments");
                String Address = rs.getString("Address");
                String State = rs.getString("State");
                String city = rs.getString("City");
                String seller[] = new String[count2];
                int PinCode = rs.getInt("Pincode");
                
                for (int j = 1; j <= count2; j++) {
                    String shopUsername = rs.getString("ShopUsername");
                    seller[j - 1] = shopUsername;
                    productNames[j - 1] = rs.getString("productname");
                    QuantityOrdered[j - 1] = rs.getInt("Quantity");
                    Shop shopobj = new Shop();
                    product[j - 1] = shopobj.new products(productNames[j - 1], shopUsername);
//                            JOptionPane.showMessageDialog(this,"name "+userobj.getFirstName()+ " Username" + UserUsername + " Comments" + comments+" ProductName"+product[j-1].getName()+" Quantity "+QuantityOrdered[j-1]);
                    TotalValue += QuantityOrdered[j - 1] * product[j - 1].getRetailPrice();
                    rs.next();
                }
               contentPanel2.add(new PreviousOrdersPanel(id, seller, product, count2, QuantityOrdered, comments, TotalValue, Address, State, city, PinCode));
                contentPanel2.repaint();
                contentPanel2.revalidate();
//                      
//                JOptionPane.showMessageDialog(this, "Username" + UserUsername + "Username prev" + UserUsernameprev + " Comments" + comments+" ProductName"+productNames[0]+" Quantity "+QuantityOrdered[0]);

            }

        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(CompletedOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     
     public void SearchShopsOrders(JPanel contentPanel2) {
        try {
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs3 = stmt.executeQuery("SELECT COUNT(DISTINCT `1516289225`.`orders`.`UserUsername`) FROM `1516289225`.`orders`;");
            int count = 0;
            while (rs3.next()) {
                count = rs3.getInt("COUNT(DISTINCT `1516289225`.`orders`.`UserUsername`)");
            }
            stmt.close();
             Statement stmt2 = con.createStatement();
            ResultSet rs = stmt2.executeQuery("SELECT *  \n"
                    + "FROM   `1516289225`.`orders`\n"
                    + "WHERE  `1516289225`.`orders`.`Shopusername` = '" + Shop.getShopUsername() + "';");
             String UserUsername = "";

            if (rs.next()) {
                UserUsername = rs.getString("UserUsername");
                
            }
            int TotalValue=0;
           for (int i = 1; i <= count; i++) {
               TotalValue=0; 
               UserUsername = rs.getString("UserUsername");
                String query2 = "SELECT COUNT(`1516289225`.`orders`.`UserUsername`)\n"
                        + "FROM `1516289225`.`orders`\n"
                        + "WHERE `1516289225`.`orders`.`UserUsername` = '" + UserUsername + "';";
                Statement stmt3 = con.createStatement();
                ResultSet rs2 = stmt3.executeQuery(query2);
                int count2 = 0;
                while (rs2.next()) {
                    count2 = rs2.getInt("COUNT(`1516289225`.`orders`.`UserUsername`)");

                }
                stmt3.close();

              
              
                    Shop.products[] product = new Shop.products[count2];
                        String productNames[]=new String[count2];
                        int QuantityOrdered[]=new int[count2];
                        String comments=rs.getString("Comments");
                        User userobj=new User(UserUsername);
                        for(int j=1;j<=count2;j++)
                        {
                            productNames[j-1]= rs.getString("productname");
                            QuantityOrdered[j-1]=rs.getInt("Quantity");
                            Shop shopobj=new Shop();
                            product[j-1]=shopobj.new products(productNames[j-1],Shop.getShopUsername());
//                            JOptionPane.showMessageDialog(this,"name "+userobj.getFirstName()+ " Username" + UserUsername + " Comments" + comments+" ProductName"+product[j-1].getName()+" Quantity "+QuantityOrdered[j-1]);
                        TotalValue+=QuantityOrdered[j-1]*product[j-1].getRetailPrice();
                            rs.next();
                        }
                        contentPanel2.add(new OrdersPanel(userobj,product,count2,QuantityOrdered,comments,TotalValue));
                        contentPanel2.repaint();
                        contentPanel2.revalidate();
//                      
//                JOptionPane.showMessageDialog(this, "Username" + UserUsername + "Username prev" + UserUsernameprev + " Comments" + comments+" ProductName"+productNames[0]+" Quantity "+QuantityOrdered[0]);
                
            }

             

        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(CurrentOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void showPendingOrders(DefaultTableModel model,JTable tblItems){
    try {
            // TODO add your handling code here:
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();
            String userName = User.getNameOfUser();
            String query ="Select * from orders where UserUsername = \"" + userName + "\";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            String productName = rs.getString("productname");
            int price = rs.getInt("Price");
            int quantity = rs.getInt("Quantity");
            String seller = rs.getString("Shopusername");
            model.insertRow(tblItems.getRowCount(), new Object[]{
             productName,
             price,
             quantity,
             seller
             });
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(user_ui.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

     
     public void orderCompleted(String userName)
     {
         try {
            // TODO add your handling code here:
//            String userName= this.lblUserUsername.getText();
            
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
            
            String query ="Select * from orders where UserUsername = \"" + userName + "\";";
            String query2 ="Select * from UserPreviousOrders where UserUsername = \"" + userName + "\";";
            int id;
            ResultSet rs4 = stmt.executeQuery(query2);
            
            if(rs4.last()==false)
            {
             id=1;
            }
            else
            {
                id=rs4.getInt("Id")+1;    
            }
            
            ResultSet rs3 = stmt.executeQuery(query);
            Statement stmt3 = con.createStatement();
            while(rs3.next())
            {
            String queryAddOrder = "INSERT INTO UserPreviousOrders(Id,UserUsername,ShopUsername,productname,Price,Quantity,Comments,Address,State,City,Pincode) VALUES("
                        + "\"" + id + "\","
                        + "\"" + rs3.getString("UserUsername") + "\","
                        + "\"" + rs3.getString("Shopusername") + "\","
                        + "\"" + rs3.getString("productname") + "\","
                        + "\"" + rs3.getString("Price") + "\","
                        + "\"" + rs3.getString("Quantity") + "\","
                        + "\"" + rs3.getString("Comments") + "\","
                        + "\"" + rs3.getString("Address") + "\","
                        + "\"" + rs3.getString("State") + "\","
                        + "\"" + rs3.getString("City") + "\","
                        + "\"" + rs3.getInt("Pincode") + "\""
                        + ");";
            
            stmt3.executeUpdate(queryAddOrder);
            }
             String queryDelete = "Delete from orders Where UserUsername=\"" + userName + "\";";
             Statement stmt2 = con.createStatement();
             stmt2.executeUpdate(queryDelete);
            
             Statement stmt4 = con.createStatement();
             String query3="Update user SET orderPlaced = '0' Where Username = \""+userName+"\";";
             stmt4.executeUpdate(query3);       
            
        } catch (SQLException ex) {
            Logger.getLogger(OrdersPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
     }
     
    
}
