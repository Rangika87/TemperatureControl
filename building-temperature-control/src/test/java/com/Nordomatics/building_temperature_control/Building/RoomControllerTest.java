package com.Nordomatics.building_temperature_control.Building;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Test
    void testGetAllRoomDetails() throws Exception {
        // Arrange: Mocking RoomService response
        List<Room> mockRooms = Arrays.asList(
                new Room(1L, "Room A", 22.0, 20.5),
                new Room(2L, "Room B", 24.0, 23.0)
        );

        when(roomService.getAllRoomDetails()).thenReturn(mockRooms);

        // Initialize MockMvc with the RoomController
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();

        // Act & Assert: Perform GET request and verify response
        mockMvc.perform(get("/roomDetails/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Room A"))
                .andExpect(jsonPath("$[1].desiredTemperature").value(24.0));
    }

}