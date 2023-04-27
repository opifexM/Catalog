package com.javarush.maximov.catalog.computer;

import com.javarush.maximov.catalog.motherboard.MotherboardDto;
import com.javarush.maximov.catalog.powersupply.PowerSupplyDto;
import com.javarush.maximov.catalog.videocard.VideoCardDto;
import lombok.Data;

import java.util.List;

/**
 * A DTO for the {@link Computer} entity
 */
@Data
public class ComputerDto {
    private long id;

    private String name;
    private MotherboardDto motherboard;
    private PowerSupplyDto powerSupply;
    private List<VideoCardDto> videoCards;
    private boolean testing = false;
    private ComputerStatus computerStatus = ComputerStatus.FORMED;
}