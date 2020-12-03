package project_CS261;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class shopOwner extends loginInfo {
	
	protected String firstName;
	protected String lastName;
        protected String gender;
	protected int age;
	protected String homeAddress;
	protected String email_id;
        protected String contact_number;
        
        
	public shopOwner() {
		super();
		this.firstName="";
		this.lastName="";
		this.age=0;
		this.homeAddress="";
                this.email_id="";
                this.gender="";
                this.contact_number = "";
           
	}
	public shopOwner(String username,String password,String firstName, String lastName, int age, String homeAddress,String email_id,String gender,String contact_number) {
		super(username,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.homeAddress = homeAddress;
                this.email_id=email_id;
                this.gender=gender;
                this.contact_number = contact_number;
        }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
	  set the homeAddress 
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	/*
	 return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}
	/*
	set the email_id 
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
        
        public boolean validateLogin(String user,String password){ 
        boolean flag = false;
        try {
             Connection con = DatabaseConnection.getConnection(); 
            Statement stmt = con.createStatement();
            String query = "select * from shopowner where Username=\"" + user + "\" and Password =\"" + password + "\" ;";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
             {
                 flag = true;
             }
          
            rs.close();
            
        }  catch (SQLException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return flag;
    }

        
        
//    @Override
//    public String toString() {
//        return "shopOwner{" + "firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", age=" + age + ", homeAddress=" + homeAddress + ", email_id=" + email_id + ", contact_number=" + contact_number + '}';
//    }
	
        
}





