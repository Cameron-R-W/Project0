package dev.weaver.entities;

public class Account 
{

	private int userID;
	private int accountID;
	private int balance;

	public Account()
	{
		super();
	}
	public Account(int userID, int balance) 
	{
		super();
		this.userID = userID;
		this.balance = balance;
	}
	public int getUserID() 
	{
		return userID;
	}
	public void setUserID(int userID) 
	{
		this.userID = userID;
	}
	public int getBalance() 
	{
		return balance;
	}
	public void setBalance(int balance) 
	{
		this.balance = balance;
	}
	public int getAccountID() 
	{
		return accountID;
	}
	public void setAccountID(int accountID) 
	{
		this.accountID = accountID;
	}
	@Override
	public String toString() {
		return "Account Number: " + accountID + " balance: " + balance + " UserID: " + userID;
	}
}
