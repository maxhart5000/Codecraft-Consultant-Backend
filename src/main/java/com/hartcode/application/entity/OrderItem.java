/*
 * Entity class representing an order item.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; // Unique identifier for the order item

    @Column(name = "image_url")
    private String imageUrl; // URL of the image associated with the order item

    @Column(name = "unit_price")
    private BigDecimal unitPrice; // Unit price of the order item

    @Column(name = "quantity")
    private int quantity; // Quantity of the order item

    @Column(name = "product_id")
    private long productId; // ID of the product associated with the order item

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // Order to which the order item belongs
}
