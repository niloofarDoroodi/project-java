package test;

import entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    private Account account;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(accountRepository, transactionRepository);
        account = new Account("12345", 1000.0, 1L);
        accountRepository.save(account); //  ذخیره حساب
    }

    @Test
    public void testCreateAccount() {
        Account createdAccount = accountService.createAccount("67890", 2000.0, 2L);

        assertNotNull(createdAccount);
        assertEquals("67890", createdAccount.getAccountNumber());
    }

    @Test
    public void testGetAccount() {
        Account foundAccount = accountService.getAccount("12345");

        assertNotNull(foundAccount);
        assertEquals("12345", foundAccount.getAccountNumber());
    }



    @Test
    public void testDeposit() {
        accountService.deposit("12345", 500.0);

        assertEquals(1500.0, account.getBalance());
        assertEquals(1, transactionRepository.findByAccountNumber("12345").size());
    }

    @Test
    public void testWithdraw_SufficientFunds() {
        accountService.withdraw("12345", 200.0);

        assertEquals(800.0, account.getBalance());
        assertEquals(2, transactionRepository.findByAccountNumber("12345").size());
    }


    @Test
    public void testTransfer_SufficientFunds() {
        Account toAccount = new Account("67890", 500.0, 2L);
        accountRepository.save(toAccount);

        accountService.transfer("12345", "67890", 300.0);

        assertEquals(700.0, account.getBalance());
        assertEquals(800.0, toAccount.getBalance());
        assertEquals(1, transactionRepository.findByAccountNumber("12345").size());
        assertEquals(1, transactionRepository.findByAccountNumber("67890").size());
    }
}

