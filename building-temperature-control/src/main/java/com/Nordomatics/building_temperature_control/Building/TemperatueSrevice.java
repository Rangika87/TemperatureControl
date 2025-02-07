package com.Nordomatics.building_temperature_control.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatueSrevice {


    private final TemperatureRepository temperatureRepository;
    private final RoomRepository roomRepository   ;

    @Autowired
    public TemperatueSrevice(TemperatureRepository temperatureRepository,RoomRepository roomRepository)
    {
        this.temperatureRepository = temperatureRepository;
        this.roomRepository = roomRepository;
    }

    public String controlTemperature(TemperatureReading reading)
    {

        // to save data in sensor_data
        SensorData sensorData = new SensorData(reading.roomId, reading.currentTemperature);
        temperatureRepository.save(sensorData);

        Optional<Room> room = roomRepository.findById(reading.roomId);
        if (room.isPresent()) {
            double desiredTemperature = room.get().getDesiredTemperature();
            double currentTemperature = reading.currentTemperature;

            if (currentTemperature < desiredTemperature)
            {
                // Simulate sending a command to the heating system
                System.out.println("Heating system activated for room: " + reading.roomId);
                // Assume gradual heating effect
                room.get().setCurrentTemperature(Math.min(currentTemperature + 0.5, desiredTemperature));
                roomRepository.save(room.get());
                return "Turning on heating for room: " + reading.roomId+ " and the set the temperature to " +Math.min(currentTemperature + 0.5, desiredTemperature);
            }
            else if(currentTemperature>desiredTemperature)
            {

                // Simulate sending a command to the cooling system
                System.out.println("Cooling system activated for room: " + reading.roomId);
                // Assume gradual cooling effect
                room.get().setCurrentTemperature(Math.max(currentTemperature - 0.5, desiredTemperature));
                roomRepository.save(room.get());
                return "Turning on cooling for room " + reading.roomId+ " and the set the temperature to " +Math.max(currentTemperature - 0.5, desiredTemperature);
            }
            else
            {
                return "No temperature control needed";
            }
        }
        else
        {
            System.out.println("Room not found: " + reading.roomId);
            return "Room not found: " + reading.roomId;
        }
    }



    public List<SensorData> getSensorData()
    {
        return temperatureRepository.findAll();
    }


    public SensorData getRoomTemperature(Long roomNo)
    {
        Optional<SensorData>  roomTemperature= temperatureRepository.findTopByRoomIdOrderByIdDesc(roomNo);
        return roomTemperature.get();
    }


}