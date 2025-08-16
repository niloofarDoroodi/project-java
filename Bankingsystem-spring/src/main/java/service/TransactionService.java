package service;

import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}