/*
 * Entity class representing a state.
 */
package com.hartcode.application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "state")
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // Unique identifier for the state

    @Column(name = "name")
    private String name; // Name of the state

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country; // Country to which the state belongs
}
