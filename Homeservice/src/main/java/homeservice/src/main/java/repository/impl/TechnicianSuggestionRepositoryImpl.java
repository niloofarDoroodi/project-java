package homeservice.src.main.java.repository.impl;


import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.TechnicianSuggestion;
import homeservice.src.main.java.repository.TechnicianSuggestionRepository;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class TechnicianSuggestionRepositoryImpl extends BaseRepositoryImpl<TechnicianSuggestion>
implements TechnicianSuggestionRepository {

    public TechnicianSuggestionRepositoryImpl(Class<TechnicianSuggestion> className) {
        super(className);
    }

    @Override
    public Optional<List<TechnicianSuggestion>> findTechnitionSugestions(Order order) {
        String queryLine = "from TechnicianSuggestion where order =:o";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("o",order);
        return Optional.of((List<TechnicianSuggestion>)query.getResultList());

    }
}
