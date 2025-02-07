package com.Nordomatics.building_temperature_control.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TemperatureController {

    @Autowired
    private final TemperatueSrevice temperatueSrevice;

    public TemperatureController(TemperatueSrevice temperatueSrevice)
    {
        this.temperatueSrevice = temperatueSrevice;
    }

    // to get all the sensor data
    @GetMapping(path="/sensorData/")
    public List<SensorData> getSensorData()
    {
         return temperatueSrevice.getSensorData();

    }

    //to get the temperature in a given room no
    @GetMapping("/room/{roomNumber}/temperature")
    public SensorData getTemperatureByRoom(@PathVariable Long roomNumber)
    {

         return temperatueSrevice.getRoomTemperature(roomNumber);

    }

    // to control the temperature captured by room id and current temperature
    @PostMapping("/sensor")
    public String controlTemperature (@RequestBody TemperatureReading reading)
    {

        return temperatueSrevice.controlTemperature(reading);

    }


}
