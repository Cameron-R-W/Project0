package dev.weaver.daos;

import java.util.List;
import java.util.Map;
import java.util.Set;

import dev.weaver.entities.User;


public interface UserDao
{
	//Create
	User createUser(User user);
	void createSuper(User user);
	
	//Read
	User getUser(int id);
	List<User> getAllUsers();
	
	//Update
	User upDateUser(User user);
	
	//Delete
	boolean deleteUser(int id);
	
}
