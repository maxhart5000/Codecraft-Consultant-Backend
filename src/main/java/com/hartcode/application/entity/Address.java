/*
 * Entity class representing an address.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Unique identifier for the address

    @Column(name = "street")
    private String street; // Street address

    @Column(name = "city")
    private String city; // City name

    @Column(name = "state")
    private String state; // State or province name

    @Column(name = "country")
    private String country; // Country name

    @Column(name = "post_code")
    private String postCode; // Postal code

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order; // Associated order with this address
}
