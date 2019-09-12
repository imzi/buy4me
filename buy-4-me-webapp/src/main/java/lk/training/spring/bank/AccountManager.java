package lk.training.spring.bank;

import java.math.BigDecimal;

import lk.training.spring.bank.data.Account;

public interface AccountManager {

	public Account create();
	
	public Account find(int accountNumber);
	
	public Account deposit(int accountNumber, BigDecimal amount);
	public Account withdraw(int accountNumber, BigDecimal amount);
	
	public void delete(int accountNumber);
	
	/** Returns the first account */
	public Account transfer(int accountNumber1, int accountNumber2, BigDecimal amount);

	public void chargeForLowBalance(BigDecimal minimumBalance, BigDecimal amount);
	
}
