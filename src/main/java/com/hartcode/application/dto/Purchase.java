/*
 * DTO class representing a purchase request.
 */
package com.hartcode.application.dto;

import com.hartcode.application.entity.Address;
import com.hartcode.application.entity.Customer;
import com.hartcode.application.entity.Order;
import com.hartcode.application.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer; // Customer making the purchase
    private Address shippingAddress; // Shipping address for the order
    private Address billingAddress; // Billing address for the order
    private Order order; // Details of the order
    private Set<OrderItem> orderItems; // Items included in the order
}
