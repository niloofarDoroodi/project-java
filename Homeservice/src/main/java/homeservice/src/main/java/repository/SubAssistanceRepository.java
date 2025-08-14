package homeservice.src.main.java.repository;


import homeservice.src.main.java.entity.Assistance;
import homeservice.src.main.java.entity.SubAssistance;
import java.util.Optional;

public interface SubAssistanceRepository {
    Optional<SubAssistance> findSubAssistance(String title, Assistance assistance);
}
