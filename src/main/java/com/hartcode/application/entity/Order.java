/*
 * Entity class representing an order.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; // Unique identifier for the order

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber; // Tracking number for the order

    @Column(name = "total_quantity")
    private int totalQuantity; // Total quantity of items in the order

    @Column(name = "total_price")
    private BigDecimal totalPrice; // Total price of the order

    @Column(name = "status")
    private String status; // Status of the order

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated; // Date and time when the order was created

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated; // Date and time when the order was last updated

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>(); // Items in the order

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // Customer who placed the order

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress; // Shipping address for the order

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress; // Billing address for the order

    /*
     * Method to add an order item to the order.
     * @param item The order item to be added.
     */
    public void add(OrderItem item) {
        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }
    }
}
