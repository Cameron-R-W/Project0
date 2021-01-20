package dev.weaver.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dev.weaver.daos.UserDAOlocalImp;
import dev.weaver.entities.User;

class UserDaoTests {

	private static UserDAOlocalImp userDao = new UserDAOlocalImp();
	
	@Test
	public void addUser()
	{
		System.out.println("Testing addUser");
		User user0 = new User("weaver.cameron@gmail.com","abc123");
		user0 = userDao.createUser(user0);
		
		User user1 = new User("kayla.langdon@gmail.com","def432");
		user1 = userDao.createUser(user1);
		System.out.println("\n\n");
	}

	@Test 
	public void findUser()
	{
		System.out.println("Testing findUser");
		User user0 = userDao.getUser(0);
		User user1 = userDao.getUser(1);
		//User userFail = userDao.getUser(6);
		System.out.println("\n\n");
	}
	
	@Test
	public void updateUser()
	{
		System.out.println("Testing updateUser");
		User user0 = userDao.getUser(0);
		user0.setUserName("cweaver123");
		user0.setPassword("fbdkwls321");
		user0 = userDao.upDateUser(user0);
		System.out.println("\n\n");
	}
	
	@Test
	public void getAllUsers()
	{
		System.out.println("Testing getAllUsers");
		ArrayList<User> users = userDao.getAllUsers();
		for(User u : users)
		{
			System.out.println(u.toString());
		}
		System.out.println("\n\n");
	}
	
	@Test
	public void deleteUser()
	{
		System.out.println("Testing deleteUser");
		User user0 = userDao.getUser(0);
		boolean delete = userDao.deleteUser(0);
		if(delete == true)
		{
			System.out.println("User deleted");
		}
		else
		{
			System.out.println("User not found");
		}
		
		ArrayList<User> users = userDao.getAllUsers();
		for(User u : users)
		{
			System.out.println(u.toString());
		}
		
		
	
	
	}

}
