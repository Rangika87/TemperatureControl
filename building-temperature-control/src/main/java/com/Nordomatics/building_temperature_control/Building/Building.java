package com.Nordomatics.building_temperature_control.Building;

import jakarta.persistence.*;



@Entity
@Table
public class Building {



    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1

    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"

    )

    public Long id;

    @Column(nullable = false)
    public String name;

    public Building() {

    }

    public Building(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
