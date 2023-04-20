package com.javarush.maximov.catalog.rest;

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

import java.util.Optional;

@Controller
@RequestMapping("/videocard")
public class VideoCardController {

    private final VideoCardService videoCardService;

    @Autowired
    public VideoCardController(VideoCardService videoCardService) {
        this.videoCardService = videoCardService;
    }

    @GetMapping(value = "")
    public String listVideocards(Model model) {
        model.addAttribute("videocardList", videoCardService.findAll());
        return "videocards";
    }

    @GetMapping(value = "/edit")
    public String getEditVideocardForm(@RequestParam(name = "id") Long id, Model model) {
        Optional<VideoCard> optionalVideoCard = videoCardService.findById(id);
        if (optionalVideoCard.isPresent()) {
            model.addAttribute("videocard", optionalVideoCard.get());
            return "videocardEdit";
        }
        return "redirect:/videocard";
    }

    @PostMapping(value = "/save")
    public String saveVideocard(@ModelAttribute VideoCard videoCard) {
        videoCardService.save(videoCard);
        return "redirect:/videocard";
    }

    // @GetMapping(value = "/search")
    // public String searchVideocards(@RequestParam(name = "core") Integer core,
    //                                @RequestParam(name = "operator") String operator,
    //                                Model model) {
    //     Iterable<VideoCard> videoCards = switch (operator) {
    //         case "greaterThanEqual" -> videoCardService.findByCoreFrequencyGreaterThanEqual(core);
    //         case "lessThan" -> videoCardService.findByCoreFrequencyLessThan(core);
    //         default -> videoCardService.findAll();
    //     };
    //     model.addAttribute("videocardList", videoCards);
    //     model.addAttribute("videocardCore", core);
    //
    //     return "videocards";
    // }

    @GetMapping("/search")
    public String searchVideocards(@RequestParam(name = "core", required = false) Integer core,
                                   @RequestParam(name = "operator", defaultValue = "greaterThanEqual") String operator,
                                   @RequestParam(name = "memoryBandwidthStart", required = false) Double memoryBandwidthStart,
                                   @RequestParam(name = "memoryBandwidthEnd", required = false) Double memoryBandwidthEnd,
                                   @RequestParam(name = "name", required = false) String name,
                                   Model model) {
        Iterable<VideoCard> videoCards;
        if (core != null) {
            switch (operator) {
                case "greaterThanEqual" -> videoCards = videoCardService.findByCoreFrequencyGreaterThanEqual(core);
                case "lessThan" -> videoCards = videoCardService.findByCoreFrequencyLessThan(core);
                default -> videoCards = videoCardService.findAll();
            }
            model.addAttribute("videocardCore", core);

        } else if (memoryBandwidthStart != null && memoryBandwidthEnd != null) {
            videoCards = videoCardService.findByMemoryBandwidthBetween(memoryBandwidthStart, memoryBandwidthEnd);
            model.addAttribute("videocardMemoryBandwidthStart", memoryBandwidthStart);
            model.addAttribute("videocardMemoryBandwidthEnd", memoryBandwidthEnd);

        } else if (name != null) {
            videoCards = videoCardService.findByNameContainsIgnoreCase(name);
            model.addAttribute("videocardName", name);

        } else {
            videoCards = videoCardService.findAll();
        }
        model.addAttribute("videocardList", videoCards);
        return "videocards";
    }

}
