package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.videocard.VideoCard;
import com.javarush.maximov.catalog.videocard.VideoCardDto;
import com.javarush.maximov.catalog.videocard.VideoCardFilter;
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
        Optional<VideoCardDto> optionalVideoCard = videoCardService.findDtoById(id);
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

    @GetMapping("/search")
    public String searchVideocards(@ModelAttribute VideoCardFilter videoCardFilter, Model model) {
        Iterable<VideoCardDto> videoCards = videoCardService.getVideoCardFiltered(videoCardFilter);


        if (videoCardFilter.hasCoreFilter()) {
            model.addAttribute("videocardCore", videoCardFilter.getCore());
        }
        if (videoCardFilter.hasMemoryBandwidthStartFilter()) {
            model.addAttribute("videocardMemoryBandwidthStart", videoCardFilter.getMemoryBandwidthStart());
        }
        if (videoCardFilter.hasMemoryBandwidthEndFilter()) {
            model.addAttribute("videocardMemoryBandwidthEnd", videoCardFilter.getMemoryBandwidthEnd());
        }
        if (videoCardFilter.hasNameFilter()) {
            model.addAttribute("videocardName", videoCardFilter.getName());
        }

        model.addAttribute("videocardList", videoCards);
        return "videocards";
    }

}
