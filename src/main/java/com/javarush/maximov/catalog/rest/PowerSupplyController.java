package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.constant.ViewConstants;
import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.powersupply.PowerSupplyDto;
import com.javarush.maximov.catalog.powersupply.PowerSupplyFilter;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
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

@Tag(name = "Power Supply Controller", description = "API for managing power supplies")
@Controller
@RequestMapping("/powersupply")
public class PowerSupplyController {

    private final PowerSupplyService powerSupplyService;

    @Autowired
    public PowerSupplyController(PowerSupplyService powerSupplyService) {
        this.powerSupplyService = powerSupplyService;
    }

    @Operation(summary = "List all power supplies", description = "This method lists all power supplies in the system.")
    @GetMapping(value = "")
    public String listPowersupplies(Model model) {
        model.addAttribute("powersupplyList", powerSupplyService.findAll());
        return ViewConstants.POWERSUPPLIES;
    }

    @Operation(summary = "Search power supplies",
            description = "This method searches for power supplies based on the provided filters.")
    @GetMapping("/search")
    public String searchPowersupplies(
            @Parameter(description = "Power supply filter object.")
            @ModelAttribute PowerSupplyFilter powerSupplyFilter,  Model model) {
        Iterable<PowerSupplyDto> powerSupplies = powerSupplyService.getPowersuppliesFiltred(powerSupplyFilter);

        if (powerSupplyFilter.hasFanSize()) {
            model.addAttribute("powersupplyFanSize", powerSupplyFilter.getFanSize());
        }
        if (powerSupplyFilter.hasPowerStart()) {
            model.addAttribute("powersupplyPowerStart", powerSupplyFilter.getPowerStart());
        }
        if (powerSupplyFilter.hasPowerEnd()) {
            model.addAttribute("powersupplyPowerEnd", powerSupplyFilter.getPowerEnd());
        }
        if (powerSupplyFilter.hasNameFilter()) {
            model.addAttribute("powersupplyName", powerSupplyFilter.getName());
        }

        model.addAttribute("powersupplyList", powerSupplies);
        return ViewConstants.POWERSUPPLIES;
    }

    @Operation(summary = "Get edit power supply form", description = "This method returns the edit power supply form.")
    @GetMapping(value = "/edit")
    public String getEditPowersupplyForm(
            @Parameter(description = "The ID of the power supply to edit.")
            @RequestParam(name = "id") Long id, Model model) {
        Optional<PowerSupplyDto> optionalPowerSupply = powerSupplyService.findDtoById(id);
        if (optionalPowerSupply.isPresent()) {
            model.addAttribute("powersupply", optionalPowerSupply.get());
            return ViewConstants.POWERSUPPLY_EDIT;
        }
        return ViewConstants.REDIRECT_POWERSUPPLY;
    }

    @Operation(summary = "Save power supply", description = "This method saves a power supply.")
    @PostMapping(value = "/save")
    public String savePowersupply(
            @Parameter(description = "Power supply object to save.")
            @ModelAttribute PowerSupply powerSupply) {
        powerSupplyService.save(powerSupply);
        return ViewConstants.REDIRECT_POWERSUPPLY;
    }
}
