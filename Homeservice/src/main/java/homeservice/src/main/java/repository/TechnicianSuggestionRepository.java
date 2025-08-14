package homeservice.src.main.java.repository;

import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.TechnicianSuggestion;
import java.util.List;
import java.util.Optional;

public interface TechnicianSuggestionRepository {
    Optional<List<TechnicianSuggestion>> findTechnitionSugestions(Order order);
}
