package homeservice.src.main.java.entity.base;

import com.github.mfathi91.time.PersianDate;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
    private long id;

    public String toString() {
        return "id = " + this.getId();
    }

    public static String getPersianDateTime(LocalDateTime dateTime){
        String[] date = dateTime.toString().split("T");
        return PersianDate.fromGregorian(dateTime.toLocalDate()) + "  " + date[1];
    }
}
