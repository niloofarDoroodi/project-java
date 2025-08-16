package controller;


import entity.Account;
import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
//    برای دریافت پارامترهای ورودی از @RequestParam
    public Account createAccount(@RequestParam String accountNumber,
                                 @RequestParam double initialBalance,
                                 @RequestParam Long customerId) {
        return accountService.createAccount(accountNumber, initialBalance, customerId);
    }
//متد برای دریافت اطلاعات حساب 
    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    @PostMapping("/{accountNumber}/deposit")
    public void deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public void withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        accountService.withdraw(accountNumber, amount);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam String fromAccountNumber,
                         @RequestParam String toAccountNumber,
                         @RequestParam double amount) {
        accountService.transfer(fromAccountNumber, toAccountNumber, amount);
    }

    @GetMapping("/{accountNumber}/transactions")
    public List<Transaction> getTransactions(@PathVariable String accountNumber) {
        return accountService.getTransactions(accountNumber);
    }
}