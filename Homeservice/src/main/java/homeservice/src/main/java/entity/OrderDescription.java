package homeservice.src.main.java.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import homeservice.src.main.java.entity.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "order_description_sequence")
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDescription extends BaseEntity {
    @Range(min = 0, message = "Price can not be negative")
    @Column(name = "customer_Suggested_Price")
    private long customerSuggestedPrice;
    @NotNull(message = "Customer desired start date must be set")
    @Column(name = "customer_Desired_Date_And_Time")
    private LocalDateTime customerDesiredDateAndTime;
    @NotNull(message = "Brief descriptions of task should be submitted")
    @Column(name = "task_details")
    private String taskDetails;
    @NotNull(message = "Address can not be null")
    private String address;

    public String toString() {
        return "\n\t\t" + super.toString() +
                "\n\t\tcustomer_Suggested_Price = " + this.getCustomerSuggestedPrice() +
                "\n\t\tcustomer_Desired_Date_And_Time = " + BaseEntity.getPersianDateTime(this.getCustomerDesiredDateAndTime()) +
                "\n\t\ttask_Details = " + this.getTaskDetails() +
                "\n\t\taddress = " + this.getAddress();
    }
}
