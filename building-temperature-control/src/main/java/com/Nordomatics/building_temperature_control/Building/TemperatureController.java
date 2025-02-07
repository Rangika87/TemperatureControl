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

    public TemperatureController(TemperatueSrevice temperatueSrevice) {
        this.temperatueSrevice = temperatueSrevice;
    }


    @GetMapping(path="/sensorData/")
    public List<SensorData> getSensorData()
    {
         return temperatueSrevice.getSensorData();

    }

    @GetMapping("/room/{roomNumber}/temperature")
    public SensorData getTemperatureByRoom(@PathVariable Long roomNumber) {
        // Calling the service method to fetch the temperature
       // return "current temperature in room number  " + roomNumber+ " is "+temperatueSrevice.getRoomTemperature(roomNumber);

         return temperatueSrevice.getRoomTemperature(roomNumber);

    }


    @PostMapping("/sensor")
    public String controlTemperature (@RequestBody TemperatureReading reading) {
//         RoomService.turnOnHeating(roomId);
        return temperatueSrevice.controlTemperature(reading);

    }


}
