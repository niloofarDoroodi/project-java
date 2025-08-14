package homeservice.src.main.java.repository;


import homeservice.src.main.java.entity.Customer;
import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.Technician;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<List<Order>> findRelatedOrders(Technician technician);
    Optional<List<Order>> findByCustomer(Customer customer);
}
