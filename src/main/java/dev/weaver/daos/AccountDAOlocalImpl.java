package dev.weaver.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.weaver.entities.Account;
import util.JDBCConnection;

public class AccountDAOlocalImpl implements AccountDao
{
	public static Connection conn = JDBCConnection.getConnection();

	//Takes in account sets a unique ID for account and store it within the database
	@Override
	public Account createAccount(Account account) 
	{
		try
		{
			String sql = "CALL add_account(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(account.getBalance()));
			cs.setString(2, Integer.toString(account.getUserID()));
			cs.execute();
			System.out.println("Account created");
			return account;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return account;
	}
	
	//Returns a list of all user accounts by matching userID associated with accounts
	@Override
	public ArrayList<Account> getUserAccounts(int userID) 
	{
		ArrayList<Account> userAccounts = new ArrayList<Account>();
		try
		{
			String sql = "SELECT * FROM account";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("userID") == userID)
				{
					Account a = new Account();
					a.setAccountID(rs.getInt("ID"));
					a.setBalance(rs.getInt("balance"));
					a.setUserID(rs.getInt("userID"));
					userAccounts.add(a);
				}
			}
			return userAccounts;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//Returns one account by matching the account unique ID. If No account found returns null
	@Override
	public Account getAccount(int accountID) 
	{
		try
		{
			String sql = "SELECT * FROM account WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountID));
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Account a = new Account();
				a.setAccountID(rs.getInt("id"));
				a.setBalance(rs.getInt("balance"));
				a.setUserID(rs.getInt("userID"));
				return a;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//Update the accounts balance by matching account unique ID to the account in the DB
	//Returns null if no account was found
	@Override
	public Account updateAccount(Account account) 
	{
		try
		{
			String sql = "UPDATE account SET balance = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(account.getBalance()));
			ps.setString(2, Integer.toString(account.getAccountID()));
			ps.executeQuery();
			return account;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//Return true if account was found and then deleted or false if not found
	@Override
	public boolean deleteAccount(int accountID) 
	{
		try
		{
			String sql = "DELETE account WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(accountID));
			ps.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}


}
