package com.Nordomatics.building_temperature_control.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private final BuildingRepository buildingRepository;
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }
    public List<Building> getBuilding() {

        return buildingRepository.findAll();

    }
}
