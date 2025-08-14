package homeservice.src.main.java.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;
import homeservice.src.main.java.entity.base.BaseEntity;
import homeservice.src.main.java.entity.enums.OrderStatus;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "order_sequence")
@Table(name = "orders")
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @ManyToOne
    private SubAssistance subAssistance;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Technician technician;
    @Column(name = "order_Registration_Date_And_Time")
    private LocalDateTime orderRegistrationDateAndTime;
    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.PERSIST)
    private OrderDescription orderDescription;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Range(min = 1, max = 5, message = "Technician score should be between 1 and 5")
    @Column(name = "technician_score")
    private int technicianScore;
    @OneToMany(mappedBy = "order")
    @Cascade(value = org.hibernate.annotations.CascadeType.MERGE)
    @Column(name = "technician_suggestions")
    private List<TechnicianSuggestion> technicianSuggestions;
    @Column(name = "technician_evaluation")
    private String techEvaluation;


    public String toString() {
        return super.toString() +
                "\n\tsub_Assistance = " + this.getSubAssistance().getTitle() +
                "\n\tassistance = " + this.getSubAssistance().getAssistance().getTitle() +
                "\n\tcustomer = " + this.getCustomer().getId() +
                "\n\ttechnician = " + (this.getTechnician()==null ? "[]": this.getTechnician().getId()) +
                "\n\torder_Registration_Date_And_Time = " + BaseEntity.getPersianDateTime(this.getOrderRegistrationDateAndTime()) +
                "\n\torder_Description = " + this.getOrderDescription() +
                "\n\torder_Status = " + this.getOrderStatus() +
                "\n\ttechnician_Score = " + this.getTechnicianScore() +
                "\n\ttechnician_Suggestions = " + this.getTechnicianSuggestions() +
                "\n\ttechnician_Evaluation = " + this.getTechEvaluation() ;
    }
}
