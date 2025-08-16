package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String accountNumber;
    private String cardType; // "DEBIT", "CREDIT"




    public Card(String cardNumber, String accountNumber, String cardType) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}