package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.computer.Computer;
import com.javarush.maximov.catalog.computer.ComputerDto;
import com.javarush.maximov.catalog.computer.ComputerFilter;
import com.javarush.maximov.catalog.computer.ComputerService;
import com.javarush.maximov.catalog.constant.ViewConstants;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
import com.javarush.maximov.catalog.videocard.VideoCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/computer")
@Tag(name = "Computer Controller", description = "API for managing computers")
@Slf4j
public class ComputerController {

    private final MotherboardService motherboardService;
    private final VideoCardService videoCardService;
    private final PowerSupplyService powerSupplyService;
    private final ComputerService computerService;

    @Autowired
    public ComputerController(MotherboardService motherboardService, VideoCardService videoCardService,
                              PowerSupplyService powerSupplyService, ComputerService computerService) {
        this.motherboardService = motherboardService;
        this.videoCardService = videoCardService;
        this.powerSupplyService = powerSupplyService;
        this.computerService = computerService;
    }

    @Operation(summary = "List all computers", description = "This method lists all computers in the system.")
    @GetMapping(value = "")
    public String listComputers(Model model) {
        log.info("Listing all computers");
        model.addAttribute("computerList", computerService.findAll());
        return ViewConstants.COMPUTERS;
    }

    @Operation(summary = "Search computers",
            description = "This method searches for computers based on the provided filters.")
    @GetMapping("/search")
    public String searchMotherboards(
            @Parameter(description = "Computer filter object.")
            @ModelAttribute ComputerFilter computerFilter, Model model) {
        log.info("Searching computers with filter: {}", computerFilter);
        Iterable<ComputerDto> computers = computerService.getComputerFiltered(computerFilter);

        if (computerFilter.hasNameFilter()) {
            model.addAttribute("name", computerFilter.getName());
        }
        if (computerFilter.hasMotherboardFilter()) {
            model.addAttribute("motherboard", computerFilter.getMotherboard());
        }
        if (computerFilter.hasVideoCardsFilter()) {
            model.addAttribute("videoCards", computerFilter.getVideoCards());
        }
        if (computerFilter.hasTesting()) {
            model.addAttribute("testing", computerFilter.getTesting());
        }

        model.addAttribute("computerList", computers);
        return ViewConstants.COMPUTERS;
    }

    @Operation(summary = "Get edit computer form", description = "This method returns the edit computer form.")
    @GetMapping(value = "/edit")
    public String getEditComputerForm(
            @Parameter(description = "The ID of the computer to edit.")
            @RequestParam(name = "id") Long id, Model model) {
        log.info("Getting edit computer form for ID: {}", id);
        Optional<ComputerDto> optionalComputer = computerService.findById(id);
        if (optionalComputer.isPresent()) {
            model.addAttribute("computer", optionalComputer.get());
            model.addAttribute("motherboardList", motherboardService.findAll());
            model.addAttribute("powerSupplyList", powerSupplyService.findAll());
            model.addAttribute("videoCardList", videoCardService.findAll());
            return ViewConstants.COMPUTER_EDIT;
        }
        return ViewConstants.REDIRECT_COMPUTER;
    }

    @Operation(summary = "Delete computer by ID", description = "This method deletes a computer by its ID.")
    @PostMapping (value = "/delete")
    public String deleteComputerById(@RequestParam(name = "id") Long id) {
        log.info("Deleting computer by ID: {}", id);
        Optional<ComputerDto> optionalComputer = computerService.findById(id);
        if (optionalComputer.isPresent()) {
            computerService.deleteById(id);
        }
        return ViewConstants.REDIRECT_COMPUTER;
    }

    @Operation(summary = "Get new computer form", description = "This method returns the new computer form.")
    @GetMapping(value = "/new")
    public String getNewComputerForm(Model model) {
        log.info("Getting new computer form");
        model.addAttribute("computer", new Computer());
        model.addAttribute("motherboardList", motherboardService.findAll());
        model.addAttribute("powerSupplyList", powerSupplyService.findAll());
        model.addAttribute("videoCardList", videoCardService.findAll());
        return ViewConstants.COMPUTER_EDIT;
    }

    @Operation(summary = "Save computer", description = "This method saves a computer.")
    @PostMapping(value = "/save")
    public String saveComputer(
            @Parameter(description = "The computer object to be saved.")
            @ModelAttribute Computer computer) {
        log.info("Saving computer: {}", computer);
        computerService.save(computer);
        return ViewConstants.REDIRECT_COMPUTER;
    }
}
