package com.Nordomatics.building_temperature_control.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

   /*public List<Room> getRoomDetails(Long roomId) {

            boolean exists= roomRepository.existsById(roomId);
            if(!exists)
            {
                throw new IllegalStateException("Room does not exist");
            }

            return (List<Room>) roomRepository.findById(roomId).get();
                  //  .orElseThrow(() -> new IllegalStateException("Room does not exist"));
    }*/

    public List<Room> getAllRoomDetails() {

        return roomRepository.findAll();
    }

//    public void turnOnHeating(Long roomId) {
//
//
//        System.out.println("Turning on heating for room: " + roomId);
//        Optional<Room> room = roomRepository.findById(roomId);
//        if (room.isPresent()) {
//            double desiredTemperature = room.get().getDesiredTemperature();
//            double currentTemperature = room.get().getCurrentTemperature();
//
//            if (currentTemperature < desiredTemperature) {
//                // Simulate sending a command to the heating system
//                System.out.println("Heating system activated for room: " + roomId);
//                // Assume gradual heating effect
//                room.get().setCurrentTemperature(Math.min(currentTemperature + 0.5, desiredTemperature));
//                roomRepository.save(room.get());
//            }
//        } else {
//            System.out.println("Room not found: " + roomId);
//        }
//    }

}
