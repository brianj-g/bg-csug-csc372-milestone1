/*
 * Module 1 CTA Option #1: Implementing a superclass bank account
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * August 18, 2024
 * 
 */

public class CheckingAccount extends BankAccount {
	private double interestRate = 0.01;
	private double overdraftFee = 30.0;
	
	public CheckingAccount(double interestRate) {
		this.setInterestRate(interestRate);
	}
	
	/**
	 * Subtracts parameter from existing balance.  Calls processWithdrawal if the account is overdrawn.
	 */
	@Override
	public void withdrawal(double amount) {
		if (amount > getBalance()) {
			processWithdrawal(amount);
		} else {
			setBalance(getBalance() - amount);
		}
	}
	
	/**
	 * @param amount Processes a withdrawal with an overdraft fee applied.
	 */
	public void processWithdrawal(double amount) {
		setBalance(getBalance() - (amount + overdraftFee));
		System.out.printf("Account is overdrawn. A fee of $%.2f has been assessed.\n", overdraftFee);
		System.out.printf("Current Balance: %.2f\n", getBalance());
	}


	/**
	 * @return Returns the interest rate 
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate Sets the interest rate
	 */
	public void setInterestRate(double interestRate) {
		if (interestRate < 0) {
			throw new IllegalArgumentException("Interest rate must be a positive decimal value");
		}
		this.interestRate = interestRate;
	}

	/**
	 * @return Returns the overdraft fee
	 */
	public double getOverdraftFee() {
		return overdraftFee;
	}

	/**
	 * @param overdraftFee Changes the overdraft fee
	 */
	public void setOverdraftFee(double overdraftFee) {
		if (overdraftFee < 0) {
			throw new IllegalArgumentException("Overdraft fee must be a positive decimal value");
		}
		this.overdraftFee = overdraftFee;
	}
	
	/**
	 * Displays all account information including superclass fields
	 */
	public void displayAccount() {
		super.accountSummary();
		System.out.printf("Interest Rate: %.2f%%\n", (interestRate * 100.0));
		System.out.printf("Overdraft Cost: $%.2f\n", overdraftFee);
	}
}
