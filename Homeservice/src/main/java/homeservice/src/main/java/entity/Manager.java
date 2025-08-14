package homeservice.src.main.java.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Manager")

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
//@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Person{
}
