package com.Nordomatics.building_temperature_control.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {

 @Autowired
private final BuildingService buildingService;
public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping(path="/hello/")
    public List<Building> GetBuilding() {

             return buildingService.getBuilding();

    }

}
