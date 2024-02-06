package com.hartcode.ecommerce.dto;

import com.hartcode.ecommerce.entity.Address;
import com.hartcode.ecommerce.entity.Customer;
import com.hartcode.ecommerce.entity.Order;
import com.hartcode.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
