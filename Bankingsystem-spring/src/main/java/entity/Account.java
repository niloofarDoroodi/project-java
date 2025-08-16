package entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Account(String accountNumber, double initialBalance, Long customerId) {}

    public Account(String accountNumber, double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }
}
