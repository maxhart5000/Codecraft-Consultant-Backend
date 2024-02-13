/*
 * Entity class representing a product category.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "product_category")
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // Unique identifier for the product category

    @Column(name = "category_name")
    private String categoryName; // Name of the product category

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products; // Set of products belonging to this category
}
