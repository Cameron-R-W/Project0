package dev.weaver.daos;

import java.util.ArrayList;
import java.util.List;

import dev.weaver.entities.Account;


public interface AccountDao
{
	//Create Account
	Account createAccount(Account account);
	
	//Read Account
	Account getAccount(int accountID);
	ArrayList<Account> getUserAccounts(int userID);
	
	//Update Account
	Account updateAccount(Account account);
	
	//Delete Account
	boolean deleteAccount(int accountID);
	
}
