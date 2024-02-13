/*
 * Entity class representing a country.
 */
package com.hartcode.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // Unique identifier for the country

    @Column(name = "code")
    private String code; // Country code

    @Column(name = "name")
    private String name; // Country name

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<State> states; // List of states/provinces in the country
}
