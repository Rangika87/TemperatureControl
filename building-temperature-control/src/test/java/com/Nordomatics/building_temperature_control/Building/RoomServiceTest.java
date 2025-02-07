package com.Nordomatics.building_temperature_control.Building;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    private List<Room> mockRooms;

    @BeforeEach
    void setUp() {
        mockRooms = Arrays.asList(
                new Room(1L, "Living Room", 22.5, 20.0),
                new Room(2L, "Bedroom", 24.0, 23.5)
        );
    }


    @Test
    void testGetAllRoomDetails() {
        // Arrange
        when(roomRepository.findAll()).thenReturn(mockRooms);

        // Act
        List<Room> rooms = roomService.getAllRoomDetails();

        // Assert
        assertEquals(2, rooms.size());
        assertEquals("Living Room", rooms.get(0).getName());
        assertEquals("Bedroom", rooms.get(1).getName());
    }
}