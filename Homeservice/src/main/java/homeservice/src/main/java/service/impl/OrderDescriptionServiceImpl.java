package homeservice.src.main.java.service.impl;


import homeservice.src.main.java.basics.baseService.impl.BaseServiceImpl;
import homeservice.src.main.java.entity.OrderDescription;
import homeservice.src.main.java.repository.impl.OrderDescriptionRepositoryImpl;

public class OrderDescriptionServiceImpl extends
        BaseServiceImpl<OrderDescriptionRepositoryImpl, OrderDescription> {

    public OrderDescriptionServiceImpl(OrderDescriptionRepositoryImpl repository) {
        super(repository);
    }

}
