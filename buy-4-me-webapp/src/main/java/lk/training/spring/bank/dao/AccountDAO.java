package lk.training.spring.bank.dao;

import java.math.BigDecimal;
import java.util.List;

import lk.training.spring.bank.data.Account;

public interface AccountDAO {
	public Account createAccount();
	public Account getAccount(int accountNumber);
	public void update(Account account);
	public void delete(int accountNumber);
	public List<Account> findAllAccounts();
	public List<Account> findAccountsWithLowBalance(BigDecimal lessThanAmount);
}
