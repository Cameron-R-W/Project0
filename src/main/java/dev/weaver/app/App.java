package dev.weaver.app;

import java.util.Scanner;

import dev.weaver.entities.User;
import dev.weaver.services.AccountServiceImpl;
import dev.weaver.services.UserServiceImpl;

public class App {
	private static UserServiceImpl userServices = new UserServiceImpl();
	private static AccountServiceImpl accountServices = new AccountServiceImpl();
	private static String superName = "weaver.cameron@gmail.com";
	private static String superPass = "password";
	
	public static void main(String[] args)
	{
		welcomeMenu();
	}
	
	public static void welcomeMenu()
	{
		Scanner kb = new Scanner(System.in);
		User user = new User();
		String input;
		String buffer;
		String userName = "";
		String userPassword = "";
		int selection;
		boolean exit = false;
		
		//Check if a super user exists already in the database
		if(userServices.checkSuper() == false)
		{
			//Create superUser
			userServices.registerSuper(superName, superPass, true);
		}

		//Loop through application until user exits by inputting 3.
		do
		{
			try
			{
				//Display welcome menu to user
				System.out.println("Revature Banking App.\n" + "---------------------");
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. End\n");
				input = kb.nextLine();
				selection = Integer.parseInt(input);
				
		
				switch(selection)
				{
				case 1: 
					System.out.println("Input user name.");
					userName = kb.nextLine();
					System.out.println("Input user password.");
					userPassword = kb.nextLine();
					user = userServices.registerUser(userName, userPassword);
					break;
				case 2:
					System.out.println("Login UserName: ");
					userName = kb.next();
					System.out.println("Login Password: ");
					userPassword = kb.next();
					user = userServices.login(userName, userPassword);
					if(user.isSuperUser() == true)
						superUserMenu(user);
					else
						userMenu(user);
					break;
				case 3: 
					exit = true;
					break;
				}
			}
			catch(Exception ex)
			{
				System.out.println("Please enter 1 for Register / 2 for Login / 3 to end application.\n\n");
			}
			System.out.println("Press enter to continue....");
			buffer = kb.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}while(exit == false);
		
		kb.close();
	}

	public static void userMenu(User user)
	{
		Scanner kb = new Scanner(System.in);
		String input;
		String buffer;
		int selection;
		boolean exit = false;
		
		System.out.println("Press enter to continue....");
		buffer = kb.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		do
		{
			try
			{
				System.out.println("User Services Menu.\n" + "---------------------");
				System.out.println("1. Create Account");
				System.out.println("2. View Accounts");
				System.out.println("3. Delete Account");
				System.out.println("4. Deposit");
				System.out.println("5. Withdraw");
				System.out.println("6. Logout\n");
				input = kb.nextLine();
				selection = Integer.parseInt(input);
				
				switch(selection)
				{
				case 1:
					accountServices.createAccount(user);
					break;
				case 2:
					accountServices.viewAccounts(user);
					break;
				case 3:
					accountServices.deleteAccount(user);
					break;
				case 4:
					accountServices.depositAccount(user);
					break;
				case 5:
					accountServices.withdrawAccount(user);
					break;
				case 6:
					exit = true;
					break;
				}
			}
			catch(Exception ex)
			{
				System.out.println("Please enter a valid selection.");
			}
			System.out.println("Press enter to continue....");
			buffer = kb.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}while(exit == false);
	}
	
	public static void superUserMenu(User user)
	{
		Scanner kb = new Scanner(System.in);
		String input;
		String buffer;
		int selection;
		boolean exit = false;
		
		System.out.println("Press enter to continue....");
		buffer = kb.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		do
		{
			try
			{
				System.out.println("User Services Menu.\n" + "---------------------");
				System.out.println("1.  Create Account");
				System.out.println("2.  View Accounts");
				System.out.println("3.  Delete Account");
				System.out.println("4.  Deposit");
				System.out.println("5.  Withdraw");
				System.out.println("6.  View Users");
				System.out.println("7.  Create Users");
				System.out.println("8.  Update Users");
				System.out.println("9.  Delete Users");
				System.out.println("10. Logout\n");
				input = kb.nextLine();
				selection = Integer.parseInt(input);
				
				switch(selection)
				{
				case 1:
					accountServices.createAccount(user);
					break;
				case 2:
					accountServices.viewAccounts(user);
					break;
				case 3:
					accountServices.deleteAccount(user);
					break;
				case 4:
					accountServices.depositAccount(user);
					break;
				case 5:
					accountServices.withdrawAccount(user);
					break;
				case 6:
					userServices.viewUsers();
					break;
				case 7:
					userServices.createUser();
					break;
				case 8:
					userServices.updateUser();
					break;
				case 9:
					userServices.deleteUser();
					break;
				case 10:
					exit = true;
					break;
				}
			}
			catch(Exception ex)
			{
				System.out.println("Please enter a valid selection.");
			}
			System.out.println("Press enter to continue....");
			buffer = kb.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}while(exit == false);
	}
}
