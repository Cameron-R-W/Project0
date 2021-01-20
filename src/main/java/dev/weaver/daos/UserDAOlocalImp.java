package dev.weaver.daos;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dev.weaver.entities.User;
import util.JDBCConnection;

public class UserDAOlocalImp implements UserDao 
{
	public static Connection conn = JDBCConnection.getConnection();

	//sets employees ID by incrementing idMaker and places them in hash map 
	public User createUser(User emp) 
	{
		try
		{
			String sql = "Call add_user(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, emp.getUserName());
			cs.setString(2, emp.getPassword());
			cs.execute();
			return emp;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return emp;
	}
	
	public void createSuper(User emp)
	{
		try
		{
			String sql = "Call add_super(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, emp.getUserName());
			cs.setString(2, emp.getPassword());
			cs.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//Return user by unique id in database
	public User getUser(int id) 
	{
		try
		{
			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				User u = new User();
				u.setUserID(rs.getInt("ID"));
				u.setUserName(rs.getString("username"));
				u.setPassword(rs.getString("userpass"));
				System.out.println("User: " + u.getUserName() + " found");
				return u;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//Return a list of users from database
	public ArrayList<User> getAllUsers() 
	{		
		try
		{
			ArrayList<User> listOfUsers = new ArrayList<User>();
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				User u = new User();
				u.setUserID(rs.getInt("ID"));
				u.setUserName(rs.getString("username"));
				u.setPassword(rs.getString("userpass"));
				int superuser = rs.getInt("superuser");
				if(superuser == 0)
					u.setSuperUser(false);
				else
					u.setSuperUser(true);
				listOfUsers.add(u);
			}
			return listOfUsers;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//Update users password and user name
	public User upDateUser(User user) 
	{	
		try
		{
			String sql = "Update users SET username = ?, userpass = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, Integer.toString(user.getUserID()));
			ps.executeQuery();
			System.out.println("Updated to: " + user.getUserName() + " " + user.getPassword());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//Delete user
	public boolean deleteUser(int id) 
	{
		try
		{
			String sql = "DELETE users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
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
