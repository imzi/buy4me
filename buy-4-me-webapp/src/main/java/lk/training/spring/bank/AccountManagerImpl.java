package lk.training.spring.bank;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lk.training.spring.bank.dao.AccountDAO;
import lk.training.spring.bank.data.Account;

@Service("accountManager")
public class AccountManagerImpl implements AccountManager {

	@Autowired
	private AccountDAO accountDAO;
	
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public void chargeForLowBalance(BigDecimal minimumBalance, BigDecimal amount) {
		List<Account> accounts = accountDAO.findAccountsWithLowBalance(minimumBalance);
		for (Iterator<Account> iterator = accounts.iterator(); iterator.hasNext();) {
			Account account = (Account) iterator.next();
			// Check if the balance will go beyond 0. If yes, set the balance to 0
			account.setBalance(account.getBalance().subtract(amount));
			accountDAO.update(account);
		}
	}

	@Override
	@Transactional
	public Account create() {
		return accountDAO.createAccount();
	}

	@Override
	public Account find(int accountNumber) {
		return accountDAO.getAccount(accountNumber);
	}
	
	@Override
	@Transactional
	public void delete(int accountNumber) {
		accountDAO.delete(accountNumber);
	}

	@Override
	@Transactional
	public Account deposit(int accountNumber, BigDecimal amount) {
		Account account = accountDAO.getAccount(accountNumber);
		account.setBalance(account.getBalance().add(amount));
		accountDAO.update(account);
		
		return account;
	}

	@Override
	@Transactional
	public Account withdraw(int accountNumber, BigDecimal amount) {
		Account account = accountDAO.getAccount(accountNumber);
		account.setBalance(account.getBalance().subtract(amount));
		accountDAO.update(account);
		
		return account;
	}
	
	@Override
	@Transactional
	public Account transfer(int accountNumber1, int accountNumber2, BigDecimal amount) {
		Account account1 = accountDAO.getAccount(accountNumber1);
		account1.setBalance(account1.getBalance().subtract(amount));
		accountDAO.update(account1);
		
		Account account2 = accountDAO.getAccount(accountNumber2);
		account2.setBalance(account2.getBalance().add(amount));
		accountDAO.update(account2);
		
		return account1;
	}
}