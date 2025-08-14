package homeservice.src.main.java.repository.impl;


import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.entity.Customer;
import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.Technician;
import homeservice.src.main.java.repository.OrderRepository;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {

    public OrderRepositoryImpl(Class<Order> className) {
        super(className);
    }

    public Optional<List<Order>> findRelatedOrders(Technician technician){
        String queryLine = """
                                select o from Technician t 
                                join t.subAssistances s 
                                join s.orders o 
                                where t=:tech 
                                and s = o.subAssistance
                                and (o.orderStatus = 'WAITING_FOR_TECHNICIANS_SUGGESTIONS'
                                or o.orderStatus = 'CHOOSING_TECHNICIAN')
                                """;
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("tech",technician);
        return Optional.of((List<Order>)query.getResultList());
    }

    @Override
    public Optional<List<Order>> findByCustomer(Customer customer) {
        String queryLine = "from Order o where o.customer =:c";
        Query query = entityManager.createQuery(queryLine);
        query.setParameter("c",customer);
        return Optional.of((List<Order>)query.getResultList());
    }
}
