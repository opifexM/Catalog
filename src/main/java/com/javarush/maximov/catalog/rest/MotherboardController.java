package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.motherboard.Motherboard;
import com.javarush.maximov.catalog.motherboard.MotherboardDto;
import com.javarush.maximov.catalog.motherboard.MotherboardFilter;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Tag(name = "Motherboard Controller", description = "API for managing motherboards")
@Controller
@RequestMapping("/motherboard")
public class MotherboardController {

    private final MotherboardService motherboardService;

    @Autowired
    public MotherboardController(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @Operation(summary = "List all motherboards", description = "This method lists all motherboards in the system.")
    @GetMapping(value = "")
    public String listMotherboards(Model model) {
        model.addAttribute("motherboardList", motherboardService.findAll());
        return "motherboards";
    }

    @Operation(summary = "Search motherboards",
            description = "This method searches for motherboards based on the provided filters.")
    @GetMapping("/search")
    public String searchMotherboards(
            @Parameter(description = "Motherboard filter object.")
            @ModelAttribute MotherboardFilter motherboardFilter, Model model) {
        Iterable<MotherboardDto> motherboards = motherboardService.getMotherboardFiltered(motherboardFilter);

        if (motherboardFilter.hasCpuCoreVoltageStartFilter()) {
            model.addAttribute("motherboardCpuCoreVoltageStart", motherboardFilter.getCpuCoreVoltageStart());
        }
        if (motherboardFilter.hasCpuCoreVoltageEndFilter()) {
            model.addAttribute("motherboardCpuCoreVoltageEnd", motherboardFilter.getCpuCoreVoltageEnd());
        }
        if (motherboardFilter.hasClockGeneratorFilter()) {
            model.addAttribute("motherboardClockGenerator", motherboardFilter.getClockGenerator());
        }
        if (motherboardFilter.hasDualBIOSFilter()) {
            model.addAttribute("motherboardDualBIOS", motherboardFilter.getDualBIOS());
        }
        if (motherboardFilter.hasBiosFlashFilter()) {
            model.addAttribute("motherboardBiosFlash", motherboardFilter.getBiosFlash());
        }
        if (motherboardFilter.hasClearCMOSFilter()) {
            model.addAttribute("motherboardClearCMOS", motherboardFilter.getClearCMOS());
        }
        if (motherboardFilter.hasNameFilter()) {
            model.addAttribute("motherboardName", motherboardFilter.getName());
        }

        model.addAttribute("motherboardList", motherboards);
        return "motherboards";
    }

    @Operation(summary = "Get edit motherboard form", description = "This method returns the edit motherboard form.")
    @GetMapping(value = "/edit")
    public String getEditMotherboardForm(
            @Parameter(description = "The ID of the motherboard to edit.")
            @RequestParam(name = "id") Long id, Model model) {
        Optional<MotherboardDto> optionalMotherboard = motherboardService.findDtoById(id);
        if (optionalMotherboard.isPresent()) {
            model.addAttribute("motherboard", optionalMotherboard.get());
            return "motherboardEdit";
        }
        return "redirect:/motherboard";
    }

    @Operation(summary = "Save motherboard", description = "This method saves a motherboard.")
    @PostMapping(value = "/save")
    public String saveMotherboard(
            @Parameter(description = "Motherboard object to save.")
            @ModelAttribute Motherboard motherboard) {
        motherboardService.save(motherboard);
        return "redirect:/motherboard";
    }
}
