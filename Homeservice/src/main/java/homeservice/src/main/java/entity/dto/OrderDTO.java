package homeservice.src.main.java.entity.dto;


import lombok.Builder;
import homeservice.src.main.java.entity.OrderDescription;
import homeservice.src.main.java.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Builder
public class OrderDTO {
    private long orderId;
    private String subAssistanceTitle;
    private String assistanceTitle;
    private long basePrice;
    private String customerFirstname;
    private String customerLastname;
    private long customerId;
    private LocalDateTime orderDate;
    private OrderDescription orderDescription;

    @Override
    public String toString() {
        return
                "\torder_Id = " + orderId +
                "\n\tsubAssistance_Title = '" + subAssistanceTitle + '\'' +
                "\n\tassistance_Title = '" + assistanceTitle + '\'' +
                "\n\tbase_Price = " + basePrice +
                "\n\tcustomer_First_name = '" + customerFirstname + '\'' +
                "\n\tcustomer_Last_name = '" + customerLastname + '\'' +
                "\n\tcustomer_Id = " + customerId +
                "\n\torder_Date = " + BaseEntity.getPersianDateTime(orderDate) +
                "\n\torder_Description : " + orderDescription ;
    }
}
