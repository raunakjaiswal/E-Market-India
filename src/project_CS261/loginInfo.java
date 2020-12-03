package project_CS261;

public abstract class loginInfo {
         protected String username;
	protected String password;
	
	public loginInfo() {
		this.username="";
		this.password="";
	}
	public loginInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/*
	  return the username
	 */
	public String getUsername() {
		return username;
	}
	/*
	  Set the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	
	/*
	  return the password
	 */
	public String getPassword() {
		return password;
	}

	/*
	 set the password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
        
        public abstract boolean validateLogin(String username,String password);
        

}
