package com.Nordomatics.building_temperature_control.Building;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
class TemperatureControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TemperatueSrevice temperatueSrevice;

    @InjectMocks
    private TemperatureController temperatureController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(temperatureController).build();
    }

    @Test
    void testGetSensorData() throws Exception {
        List<SensorData> sensorDataList = Arrays.asList(
                new SensorData(1L, 20.0),
                new SensorData(2L, 22.0)
        );
        when(temperatueSrevice.getSensorData()).thenReturn(sensorDataList);

        mockMvc.perform(get("/sensorData/"))
                .andExpect(status().isOk());

        verify(temperatueSrevice, times(1)).getSensorData();
    }

    @Test
    void testGetTemperatureByRoom() throws Exception {
        SensorData sensorData = new SensorData(1L, 21.5);
        when(temperatueSrevice.getRoomTemperature(1L)).thenReturn(sensorData);

        mockMvc.perform(get("/room/1/temperature"))
                .andExpect(status().isOk());

        verify(temperatueSrevice, times(1)).getRoomTemperature(1L);
    }

    @Test
    void testControlTemperature() throws Exception {
        TemperatureReading reading = new TemperatureReading();
        reading.roomId = 1L;
        reading.currentTemperature = 18.0;

        when(temperatueSrevice.controlTemperature(any(TemperatureReading.class))).thenReturn("Heating activated");

        mockMvc.perform(post("/sensor")
                        .contentType(APPLICATION_JSON)
                        .content("{\"roomId\":1,\"currentTemperature\":18.0}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Heating activated"));

        verify(temperatueSrevice, times(1)).controlTemperature(any(TemperatureReading.class));
    }
}