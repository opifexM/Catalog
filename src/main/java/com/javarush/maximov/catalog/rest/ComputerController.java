package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.computer.Computer;
import com.javarush.maximov.catalog.computer.ComputerDto;
import com.javarush.maximov.catalog.computer.ComputerFilter;
import com.javarush.maximov.catalog.computer.ComputerService;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
import com.javarush.maximov.catalog.videocard.VideoCardService;
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
public class ComputerController {

    private final MotherboardService motherboardService;
    private final VideoCardService videoCardService;
    private final PowerSupplyService powerSupplyService;
    private final ComputerService computerService;

    @Autowired
    public ComputerController(MotherboardService motherboardService,
                              VideoCardService videoCardService,
                              PowerSupplyService powerSupplyService,
                              ComputerService computerService) {
        this.motherboardService = motherboardService;
        this.videoCardService = videoCardService;
        this.powerSupplyService = powerSupplyService;
        this.computerService = computerService;
    }

    @GetMapping(value = "")
    public String listComputers(Model model) {
        model.addAttribute("computerList", computerService.findAll());
        return "computers";
    }

    @GetMapping("/search")
    public String searchMotherboards(@ModelAttribute ComputerFilter computerFilter, Model model) {
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
        return "computers";
    }

    @GetMapping(value = "/edit")
    public String getEditComputerForm(@RequestParam(name = "id") Long id, Model model) {
        Optional<ComputerDto> optionalComputer = computerService.findById(id);
        if (optionalComputer.isPresent()) {
            model.addAttribute("computer", optionalComputer.get());
            model.addAttribute("motherboardList", motherboardService.findAll());
            model.addAttribute("powerSupplyList", powerSupplyService.findAll());
            model.addAttribute("videoCardList", videoCardService.findAll());
            return "computerEdit";
        }
        return "redirect:/computer";
    }

    @GetMapping(value = "/delete")
    public String deleteComputerById(@RequestParam(name = "id") Long id) {
        Optional<ComputerDto> optionalComputer = computerService.findById(id);
        if (optionalComputer.isPresent()) {
            computerService.deleteById(id);
        }
        return "redirect:/computer";
    }

    @GetMapping(value = "/new")
    public String getNewComputerForm(Model model) {
        model.addAttribute("computer", new Computer());
        model.addAttribute("motherboardList", motherboardService.findAll());
        model.addAttribute("powerSupplyList", powerSupplyService.findAll());
        model.addAttribute("videoCardList", videoCardService.findAll());
        return "computerEdit";
    }

    @PostMapping(value = "/save")
    public String saveComputer(@ModelAttribute Computer computer) {
        computerService.save(computer);
        return "redirect:/computer";
    }
}
