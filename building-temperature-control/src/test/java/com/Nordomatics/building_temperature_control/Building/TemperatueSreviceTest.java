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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemperatueSreviceTest {
    @Mock
    private TemperatureRepository temperatureRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private TemperatueSrevice temperatureService;

    @BeforeEach
    void setUp() {
        temperatureService = new TemperatueSrevice(temperatureRepository, roomRepository);
    }

    @Test
    void testControlTemperatureHeating() {
        TemperatureReading reading = new TemperatureReading();
        reading.roomId = 1L;
        reading.currentTemperature = 18.0;

        Room room = new Room(1L, "Room1", 22.0, 18.0);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        String result = temperatureService.controlTemperature(reading);

        assertEquals("Turning on heating for room: 1 and the set the temperature to 18.5", result);
        verify(roomRepository, times(1)).save(room);
        verify(temperatureRepository, times(1)).save(any(SensorData.class));
    }

    @Test
    void testControlTemperatureCooling() {
        TemperatureReading reading = new TemperatureReading();
        reading.roomId = 2L;
        reading.currentTemperature = 25.0;

        Room room = new Room(2L, "Room2", 22.0, 25.0);
        when(roomRepository.findById(2L)).thenReturn(Optional.of(room));

        String result = temperatureService.controlTemperature(reading);

        assertEquals("Turning on cooling for room 2 and the set the temperature to 24.5", result);
        verify(roomRepository, times(1)).save(room);
        verify(temperatureRepository, times(1)).save(any(SensorData.class));
    }

    @Test
    void testControlTemperatureNoChange() {
        TemperatureReading reading = new TemperatureReading();
        reading.roomId = 3L;
        reading.currentTemperature = 22.0;

        Room room = new Room(3L, "Room3", 22.0, 22.0);
        when(roomRepository.findById(3L)).thenReturn(Optional.of(room));

        String result = temperatureService.controlTemperature(reading);

        assertEquals("No temperature control needed", result);
        verify(roomRepository, never()).save(room);
        verify(temperatureRepository, times(1)).save(any(SensorData.class));
    }

    @Test
    void testControlTemperatureRoomNotFound() {
        TemperatureReading reading = new TemperatureReading();
        reading.roomId = 4L;
        reading.currentTemperature = 20.0;

        when(roomRepository.findById(4L)).thenReturn(Optional.empty());

        String result = temperatureService.controlTemperature(reading);

        assertEquals("Room not found: 4", result);
        verify(roomRepository, never()).save(any());
        verify(temperatureRepository, times(1)).save(any(SensorData.class));
    }

    @Test
    void testGetSensorData() {
        List<SensorData> sensorDataList = Arrays.asList(
                new SensorData(1L, 20.0),
                new SensorData(2L, 22.0)
        );
        when(temperatureRepository.findAll()).thenReturn(sensorDataList);

        List<SensorData> result = temperatureService.getSensorData();

        assertEquals(2, result.size());
        verify(temperatureRepository, times(1)).findAll();
    }

    @Test
    void testGetRoomTemperature() {
        SensorData sensorData = new SensorData(1L, 21.5);
        when(temperatureRepository.findTopByRoomIdOrderByIdDesc(1L)).thenReturn(Optional.of(sensorData));

        SensorData result = temperatureService.getRoomTemperature(1L);

        assertNotNull(result);
        assertEquals(21.5, result.getCurrentTemperature());
        verify(temperatureRepository, times(1)).findTopByRoomIdOrderByIdDesc(1L);
    }

}