package com.Nordomatics.building_temperature_control.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemperatureRepository extends JpaRepository<SensorData,Long> {


        Optional<SensorData> findTopByRoomIdOrderByIdDesc(Long roomId);
}
