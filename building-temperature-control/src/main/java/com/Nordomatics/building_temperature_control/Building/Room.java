package com.Nordomatics.building_temperature_control.Building;

import jakarta.persistence.*;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1

    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"

    )
    public Long id;
    public String name;
    public double desiredTemperature;
    public double currentTemperature;

    public Room(Long id, String name, double desiredTemperature, double currentTemperature) {
        this.id = id;
        this.name = name;
        this.desiredTemperature = desiredTemperature;
        this.currentTemperature = currentTemperature;
    }
    public Room()
    {

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

    public double getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(double desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
