/*
 * Entity class representing a customer.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Unique identifier for the customer

    @Column(name = "first_name")
    private String firstName; // First name of the customer

    @Column(name = "last_name")
    private String lastName; // Last name of the customer

    @Column(name = "email")
    private String email; // Email address of the customer

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>(); // Orders associated with the customer

    /*
     * Method to add an order to the customer.
     * @param order The order to be added.
     */
    public void add(Order order) {
        if (order != null) {
            if (orders == null) {
                orders = new HashSet<>();
            }
            orders.add(order);
            order.setCustomer(this);
        }
    }
}
