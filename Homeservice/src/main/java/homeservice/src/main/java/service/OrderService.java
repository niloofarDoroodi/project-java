package homeservice.src.main.java.service;


import homeservice.src.main.java.entity.Customer;
import homeservice.src.main.java.entity.Order;
import homeservice.src.main.java.entity.Technician;
import homeservice.src.main.java.entity.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> findRelatedOrders(Technician technician);
    void sendTechnicianSuggestion(Technician technician, Order order);
    List<Order> findByCustomer(Customer customer);
}
