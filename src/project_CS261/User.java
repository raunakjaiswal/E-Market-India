package project_CS261;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class User extends loginInfo {
      public static String NameOfUser;

    public static String getNameOfUser() {
        return NameOfUser;
    }

    public static void setNameOfUser(String NameOfUser) 
    {
        User.NameOfUser = NameOfUser;
    }
	public User(String username, String password,String firstName, String lastName, int age,String gender, String homeAddress, String officeAddress, String email_id,
			String contact_number,String city,int pincode,String state) {
		super(username, password);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.homeAddress = homeAddress;
		this.officeAddress = officeAddress;
		this.email_id = email_id;
		this.contact_number = contact_number;
                this.city=city;
                this.gender=gender;
                this.state=state;
                this.pincode=pincode;
	}

	public User() {
		super();
		this.firstName = "";
		this.lastName = "";
		this.age = 0;
		this.homeAddress = "";
		this.officeAddress = "";
		this.email_id = "";
		this.contact_number = "";
                this.city="";
                this.gender="";
                this.state="";
                this.pincode=0;

	}
           public User(String checkusername)
        {   super();
                try
    {   
       Connection con= DatabaseConnection.getConnection();
       Statement stmt = con.createStatement();
       String query = "select * from user where Username=\""+checkusername+"\" ;";
       ResultSet rs = stmt.executeQuery(query);
       if(rs.next())
       {        this.username=rs.getString("Username");
		this.firstName = rs.getString("FirstName");
		this.lastName = rs.getString("LastName");
		this.age = rs.getInt("age");
		this.homeAddress =rs.getString("HomeAddress") ;
		this.officeAddress =rs.getString("OfficeAddress") ;
		this.email_id = rs.getString("email_id");
		this.contact_number = rs.getString("ContactNumber");
                this.city=rs.getString("city");
                this.gender=rs.getString("Gender");
                this.state=rs.getString("state");
                this.pincode=rs.getInt("pincode");
        
       }
    }
                  
    catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	private String firstName;
	private String lastName;
	private int age;
        private String gender;
	private String homeAddress;
	private String officeAddress;
	private String email_id;
	private String contact_number;
        private String city;
        private String state;
        private int pincode;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    
    public String getUsername() {
		return username;
	}
        
	/*
	  return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/*
	 set the firstName 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/*
	 set the lastName 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	  return the age
	 */
	public int getAge() {
		return age;
	}

	/*
	 set the age 
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/*
	  return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/*
	  set the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/*
	 return the officeAddress
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}

	/*
	 *set the officeAddress 
	 */
        public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	/*
	 * return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	/*
	 *set the email_id 
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/*
	 * return the contact_number
	 */
	public String getContact_number() {
		return contact_number;
	}

	/*
	 * set the contact_number 
	 */
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
        
        public boolean validateLogin(String user,String password){ 
        boolean flag = false;
        try {
            Connection con = DatabaseConnection.getConnection(); 
            Statement stmt = con.createStatement();
            String query = "select * from user where Username=\"" + user + "\" and Password =\"" + password + "\" ;";
            ResultSet rs = stmt.executeQuery(query);
           
            while(rs.next())
             {
                 flag = true;
             }
           
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        return flag;
    }
        public void searchItems(String label,JPanel contentPanel){
        try{
        Connection con = DatabaseConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT *  \n" +
        "FROM   `1516289225`.`products`\n" +
        "JOIN `1516289225`.`itemlabel`\n" +
        "ON `1516289225`.`products`.`id` = `1516289225`.`itemlabel`.`itemid`\n" +
        "WHERE  `1516289225`.`itemlabel`.`labelname` = '" + label + "';");
        String shopUsername;
        String name;
        String brand;
        int retailPrice;
        int stock;
        String description;
        String status;       
        while(rs.next()){
         shopUsername = rs.getString("Username");
         
         Shop shopobj=new Shop(shopUsername);
         name = rs.getString("name");
         Shop.products productobj =shopobj.new products(name,shopobj.getUsername());
         
//         brand = rs.getString("Brand");
//         retailPrice = rs.getInt("retailPrice");
//         description = rs.getString("description");
//         status = rs.getString("status"); 
//         stock = rs.getInt("Stock");

        contentPanel.add(new SearchResultsPanel(productobj,shopUsername));
        contentPanel.repaint();
        contentPanel.revalidate();
        }
        stmt.close();
        }
        catch (HeadlessException | SQLException ex) {
            Logger.getLogger(Search_Results.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
        public boolean updateInfo(String username,String firstName, String lastName, int age, String homeAddress, String officeAddress, String email_id,
			String contact_number,String city,int pincode,String state)
        {
                this.username=username;
                this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.homeAddress = homeAddress;
		this.officeAddress = officeAddress;
		this.email_id = email_id;
		this.contact_number = contact_number;
                this.city=city;
                this.gender=gender;
                this.state=state;
                this.pincode=pincode;
                
                try {
            Connection con = DatabaseConnection.getConnection();
            Statement stmt = con.createStatement();

            String query = "UPDATE user SET "
                    + "FirstName = \""+firstName+"\","
                    + "LastName = \""+lastName+"\","
                    + "age = \""+age+"\","
                    + "city = \""+city+"\","
                    + "state = \""+state +"\","
                    + "pincode = \""+pincode+"\","
                    + "HomeAddress = \""+homeAddress+"\","
                    + "OfficeAddress = \""+officeAddress+"\","
                    + "email_id = \""+email_id+"\","
                    + "ContactNumber = \""+contact_number+"\""
                    + " WHERE Username= \""+username+"\";";
            
            
            stmt.executeUpdate(query);
            stmt.close();
           

        } catch (SQLException ex) {
            Logger.getLogger(edit_shop_profile.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                return true;  
                
        }
        
        
        
        
        
    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", gender=" + gender + ",\n homeAddress=" + homeAddress + ", officeAddress=" + officeAddress + ", email_id=" + email_id + ", contact_number=" + contact_number + ", \ncity=" + city + ", state=" + state + ", pincode=" + pincode + '}';
    }

    
	
}
