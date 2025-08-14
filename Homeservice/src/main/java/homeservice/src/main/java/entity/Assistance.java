package homeservice.src.main.java.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import homeservice.src.main.java.entity.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "assistance_sequence")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Assistance extends BaseEntity {
    @NotNull(message = "Assistance title can not be null")
    private String title;

    public String toString() {
        return  this.getTitle() ;
    }
}
