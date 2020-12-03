package project_CS261;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Shop extends shopOwner {

  public static String ShopUsername;  

    public static String getShopUsername() {
        return ShopUsername;
    }

    public static void setShopUsername(String ShopUsername) {
        Shop.ShopUsername = ShopUsername;
    }
    private String shopName;
    private String shopType;
    private String shortDescription;
    private String shopAdress;
    private String city;
    private String state;
    private int pin_code;


    public Shop(String username, String password, String firstName, String lastName, int age, String homeAddress, String shopName, String shopType, String shortDescription, String shopAdress, String email_id, String gender, String state, String city, String contact_number, int pin_code) {
        super(username, password, firstName, lastName, age, homeAddress, email_id, gender, contact_number);
        this.shopName = shopName;
        this.shopType = shopType;
        this.shortDescription = shortDescription;
        this.shopAdress = shopAdress;
        this.city = city;
        this.state = state;
        this.pin_code = pin_code;
       
    }

    public Shop() {

        super();
        this.shopName = "";
        this.shopType = "";
        this.shortDescription = "";
        this.shopAdress = "";
        this.state = "";
        this.city = "";
        this.pin_code =0;
    }
 public Shop(String checkusername) {

        super();
         try
    {   
       Connection con= DatabaseConnection.getConnection();
       Statement stmt = con.createStatement();
       String query = "select * from shopowner where Username=\""+checkusername+"\" ;";
       ResultSet rs;
       rs = stmt.executeQuery(query);
       if(rs.next())
       {        
                this.username=rs.getString("Username");
		this.firstName = rs.getString("FirstName");
		this.lastName = rs.getString("LastName");
                this.gender=rs.getString("gender");
		this.age = rs.getInt("age");
		this.homeAddress =rs.getString("HomeAddress") ;
		this.email_id = rs.getString("email_id");
		this.contact_number = rs.getString("ContactNumber");
                
                
                 
       }
        rs.close();
        String query2 = "select * from shop where Username=\""+checkusername+"\" ;";
        rs = stmt.executeQuery(query2);
        if(rs.next())
       {        
                this.shopName = rs.getString("Name");
                this.shopType = rs.getString("Type");
                this.shortDescription = rs.getString("description");
                this.shopAdress = rs.getString("address");
		this.city=rs.getString("city");
                this.state=rs.getString("state");
                this.pin_code=rs.getInt("pincode");
                
       }
        rs.close();
        
    }
    catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    
    
    public int getPin_code() {
        return pin_code;
    }

    public void setPin_code(int pin_code) {
        this.pin_code = pin_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /*
	  return the shopName
     */
    public String getShopName() {
        return shopName;
    }

    /*
	  set the shopName 
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /*
	  return the shopType
     */
    public String getShopType() {
        return shopType;
    }

    /*
	  set the shopType to set
     */
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    /*
	  return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /*
	 set the shortDescription 
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /*
	  return the shopAdress
     */
    public String getShopAdress() {
        return shopAdress;
    }

    /*
	 set the shopAdress 
     */
    public void setShopAdress(String shopAdress) {
        this.shopAdress = shopAdress;
    }

    /*
	  return the countOfProducts
     */
//    public int getCountOfProducts() {
//        return countOfProducts;
//    }
//
//    public void incrementCountOfProducts()
//    {
//        countOfProducts++;
//    }
    /*	
	In the case of normal or regular inner classes, without an outer class object existing, 
	there cannot be an inner class object. i.e., 
	an object of the inner class is always strongly associated with an outer class object.
     */
    class products {

        private String name;
        private int costPrice;
        private int retailPrice;
        private String description;
        private String status;
        private int Stock;
        private String brand;
        private String tags;
        
        public products(String name, int costPrice, int retailPrice, String description, String status, int Stock, String brand,String tags) {
            this.name = name;
            this.costPrice = costPrice;
            this.retailPrice = retailPrice;
            this.description = description;
            this.status = status;
            this.Stock = Stock;
            this.brand = brand;
            this.tags = tags;
        }
         public products() {
            this.name = "";
            this.costPrice = 0;
            this.retailPrice = 0;
            this.description = "";
            this.status = "";
            this.Stock = 0;
            this.brand = "";
            this.tags = "";
        }
         public products(String productname,String checkusername)
         {  
             try
         {
             Connection con= DatabaseConnection.getConnection();
       Statement stmt = con.createStatement();
       String query = "select * from products where Username=\"" + checkusername + "\" and name =\"" + productname + "\" ;";
       ResultSet rs;
       rs = stmt.executeQuery(query);
       
         if(rs.next())
       {        
                this.name = rs.getString("name");
                this.costPrice = rs.getInt("costPrice");
                this.retailPrice = rs.getInt("retailPrice");
                this.description = rs.getString("description");
		this.status=rs.getString("status");
                this.Stock=rs.getInt("Stock");
                this.brand=rs.getString("Brand");
                this.tags="";
       }
        rs.close();
        stmt.close();
    }
    catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         }
         
        @Override
        public String toString() {
            return "products{" + "name=" + name + ", costPrice=" + costPrice + ", retailPrice=" + retailPrice + ", description=" + description + ", status=" + status + ", Stock=" + Stock + ", brand=" + brand + ", tags=" + tags + '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(int costPrice) {
            this.costPrice = costPrice;
        }

        public int getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(int retailPrice) {
            this.retailPrice = retailPrice;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStock() {
            return Stock;
        }

        public void setStock(int Stock) {
            this.Stock = Stock;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
        
        
        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }
        public boolean addProduct(Shop.products obj){
     try{
          Connection con = DatabaseConnection.getConnection();
          Statement stmt = con.createStatement();
          String queryExist = "SELECT ID FROM products WHERE name='" + obj.getName() + "';";
          ResultSet resultExist = stmt.executeQuery(queryExist);
          int itemId = 0;
//          SerialBlob blob =  new SerialBlob(itemImage);
          if(resultExist.next()){
              itemId = resultExist.getInt("ID");
          }
          else{
            String queryItem = "INSERT INTO products(Username,name,costPrice,retailPrice,description,status,Stock,Brand) VALUES(" 
                     + "\"" + Shop.getShopUsername()+ "\"," 
                    + "\"" + obj.getName() + "\"," 
                    + "\"" + obj.getCostPrice() + "\","
                    + "\"" + obj.getRetailPrice() + "\","
                    + "\"" + obj.getDescription()+ "\","
                    + "\"" + obj.getStatus()+ "\","
                    + "\"" + obj.getStock()+ "\","
                    + "\"" + obj.getBrand()+ "\""
                    +");";
            stmt.executeUpdate(queryItem);
            
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products;");
            while(rs.next()){
            itemId = rs.getInt("COUNT(*)");
            }
            }
          String label = obj.getTags();
            String[] tags = label.split(",");
            String productName = obj.getName();
            List<String> labelArray = new ArrayList<String>(Arrays.asList(tags));
            labelArray.add(productName);
            for(int i=0;i<labelArray.size();i++){
            String queryLabel = "INSERT INTO itemlabel(LabelName,ItemID) VALUES(" 
                    + "\"" + labelArray.get(i) + "\"," 
                    + "\"" + itemId + "\""
                    +");";
            stmt.executeUpdate(queryLabel);
            }
            stmt.close();
//            con.close();
//            JOptionPane.showMessageDialog(addProduct_ui.this, "Product Added");
      }
      catch (HeadlessException | SQLException ex) {
            Logger.getLogger(registerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
     }   
        
       public void showItems(DefaultTableModel model,JTable tblItems){
        try{
        Connection con = DatabaseConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT *  \n" +
        "FROM   `1516289225`.`products`\n" +
        "WHERE  `1516289225`.`products`.`Username` = '" + Shop.getShopUsername() + "';");
        String name;
        String brand;
        int costPrice;
        int retailPrice;
        int stock;
        String description;
        String status;       
        while(rs.next()){
         name = rs.getString("name");
         brand = rs.getString("Brand");
         costPrice = rs.getInt("costPrice");
         retailPrice = rs.getInt("retailPrice");
         stock = rs.getInt("Stock");
         description = rs.getString("description");
         status = rs.getString("status"); 

         model.insertRow(tblItems.getRowCount(), new Object[]{
            name,
            brand,
            costPrice,
            retailPrice,
            stock,
            description,
            status
         });
        }
         stmt.close();
//         con.close();
        }
        catch (HeadlessException | SQLException ex) {
            Logger.getLogger(Product_List.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
        
        
        
    }

    public boolean updateInfo(String username,String firstName, String lastName, int age, String homeAddress,String email_id,String contact_number,String shopName, String shopType, String shopAdress,String state, String city, int pin_code)
        {
                this.username=username;
                this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.homeAddress = homeAddress;
                this.email_id = email_id;
		this.contact_number = contact_number;
                this.shopName = shopName;
                this.shopType = shopType;
                this.shopAdress = shopAdress;
                this.state=state;
                this.city=city;
                this.pin_code=pin_code;
                
               try {
            // TODO add your handling code here:
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();

            String query = "UPDATE shopowner SET "
                    + "FirstName = \""+firstName+"\","
                    + "LastName = \""+lastName+"\","
                    + "HomeAddress = \""+homeAddress+"\","
                    + "email_id = \""+email_id+"\","
                    + "ContactNumber = \""+contact_number+"\""
                    + " WHERE Username= \""+Shop.getShopUsername()+"\";";
            stmt.executeUpdate(query);
            stmt.close();
            
            Statement stmt2 = con.createStatement();

            String query2 = "UPDATE shop SET "
                    + "Name = \""+shopName+"\","
                    + "Type = \""+shopType+"\","
                    + "address = \""+shopAdress+"\","
                    + "city = \""+city+"\","
                    + "state = \""+state+"\","
                    + "pincode = \""+pin_code+"\""
                    + " WHERE Username= \""+Shop.getShopUsername()+"\";";
            stmt2.executeUpdate(query2);
            stmt2.close();
//                     JOptionPane.showMessageDialog(this, "Data Updatated Succesfully");

        } catch (SQLException ex) {
            Logger.getLogger(edit_shop_profile.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return true;  
                
        }
        
    
    
    @Override
    public String toString() {
        return "shopOwner{" + "firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", age=" + age + ", homeAddress=" + homeAddress + ", email_id=" + email_id + ", contact_number=" + contact_number + '}' + "Shop{" + "shopName=" + shopName + ", shopType=" + shopType + ",\n shortDescription=" + shortDescription + ", shopAdress=" + shopAdress + ", city=" + city + ", \nstate=" + state + ", pin_code=" + pin_code + username + password + '}';
    }

}
