package com.Nordomatics.building_temperature_control.Building;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomId;

    private double currentTemperature;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime timestamp;

    // Default constructor (required by JPA)
    public SensorData() {
        this.timestamp = LocalDateTime.now(); // Assign current timestamp in Java
    }

    public SensorData(Long roomId, double currentTemperature) {
        this.roomId = roomId;
        this.currentTemperature = currentTemperature;
        this.timestamp = LocalDateTime.now(); // Assign timestamp on object creation
    }


    // Getters and setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
