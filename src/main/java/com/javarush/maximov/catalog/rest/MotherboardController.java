package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.motherboard.Motherboard;
import com.javarush.maximov.catalog.motherboard.MotherboardDto;
import com.javarush.maximov.catalog.motherboard.MotherboardFilter;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
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
@RequestMapping("/motherboard")
public class MotherboardController {

    private final MotherboardService motherboardService;

    @Autowired
    public MotherboardController(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @GetMapping(value = "")
    public String listMotherboards(Model model) {
        model.addAttribute("motherboardList", motherboardService.findAll());
        return "motherboards";
    }

    @GetMapping("/search")
    public String searchMotherboards(@ModelAttribute MotherboardFilter motherboardFilter, Model model) {
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

    @GetMapping(value = "/edit")
    public String getEditMotherboardForm(@RequestParam(name = "id") Long id, Model model) {
        Optional<MotherboardDto> optionalMotherboard = motherboardService.findDtoById(id);
        if (optionalMotherboard.isPresent()) {
            model.addAttribute("motherboard", optionalMotherboard.get());
            return "motherboardEdit";
        }
        return "redirect:/motherboard";
    }

    @PostMapping(value = "/save")
    public String saveMotherboard(@ModelAttribute Motherboard motherboard) {
        motherboardService.save(motherboard);
        return "redirect:/motherboard";
    }
}
