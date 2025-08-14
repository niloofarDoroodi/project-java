package homeservice.src.main.java.repository.impl;


import homeservice.src.main.java.basics.baseRepository.impl.BaseRepositoryImpl;
import homeservice.src.main.java.entity.OrderDescription;

public class OrderDescriptionRepositoryImpl extends BaseRepositoryImpl<OrderDescription> {

    public OrderDescriptionRepositoryImpl(Class<OrderDescription> className) {
        super(className);
    }

}
