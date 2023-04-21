package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.powersupply.PowerSupply;
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
    public String searchPowersupplies(@RequestParam(name = "powerStart", required = false) Integer powerStart,
                                      @RequestParam(name = "powerEnd", required = false) Integer powerEnd,
                                      @RequestParam(name = "fanSize", required = false) Integer fanSize,
                                      @RequestParam(name = "fanSizeOperator", defaultValue = "greaterThanEqual") String fanSizeOperator,
                                      @RequestParam(name = "name", required = false) String name,
                                      Model model) {
        Iterable<PowerSupply> powerSupplies;
        if (fanSize != null) {
            switch (fanSizeOperator) {
                case "greaterThanEqual" -> powerSupplies = powerSupplyService.findByFanSizeGreaterThanEqual(fanSize);
                case "lessThan" -> powerSupplies = powerSupplyService.findByFanSizeLessThan(fanSize);
                default -> powerSupplies = powerSupplyService.findAll();
            }
            model.addAttribute("powersupplyFanSize", fanSize);

        } else if (powerStart != null && powerEnd != null) {
            powerSupplies = powerSupplyService.findByPowerBetween(powerStart, powerEnd);
            model.addAttribute("powersupplyPowerStart", powerStart);
            model.addAttribute("powersupplyPowerEnd", powerEnd);

        } else if (name != null) {
            powerSupplies = powerSupplyService.findByNameContainsIgnoreCase(name);
            model.addAttribute("powersupplyName", name);

        } else {
            powerSupplies = powerSupplyService.findAll();
        }
        model.addAttribute("powersupplyList", powerSupplies);
        return "powersupplies";
    }

    @GetMapping(value = "/edit")
    public String getEditPowersupplyForm(@RequestParam(name = "id") Long id, Model model) {
        Optional<PowerSupply> optionalPowerSupply = powerSupplyService.findById(id);
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
