package dev.weaver.entities;

public class User {
	
	private int userID;
	private String userName;
	private String password;
	private boolean superUser;
	
	public User() 
	{
		super();
	}
	
	public User(String userName, String password)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.userID = userID;
		this.superUser = false;
	}
	
	public User(int userID, String userName, String password) 
	{
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.superUser = false;
	}
	
	public void setSuper(int superUser)
	{
		
	}
	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User ID: " + userID + " User Name: " + userName + " Password: " + password;
	}
	
	
	
}
