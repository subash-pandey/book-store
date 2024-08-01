package org.subash.capstone.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CreateOrderDetailFormBean {
    private Integer orderDetailId;

    private Integer orderId;


    private Integer bookId;

    private Integer quantity;

    private Double price;
}
