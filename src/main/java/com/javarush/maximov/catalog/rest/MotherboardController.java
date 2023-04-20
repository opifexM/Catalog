package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.motherboard.Motherboard;
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

    @GetMapping(value = "/edit")
    public String getEditMotherboardForm(@RequestParam(name = "id") Long id, Model model) {
        Optional<Motherboard> optionalMotherboard = motherboardService.findById(id);
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
