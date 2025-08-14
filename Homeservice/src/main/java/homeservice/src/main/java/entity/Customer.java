package homeservice.src.main.java.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Customer")

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    @Range(min = 0, message = "Credit can not be negative")
    private long credit;

    public String toString() {
        return super.toString() +
                "\n\tcredit=" + this.getCredit();
    }
}
