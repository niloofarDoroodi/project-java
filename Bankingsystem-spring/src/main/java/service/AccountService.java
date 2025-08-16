package service;


import entity.Account;
import entity.Transaction;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
//    بخاطر ارور setup در تست
    }
    public Account createAccount(String accountNumber, double initialBalance, Long customerId) {
        Account account = new Account(accountNumber, initialBalance, customerId);
        return accountRepository.save(account);
    }

    public Account getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        transactionRepository.save(new Transaction(accountNumber, amount, "DEPOSIT"));
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        transactionRepository.save(new Transaction(accountNumber, amount, "WITHDRAW"));
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in the source account");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionRepository.save(new Transaction(fromAccountNumber, amount, "TRANSFER"));
        transactionRepository.save(new Transaction(toAccountNumber, amount, "TRANSFER"));
    }

    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}