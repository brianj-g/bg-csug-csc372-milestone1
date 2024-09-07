/*
 * Module 1 CTA Option #1: Implementing a superclass bank account
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * August 18, 2024
 * 
 */

/**
 * Contains rudimentary unit tests for BankAccount and CheckingAccount classes
 */
public class BankTest {
	
	public static void main(String[] args) {
		
		double amountToAdd = 100.0;
		double amountToSubtract = 75.0;
		double amountOverdraft = 500.00;
		double testBalance = 0.00;
		BankAccount myBankAccount = null;
		myBankAccount = new BankAccount();
		
		CheckingAccount myCheckingAccount = null;
		try {
			myCheckingAccount = new CheckingAccount(0.0075);
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("Using a fallback value for interest rate.");
			myCheckingAccount = new CheckingAccount(0.001);
		}
		
	// Unit tests for BankAccount and CheckingAccount
		
		// Set name and ID
		myBankAccount.setFirstName("Test");
		myBankAccount.setLastName("Account");
		myBankAccount.setAccountID(999);
		
		myCheckingAccount.setFirstName("Test");
		myCheckingAccount.setLastName("Account");
		myCheckingAccount.setAccountID(999);
		
		// Initial balance should be zero
		System.out.println("*** Testing Initial Balances ***");
		System.out.print("BankAccount: ");
		checkMethod(myBankAccount.getBalance(), testBalance);
		System.out.print("CheckingAccount: ");
		checkMethod(myCheckingAccount.getBalance(), testBalance);
		
		System.out.println();
		
		// Test deposit method
		System.out.println("*** Testing deposit ***");
		myBankAccount.deposit(amountToAdd);
		myCheckingAccount.deposit(amountToAdd);
		testBalance += amountToAdd;
		System.out.print("BankAccount: ");
		checkMethod(myBankAccount.getBalance(), testBalance);
		System.out.print("CheckingAccount: ");
		checkMethod(myCheckingAccount.getBalance(), testBalance);
		
		System.out.println();
		
		// Test withdrawal method
		System.out.println("*** Testing withdrawal ***");
		testBalance -= amountToSubtract;
		System.out.print("BankAccount: ");
		try {
			myBankAccount.withdrawal(amountToSubtract);
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		myCheckingAccount.withdrawal(amountToSubtract);
		checkMethod(myBankAccount.getBalance(), testBalance);
		System.out.print("CheckingAccount: ");
		checkMethod(myCheckingAccount.getBalance(), testBalance);
		
		System.out.println();
		
		// Test overdraft
		System.out.println("*** Testing overdraft ***");
		testBalance -= (amountOverdraft + myCheckingAccount.getOverdraftFee());
		myCheckingAccount.withdrawal(amountOverdraft);
		System.out.print("CheckingAccount: ");
		checkMethod(myCheckingAccount.getBalance(), testBalance);
		
		System.out.println();
		
		// Test the summary method
		System.out.println("*** Bank Account Summary ***");
		myBankAccount.accountSummary();
		System.out.println();
		System.out.println("*** Checking Account Summary ***");
		myCheckingAccount.displayAccount();		
	}
	
	/*
	 * Method to check for balance accuracy
	 */
	public static void checkMethod(double balance, double checkValue) {
		if (balance == checkValue) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		System.out.printf("Expected: %.2f / Actual: %.2f\n", checkValue, balance);
	}

}
