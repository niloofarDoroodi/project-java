package homeservice.src.main.java.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import homeservice.src.main.java.entity.enums.TechnicianStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Technician")

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Technician extends Person{
    @Range(min = 0, message = "Credit can not be negative")
    private long credit;
    @ManyToMany(mappedBy = "technicians")
    @Column(name = "sub_Assistances")
    private List<SubAssistance> subAssistances;
    private int score;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "technician_Status")
    private TechnicianStatus technicianStatus;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    @Range(min = 0, message = "Number of finished tasks can not be negative")
    @Column(name = "number_Of_Finished_Tasks")
    private int numberOfFinishedTasks;
    @Column(name = "is_Active")
    private boolean isActive;

    public String toString() {
        return super.toString() +
                "\n\tscore = " + this.getScore() +
                "\n\tcredit = " + this.getCredit() +
                "\n\ttechnician_Status = " + this.getTechnicianStatus() +
                "\n\tnumber_Of_Finished_Tasks = " + this.getNumberOfFinishedTasks() +
                "\n\tis_Active = " + this.isActive() +
                "\n\tsub_Assistances = " + this.subAssistances;
    }
}
