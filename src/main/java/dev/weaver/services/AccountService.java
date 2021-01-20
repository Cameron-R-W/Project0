package dev.weaver.services;

import dev.weaver.entities.User;

public interface AccountService {

	public void viewAccounts(User user);
	
	public void createAccount(User user);
	
	public void deleteAccount(User user);
	
	public void depositAccount(User user);
	
	public void withdrawAccount(User user);

}
