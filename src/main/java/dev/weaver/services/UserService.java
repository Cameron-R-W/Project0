package dev.weaver.services;

import dev.weaver.entities.User;

public interface UserService
{
	User registerUser(String username, String password);
	
	User registerSuper(String username, String password, Boolean superUser);
	
	Boolean checkSuper();
	
	User login(String username, String password);
	
	void viewUsers();
	
	void createUser();
	
	void updateUser();
	
	void deleteUser();
}
