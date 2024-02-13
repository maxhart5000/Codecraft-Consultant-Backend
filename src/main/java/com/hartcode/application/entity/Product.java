/*
 * Entity class representing a product.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Unique identifier for the product

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category; // Category to which the product belongs

    @Column(name = "sku")
    private String sku; // Stock Keeping Unit (SKU) of the product

    @Column(name = "name")
    private String name; // Name of the product

    @Column(name = "description")
    private String description; // Description of the product

    @Column(name = "unit_price")
    private BigDecimal unitPrice; // Unit price of the product

    @Column(name = "image_url")
    private String imageUrl; // URL of the image associated with the product

    @Column(name = "active")
    private boolean active; // Flag indicating whether the product is active or not

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated; // Date and time when the product was created

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated; // Date and time when the product was last updated
}
