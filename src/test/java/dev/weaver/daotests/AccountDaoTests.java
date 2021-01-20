package dev.weaver.daotests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dev.weaver.daos.AccountDAOlocalImpl;
import dev.weaver.entities.Account;

class AccountDaoTests {
	private static AccountDAOlocalImpl adao = new AccountDAOlocalImpl();
	
	@Test
	public void addAccount()
	{
		System.out.println("Testing addAccount");
		//Create two accounts belonging to User ID: 0
		Account newAccount0 = new Account(0,100);
		adao.createAccount(newAccount0);
		Account newAccount1 = new Account(0,100);
		adao.createAccount(newAccount1);
		System.out.println("\n\n");
	}
	
	@Test
	public void getAccount()
	{
		System.out.println("Testing getAccount");
		
		//Create two new accounts both belonging to User ID: 1
		Account newAccount0 = new Account(1,150);
		adao.createAccount(newAccount0);
		Account newAccount1 = new Account(1,200);
		adao.createAccount(newAccount1);
		
		//Retrieve both accounts
		Account testAccount0 = adao.getAccount(1);
		System.out.println("Account: " + testAccount0.getAccountID() + " found");
		Account testAccount1 = adao.getAccount(2);
		System.out.println("Account: " + testAccount1.getAccountID() + " found");
		System.out.println("\n\n");
	}
	
	@Test
	public void getAllAccounts()
	{
		System.out.println("Testing getAllAccounts");
		
		//Return all accounts belonging to user 0 in a arraylist
		ArrayList<Account> userAccounts = new ArrayList<Account>();
		userAccounts = (ArrayList<Account>) adao.getUserAccounts(0);
		for(Account n : userAccounts)
		{
			System.out.println(n.toString());
		}
		
		System.out.println("\n\n");
	}
	
	@Test
	public void updateAccount()
	{
		System.out.println("Testing updateAccount");
		
		//Create new account to match account id and set balance to update
		Account testUpdate = new Account();
		testUpdate.setAccountID(1);
		testUpdate.setBalance(1000);
		
		//Update account returns the account within the DB to check for null values
		testUpdate = adao.updateAccount(testUpdate);
		System.out.println(testUpdate.toString() + " updated.");
		System.out.println("\n\n");
	}
	
	@Test 
	public void deleteAccount()
	{
		System.out.println("Testing deleteAccount");
		
		//Create two new accounts both belonging to User ID: 6
		Account newAccount0 = new Account(6,150);
		adao.createAccount(newAccount0);
		Account newAccount1 = new Account(6,200);
		adao.createAccount(newAccount1);
		
		//Retrieve both accounts
		Account testAccount0 = adao.getAccount(0);
		System.out.println("Account: " + testAccount0.getAccountID() + " found");
		Account testAccount1 = adao.getAccount(1);
		System.out.println("Account: " + testAccount1.getAccountID() + " found");
		
		//Delete account 0 
		boolean delete = adao.deleteAccount(0);
		if(delete == true)
			System.out.println("Account deleted");
		System.out.println("\n\n");
	}

}
