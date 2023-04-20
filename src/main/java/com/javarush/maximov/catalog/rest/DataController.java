package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.computer.Computer;
import com.javarush.maximov.catalog.computer.ComputerService;
import com.javarush.maximov.catalog.motherboard.Motherboard;
import com.javarush.maximov.catalog.motherboard.MotherboardService;
import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.powersupply.PowerSupplyService;
import com.javarush.maximov.catalog.videocard.VideoCard;
import com.javarush.maximov.catalog.videocard.VideoCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
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

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("message", "Hello World2222!");
        model.addAttribute("computerCount", computerService.count());
        model.addAttribute("motherboardCount", motherboardService.count());
        model.addAttribute("powerSupplyCount", powerSupplyService.count());
        model.addAttribute("videoCardCount", videoCardService.count());
        return "index";
    }


    //+++++++++++++++++++++++++++++++++
    @PostMapping("/delete")
    public String deleteAll() {
        motherboardService.deleteAll();
        powerSupplyService.deleteAll();
        videoCardService.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String uploadAll() {
        motherboardService.loadListFromJson();
        powerSupplyService.loadListFromJson();
        videoCardService.loadListFromJson();

        Computer computer = new Computer();
        computer.setName("For Testing");
        computer.setMotherboard(motherboardService.findById(10).get());
        computer.setPowerSupply(powerSupplyService.findById(15).get());
        List<VideoCard> videoCardList = new ArrayList<>();
        videoCardList.add(videoCardService.findById(5).get());
        videoCardList.add(videoCardService.findById(6).get());
        computer.setVideoCards(videoCardList);
        computerService.save(computer);

        Computer computer2 = new Computer();
        computer2.setName("For Gaming");
        computer2.setMotherboard(motherboardService.findById(12).get());
        computer2.setPowerSupply(powerSupplyService.findById(18).get());
        List<VideoCard> videoCardList2 = new ArrayList<>();
        videoCardList2.add(videoCardService.findById(3).get());
        computer2.setVideoCards(videoCardList2);
        computerService.save(computer2);

        Computer computer3 = new Computer();
        computer3.setName("For Office");
        computer3.setMotherboard(motherboardService.findById(14).get());
        computer3.setPowerSupply(powerSupplyService.findById(20).get());
        List<VideoCard> videoCardList3 = new ArrayList<>();
        videoCardList3.add(videoCardService.findById(28).get());
        videoCardList3.add(videoCardService.findById(29).get());
        computer3.setVideoCards(videoCardList3);
        computerService.save(computer3);

        return "redirect:/";
    }
}
