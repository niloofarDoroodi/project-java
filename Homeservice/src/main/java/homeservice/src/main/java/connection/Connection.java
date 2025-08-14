package homeservice.src.main.java.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Task");
    public final static EntityManager entityManager = emf.createEntityManager();
}
