package homeservice.src.main.java.service;


import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.dto.TechnicianSuggestionDTO;
import java.util.List;

public interface TechnicianSuggestionService {
    List<TechnicianSuggestionDTO> getSuggestionsOf(Order order);
}
