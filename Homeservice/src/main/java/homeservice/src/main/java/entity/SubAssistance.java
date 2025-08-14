package homeservice.src.main.java.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import homeservice.src.main.java.entity.base.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "sub_assistance_sequence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SubAssistance extends BaseEntity {
    @NotNull(message = "Sub-assistance title can not be null")
    private String title;
    @Range(min = 0, message = "Base price can not be negative")
    @Column(name = "base_Price")
    private long basePrice;
    @ManyToMany
    private List<Technician> technicians;
    @ManyToOne
    private Assistance assistance;
    @OneToMany(mappedBy = "subAssistance")
    private List<Order> orders;
    @NotNull(message = "Sub-assistance should have some descriptions")
    private String about;

    public String toString() {
        return "\n\t\ttitle = " + this.getTitle() +
                "\n\t\tassistance_category = " + this.getAssistance() +
                "\n\t\tbase_Price = " + this.getBasePrice() +
                "\n\t\tabout = " + this.getAbout() + "\n";
    }
}
