package homeservice.src.main.java.repository;


import homeservice.src.main.java.entity.Assistance;
import java.util.Optional;

public interface AssistanceRepository {
    Optional<Assistance> findAssistance(String assistanceName);
}
