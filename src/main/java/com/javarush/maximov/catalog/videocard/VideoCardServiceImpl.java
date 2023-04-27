package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.utils.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VideoCardServiceImpl implements VideoCardService {

    private final VideoCardRepository videoCardRepository;

    private final VideoCardMapper videoCardMapper;

    @Autowired
    public VideoCardServiceImpl(VideoCardRepository videoCardRepository, VideoCardMapper videoCardMapper) {
        this.videoCardRepository = videoCardRepository;
        this.videoCardMapper = videoCardMapper;
    }

    @Override
    public Iterable<VideoCardDto> findAll() {
        log.info("Fetching all video cards");
        List<VideoCard> videoCardList = videoCardRepository.findAll(Sort.by("id"));
        return videoCardMapper.map(videoCardList);
    }

    @Override
    public Optional<VideoCardDto> findDtoById(long id) {
        log.info("Fetching video card DTO with id: {}", id);
        Optional<VideoCard> optionalVideoCard = videoCardRepository.findById(id);
        return optionalVideoCard.map(videoCardMapper::map);
    }

    @Override
    public Optional<VideoCard> findById(long id) {
        log.info("Fetching video card with id: {}", id);
        return videoCardRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        log.info("Deleting all video cards");
        videoCardRepository.deleteAll();
    }

    @Override
    public void save(VideoCard videoCard) {
        log.info("Saving video card: {}", videoCard);
        videoCardRepository.save(videoCard);
        log.info("Video card saved: {}", videoCard);
    }

    @Override
    public void saveList(Iterable<VideoCard> videoCards) {
        log.info("Saving list of video cards");
        videoCardRepository.saveAll(videoCards);
        log.info("List of video cards saved");
    }

    @Override
    public void loadListFromJson(String file) {
        log.info("Loading video cards from JSON file: {}", file);
        List<VideoCard> videoCardList = JsonService.jsonToList(file, VideoCard[].class);
        saveList(videoCardList);
    }

    @Override
    public long count() {
        log.info("Counting video cards");
        return videoCardRepository.count();
    }

    @Override
    public Long findMinId() {
        log.info("Finding minimum video card id");
        return videoCardRepository.findMinId();
    }

    @Override
    public Long findMaxId() {
        log.info("Finding maximum video card id");
        return videoCardRepository.findMaxId();
    }

    @Override
    public Iterable<VideoCardDto> getVideoCardFiltered(VideoCardFilter videoCardFilter) {
        log.info("Fetching filtered video cards: {}", videoCardFilter);
        List<VideoCard> videoCards;
        if (videoCardFilter.hasCoreFilter() && videoCardFilter.hasOperatorFilter()) {
            switch (videoCardFilter.getOperator()) {
                case "greaterThanEqual" ->
                        videoCards = videoCardRepository.findByCoreFrequencyGreaterThanEqual(videoCardFilter.getCore());
                case "lessThan" ->
                        videoCards = videoCardRepository.findByCoreFrequencyLessThan(videoCardFilter.getCore());
                default -> videoCards = videoCardRepository.findAll();
            }
        } else if (videoCardFilter.hasMemoryBandwidthStartFilter() && videoCardFilter.hasMemoryBandwidthEndFilter()) {
            videoCards = videoCardRepository.findByMemoryBandwidthBetween(videoCardFilter.getMemoryBandwidthStart(),
                    videoCardFilter.getMemoryBandwidthEnd());
        } else if (videoCardFilter.hasNameFilter()) {
            videoCards = videoCardRepository.findByNameContainsIgnoreCase(videoCardFilter.getName());
        } else {
            videoCards = videoCardRepository.findAll();
        }
        return videoCardMapper.map(videoCards);
    }
}

