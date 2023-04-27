package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.computer.Computer;
import com.javarush.maximov.catalog.computer.ComputerService;
import com.javarush.maximov.catalog.motherboard.Motherboard;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
import com.javarush.maximov.catalog.utils.Randomizer;
import com.javarush.maximov.catalog.videocard.VideoCard;
import com.javarush.maximov.catalog.videocard.VideoCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/")
@Tag(name = "Data Controller", description = "API for managing data")
public class DataController {

    private final MotherboardService motherboardService;
    private final VideoCardService videoCardService;
    private final PowerSupplyService powerSupplyService;
    private final ComputerService computerService;

    @Autowired
    public DataController(MotherboardService motherboardService,
                          VideoCardService videoCardService,
                          PowerSupplyService powerSupplyService,
                          ComputerService computerService) {
        this.motherboardService = motherboardService;
        this.videoCardService = videoCardService;
        this.powerSupplyService = powerSupplyService;
        this.computerService = computerService;
    }

    @Operation(summary = "Get statistics", description = "This method returns statistics.")
    @GetMapping(value = "")

    public String index(Model model) {
        model.addAttribute("computerCount", computerService.count());
        model.addAttribute("motherboardCount", motherboardService.count());
        model.addAttribute("powerSupplyCount", powerSupplyService.count());
        model.addAttribute("videoCardCount", videoCardService.count());
        return "index";
    }

    @Operation(summary = "Delete all data", description = "This method deletes all data from the DB.")
    @PostMapping("/delete")
    public String deleteAll() {
        computerService.deleteAll();
        motherboardService.deleteAll();
        powerSupplyService.deleteAll();
        videoCardService.deleteAll();
        return "redirect:/";
    }

    @Operation(summary = "Upload data", description = "This method uploads data from JSON files.")
    @PostMapping("/upload")
    public String uploadAll() {
        motherboardService.loadListFromJson("motherboard.json");
        powerSupplyService.loadListFromJson("powersupply.json");
        videoCardService.loadListFromJson("videocard.json");

        int motherboardMinId = Math.toIntExact(motherboardService.findMinId());
        int motherboardMaxId = Math.toIntExact(motherboardService.findMaxId());
        int powerSupplyMinId = Math.toIntExact(powerSupplyService.findMinId());
        int powerSupplyMaxId = Math.toIntExact(powerSupplyService.findMaxId());
        int videoCardMinId = Math.toIntExact(videoCardService.findMinId());
        int videoCardMaxId = Math.toIntExact(videoCardService.findMaxId());

        for (int i = 1; i <= 20; i++) {
            Computer computer = new Computer();
            computer.setName("PC #" + i);

            int random = Randomizer.randomIntFromToNotInclude(motherboardMinId, motherboardMaxId);
            Optional<Motherboard> optionalMotherboard = motherboardService.findById(random);
            optionalMotherboard.ifPresent(computer::setMotherboard);

            random = Randomizer.randomIntFromToNotInclude(powerSupplyMinId, powerSupplyMaxId);
            Optional<PowerSupply> optionalPowerSupply = powerSupplyService.findById(random);
            optionalPowerSupply.ifPresent(computer::setPowerSupply);

            List<VideoCard> videoCardList = new ArrayList<>();
            random = Randomizer.randomIntFromToNotInclude(videoCardMinId, videoCardMaxId);
            Optional<VideoCard> optionalVideoCard1 = videoCardService.findById(random);
            optionalVideoCard1.ifPresent(videoCardList::add);

            int random2 = Randomizer.randomIntFromToNotInclude(videoCardMinId, videoCardMaxId);
            Optional<VideoCard> optionalVideoCard2 = videoCardService.findById(random2);
            optionalVideoCard2.ifPresent(videoCardList::add);

            if (!videoCardList.isEmpty() && random != random2) {
                computer.setVideoCards(videoCardList);
            }

            if (nonNull(computer.getMotherboard()) && nonNull(computer.getPowerSupply()) &&
                    nonNull(computer.getVideoCards()) && !computer.getVideoCards().isEmpty()) {
                computerService.save(computer);
            }
        }

        return "redirect:/";
    }
}
