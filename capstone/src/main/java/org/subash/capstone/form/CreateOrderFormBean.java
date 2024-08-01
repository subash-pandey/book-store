package org.subash.capstone.form;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CreateOrderFormBean {

    private Integer orderId;

    private Integer userId;

    private Double totalAmount;

    private String shippingAddressLine1;

    private String shippingAddressLine2;

    private String shippingCity;

    private String shippingState;

    private String shippingZipCode;

    private String shippingCountry;

    private String orderStatus;
}
