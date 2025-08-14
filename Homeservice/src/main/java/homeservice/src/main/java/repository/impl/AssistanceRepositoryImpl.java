package homeservice.src.main.java.repository.impl;


import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.entity.Assistance;
import homeservice.src.main.java.repository.AssistanceRepository;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

public class AssistanceRepositoryImpl extends BaseRepositoryImpl<Assistance> implements AssistanceRepository {

    public AssistanceRepositoryImpl(Class<Assistance> className) {
        super(className);
    }

    @Override
    public Optional<Assistance> findAssistance(String assistanceName) {
        String queryLine = "from Assistance where title=:t";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("t",assistanceName);
        try {
            return Optional.of((Assistance) query.getSingleResult());
        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
