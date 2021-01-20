package dev.weaver.services;

import java.util.ArrayList;
import java.util.Scanner;

import dev.weaver.daos.AccountDAOlocalImpl;
import dev.weaver.entities.Account;
import dev.weaver.entities.User;

public class AccountServiceImpl implements AccountService{

	private static AccountDAOlocalImpl adao = new AccountDAOlocalImpl();
	
	@Override
	public void viewAccounts(User user) {
		//Retrieve list of accounts from DB
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = adao.getUserAccounts(user.getUserID());
		
		//If null then the list came back empty and this user has no accounts
		if(accounts.equals(null))
		{
			System.out.println("No accounts have been created for this user yet.");
		}
		else
		{
			for(Account n : accounts)
			{
				System.out.println(n.toString());
			}
			
		}
	}

	@Override
	public void createAccount(User user) {
		Account newAccount = new Account(user.getUserID(),0);
		adao.createAccount(newAccount);
	}

	@Override
	public void deleteAccount(User user) {
		viewAccounts(user);
		System.out.println("--------------------------------\n");
		Scanner kb = new Scanner(System.in);
		Boolean delete;
		int ID;
		
		//Catch any user error inputs
		try
		{
			System.out.println("Input an account number to delete");
			ID = kb.nextInt();
			Account account = adao.getAccount(ID);
			
			if(account.getBalance() > 0)
			{
				System.out.println("Unable to delete account");
			}
			else
			{
				delete = adao.deleteAccount(ID);
				if(delete == true)
					System.out.println("Account: " + ID + " deleted");
				else
					System.out.println("Unable to delete account");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Invalid Input");
		}
	}

	@Override
	public void depositAccount(User user) {
		viewAccounts(user);
		System.out.println("--------------------------------\n");
		Scanner kb = new Scanner(System.in);
	
		int ID;
		int amount;
		//Catch any user error inputs
		try
		{
			//Retrieve user selected account
			System.out.println("Input an account number to deposit");
			ID = kb.nextInt();
			Account account = adao.getAccount(ID);
			
			//Set account balance to amount + current balance
			System.out.println("Input an amount to deposit.");
			amount = kb.nextInt();
			amount += account.getBalance();
			account.setBalance(amount);
			
			//Update account in DB
			adao.updateAccount(account);
			System.out.println("Account: " + account.getAccountID() + " balance updated to: " + account.getBalance());
		}
		catch(Exception ex)
		{
			System.out.println("Invalid Input");
		}
	}

	@Override
	public void withdrawAccount(User user) {
		viewAccounts(user);
		System.out.println("--------------------------------\n");
		Scanner kb = new Scanner(System.in);
	
		int ID;
		int amount;
		//Catch any user error inputs
		try
		{
			//Retrieve user selected account
			System.out.println("Input an account number to withdraw");
			ID = kb.nextInt();
			Account account = adao.getAccount(ID);
			
			//Set account balance to amount + current balance
			System.out.println("Input an amount to withdraw.");
			amount = kb.nextInt();
			amount = account.getBalance() - amount;
			if(amount < 0 )
			{
				System.out.println("Sorry you're broke. Not enough $$$$.");
			}
			else
			{
			account.setBalance(amount);
			
			//Update account in DB
			adao.updateAccount(account);
			System.out.println("Account: " + account.getAccountID() + " balance updated to: " + account.getBalance());
			}
		}
		catch(Exception ex)
		{
			System.out.println("Invalid Input");
		}
	}

}
