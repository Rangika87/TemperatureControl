package com.Nordomatics.building_temperature_control.Building;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class SensorData {
//    @Id
//    @SequenceGenerator(
//            name = "student_sequence",
//            sequenceName = "student_sequence",
//            allocationSize = 1
//
//    )
//
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "student_sequence"
//
//    )
//    private Long id;
//
//    @Column(nullable = false)
//    private Double temperature; // Measured temperature
//
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//
////    @ManyToOne
////    @JoinColumn(name = "room_id", nullable = false)
//    private Long room_id;
//
//    public SensorData(Long id, Double temperature,  Long room_id) {
//        this.id = id;
//        this.temperature = temperature;
//        this.timestamp = LocalDateTime.now();;
//        this.room_id = room_id;
//    }
//
//    public SensorData() {
//
//    }
//
//    public SensorData( Long room_id,Double temperature) {
//        this.temperature = temperature;
//        this.room_id = room_id;
//        this.timestamp = LocalDateTime.now();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Double getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(Double temperature) {
//        this.temperature = temperature;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public Long getRoom_id() {
//        return room_id;
//    }
//
//    public void setRoom_id(Long room_id) {
//        this.room_id = room_id;
//    }


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
