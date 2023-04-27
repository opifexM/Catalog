package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.computer.Computer;
import com.javarush.maximov.catalog.computer.ComputerDto;
import com.javarush.maximov.catalog.computer.ComputerService;
import com.javarush.maximov.catalog.motherboard.Motherboard;
import com.javarush.maximov.catalog.motherboard.MotherboardDto;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.powersupply.PowerSupplyDto;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
import com.javarush.maximov.catalog.videocard.VideoCard;
import com.javarush.maximov.catalog.videocard.VideoCardDto;
import com.javarush.maximov.catalog.videocard.VideoCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final MotherboardService motherboardService;
    private final VideoCardService videoCardService;
    private final PowerSupplyService powerSupplyService;
    private final ComputerService computerService;

    @Autowired
    public ApiController(MotherboardService motherboardService,
                         VideoCardService videoCardService,
                         PowerSupplyService powerSupplyService,
                         ComputerService computerService) {
        this.motherboardService = motherboardService;
        this.videoCardService = videoCardService;
        this.powerSupplyService = powerSupplyService;
        this.computerService = computerService;
    }

    @GetMapping("/computer")
    public Iterable<ComputerDto> getAllComputers() {
        return computerService.findAll();
    }

    @GetMapping("/motherboard")
    public Iterable<MotherboardDto> getAllMotherboards() {
        return motherboardService.findAll();
    }

    @PutMapping("/motherboard")
    public void saveListMotherboard(@RequestBody Iterable<Motherboard> motherboards) {
        motherboardService.saveList(motherboards);
    }

    @DeleteMapping("/motherboard")
    public void clearMotherboard() {
        motherboardService.deleteAll();
    }

    @GetMapping("/powersupply")
    public Iterable<PowerSupplyDto> getAllPowerSupplies() {
        return powerSupplyService.findAll();
    }

    @GetMapping("/videocard")
    public Iterable<VideoCardDto> getAllVideoCard() {
        return videoCardService.findAll();
    }

}
