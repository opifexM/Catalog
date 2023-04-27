package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        List<VideoCard> videoCardList = videoCardRepository.findAll(Sort.by("id"));
        return videoCardMapper.map(videoCardList);
    }

    @Override
    public Optional<VideoCardDto> findDtoById(long id) {
        Optional<VideoCard> optionalVideoCard = videoCardRepository.findById(id);
        return optionalVideoCard.map(videoCardMapper::map);
    }

    @Override
    public Optional<VideoCard> findById(long id) {
        return videoCardRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        videoCardRepository.deleteAll();
    }

    @Override
    public void save(VideoCard videoCard) {
        videoCardRepository.save(videoCard);
    }

    @Override
    public void saveList(Iterable<VideoCard> videoCards) {
        videoCardRepository.saveAll(videoCards);
    }

    @Override
    public void loadListFromJson(String file) {
        List<VideoCard> videoCardList = JsonService.jsonToList(file, VideoCard[].class);
        saveList(videoCardList);
    }

    @Override
    public long count() {
        return videoCardRepository.count();
    }

    @Override
    public Long findMinId() {
        return videoCardRepository.findMinId();
    }

    @Override
    public Long findMaxId() {
        return videoCardRepository.findMaxId();
    }

    @Override
    public Iterable<VideoCardDto> getVideoCardFiltered(VideoCardFilter videoCardFilter) {
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

