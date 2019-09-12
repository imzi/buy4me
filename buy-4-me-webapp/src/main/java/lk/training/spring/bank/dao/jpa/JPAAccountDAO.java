package lk.training.spring.bank.dao.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import lk.training.spring.bank.dao.AccountDAO;
import lk.training.spring.bank.data.Account;

@Repository
public class JPAAccountDAO implements AccountDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Account createAccount() {
		Account a = new Account();
		a.setBalance(new BigDecimal("0.00"));
		em.persist(a);
		return a;
	}

	@Override
	public void delete(int accountNumber) {
		em.remove(em.find(Account.class, accountNumber));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAccountsWithLowBalance(BigDecimal lessThanAmount) {
		return em.createQuery("select a from Account a where a.balance < :amount")
				.setParameter("amount", lessThanAmount).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAllAccounts() {
		return em.createQuery("select a from Account a").getResultList();
	}

	@Override
	public Account getAccount(int accountNumber) {
		return em.find(Account.class, accountNumber);
	}

	@Override
	public void update(Account account) {
		em.merge(account);
	}	
}