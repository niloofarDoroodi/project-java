package homeservice.src.main.java.repository.impl;



import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.entity.Manager;
import homeservice.src.main.java.repository.ManagerRepository;

import javax.persistence.Query;

public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager> implements ManagerRepository {

    public ManagerRepositoryImpl(Class<Manager> className) {
        super(className);
    }

    @Override
    public boolean doesManagerExist() {
        String queryLine = "from Manager";
        Query query = entityManager.createQuery(queryLine);
        return !query.getResultList().isEmpty();
    }
}
