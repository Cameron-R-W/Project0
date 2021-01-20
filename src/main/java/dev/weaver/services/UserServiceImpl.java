package dev.weaver.services;

import dev.weaver.daos.UserDao;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import dev.weaver.daos.UserDAOlocalImp;
import dev.weaver.entities.User;

public class UserServiceImpl implements UserService {

	private static UserDao edao = new UserDAOlocalImp();
	
	public User registerUser(String username, String password) 
	{
		//create user
		User newUser = new User(username, password);
		
		//check for existing user
		ArrayList<User> listOfCurrentUsers = new ArrayList<User>();
		listOfCurrentUsers = (ArrayList<User>) edao.getAllUsers();
		for(User user : listOfCurrentUsers)
		{
			if(user.getUserName().equals(newUser.getUserName()) && user.getPassword().equals(newUser.getPassword()))
			{
				System.out.println("This user already exists.");
				return null;
			}
		}
		
		edao.createUser(newUser);
		System.out.println("User Created");
		return newUser;
	}

	public User login(String username, String password)
	{
		ArrayList<User> listOfUsers = (ArrayList<User>) edao.getAllUsers();
		for(User user : listOfUsers)
		{
			if(user.getUserName().equals(username) && user.getPassword().equals(password))
			{
				System.out.println("User: " + username + " login succesful.");
				return user;
			}
		}
		System.out.println("Incorrect username or password.");
		return null;
	}

//	@Override
	public User registerSuper(String username, String password, Boolean superUser)
	{
		//create user
		User newUser = new User(username, password);
		newUser.setSuperUser(true);
		edao.createSuper(newUser);
		return null;
	}
	
	//Program creates a super user at the start. Checks if super is in DB. If so return true
	@Override
	public Boolean checkSuper()
	{
		User u = edao.getUser(0);
		if(u.equals(null))
		return false;
		else
			return true;
	}

	@Override
	public void viewUsers() 
	{
		ArrayList<User> listOfUsers = (ArrayList<User>) edao.getAllUsers();
		for(User n : listOfUsers)
		{
			System.out.println(n.toString());
		}
	}

	@Override
	public void createUser() 
	{
		Scanner kb = new Scanner(System.in);
		String userName;
		String password;
		System.out.println("Input new user name");
		userName = kb.nextLine();
		System.out.println("Input new user password");
		password = kb.nextLine();
		registerUser(userName, password);
	}

	@Override
	public void updateUser() 
	{
		Scanner keyboard = new Scanner(System.in);
		int ID;
		int selection;
		String userName;
		String password;
		viewUsers();
		System.out.println("Select a user id to update that user.");
		try
		{
			ID = keyboard.nextInt();
			System.out.println("Input new user name");
			userName = keyboard.next();
			
			System.out.println("Input new user password");
			password = keyboard.next();
			User user = new User(ID, userName, password);
			edao.upDateUser(user);
		}
		catch(Exception ex)
		{
			System.out.println("Invalid input");
		}
		
		System.out.println("\n");
	}

	@Override
	public void deleteUser() 
	{
		Scanner keyboard = new Scanner(System.in);
		int ID;
		Boolean delete;
		
		//display users
		viewUsers();
		
		System.out.println("Select a user id to delete that user.");
		ID = keyboard.nextInt();
		delete = edao.deleteUser(ID);
		if(delete == true)
		{
			System.out.println("User deleted");
		}
		else
			System.out.println("Could not delete user");
	}


}
