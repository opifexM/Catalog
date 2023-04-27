package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.powersupply.PowerSupplyDto;
import com.javarush.maximov.catalog.powersupply.PowerSupplyFilter;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
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
@RequestMapping("/powersupply")
public class PowerSupplyController {

    private final PowerSupplyService powerSupplyService;

    @Autowired
    public PowerSupplyController(PowerSupplyService powerSupplyService) {
        this.powerSupplyService = powerSupplyService;
    }

    @GetMapping(value = "")
    public String listPowersupplies(Model model) {
        model.addAttribute("powersupplyList", powerSupplyService.findAll());
        return "powersupplies";
    }

    @GetMapping("/search")
    public String searchPowersupplies(@ModelAttribute PowerSupplyFilter powerSupplyFilter,  Model model) {
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
        return "powersupplies";
    }

    @GetMapping(value = "/edit")
    public String getEditPowersupplyForm(@RequestParam(name = "id") Long id, Model model) {
        Optional<PowerSupplyDto> optionalPowerSupply = powerSupplyService.findDtoById(id);
        if (optionalPowerSupply.isPresent()) {
            model.addAttribute("powersupply", optionalPowerSupply.get());
            return "powersupplyEdit";
        }
        return "redirect:/powersupply";
    }

    @PostMapping(value = "/save")
    public String savePowersupply(@ModelAttribute PowerSupply powerSupply) {
        powerSupplyService.save(powerSupply);
        return "redirect:/powersupply";
    }
}
