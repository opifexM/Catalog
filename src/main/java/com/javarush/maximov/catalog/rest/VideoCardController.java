package com.javarush.maximov.catalog.rest;

import com.javarush.maximov.catalog.constant.ViewConstants;
import com.javarush.maximov.catalog.videocard.VideoCard;
import com.javarush.maximov.catalog.videocard.VideoCardDto;
import com.javarush.maximov.catalog.videocard.VideoCardFilter;
import com.javarush.maximov.catalog.videocard.VideoCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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
@Tag(name = "Video Card Controller", description = "API for managing video cards")
@Slf4j
public class VideoCardController {

    private final VideoCardService videoCardService;

    @Autowired
    public VideoCardController(VideoCardService videoCardService) {
        this.videoCardService = videoCardService;
    }

    @Operation(summary = "List all video cards", description = "This method lists all video cards in the system.")
    @GetMapping(value = "")
    public String listVideocards(Model model) {
        log.info("Listing all video cards");
        model.addAttribute("videocardList", videoCardService.findAll());
        return ViewConstants.VIDEOCARDS;
    }

    @Operation(summary = "Get edit video card form", description = "This method returns the edit video card form.")
    @GetMapping(value = "/edit")
    public String getEditVideocardForm(
            @Parameter(description = "The ID of the video card to edit.")
            @RequestParam(name = "id") Long id, Model model) {
        log.info("Getting edit video card form for id: {}", id);
        Optional<VideoCardDto> optionalVideoCard = videoCardService.findDtoById(id);
        if (optionalVideoCard.isPresent()) {
            model.addAttribute("videocard", optionalVideoCard.get());
            return ViewConstants.VIDEOCARD_EDIT;
        }
        return ViewConstants.REDIRECT_VIDEOCARD;
    }

    @Operation(summary = "Save video card", description = "This method saves a video card.")
    @PostMapping(value = "/save")
    public String saveVideocard(
            @Parameter(description = "Video card object to save.")
            @ModelAttribute VideoCard videoCard) {
        log.info("Saving video card");
        videoCardService.save(videoCard);
        return ViewConstants.REDIRECT_VIDEOCARD;
    }

    @Operation(summary = "Search video cards",
            description = "This method searches for video cards based on the provided filters.")
    @GetMapping("/search")
    public String searchVideocards(
            @Parameter(description = "Video card filter object.")
            @ModelAttribute VideoCardFilter videoCardFilter, Model model) {
        log.info("Searching video cards with provided filters");
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
        return ViewConstants.VIDEOCARDS;
    }
}
