package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private double amount;
    private String type; // "DEPOSIT", "WITHDRAW", "TRANSFER"
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;


    public Transaction(String accountNumber, double amount, String type) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.timestamp = new Date();
    }

    public Transaction(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}