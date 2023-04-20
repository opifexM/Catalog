package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VideoCardServiceImpl implements VideoCardService {

    public static final String VIDEOCARD_JSON = "videocard.json";
    private final VideoCardRepository videoCardRepository;

    @Autowired
    public VideoCardServiceImpl(VideoCardRepository videoCardRepository) {
        this.videoCardRepository = videoCardRepository;
    }

    @Override
    public Iterable<VideoCard> findAll() {
        return videoCardRepository.findAll(Sort.by("id"));
    }

    @Override
    public boolean existsById(long id) {
        return videoCardRepository.existsById(id);
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
    public Iterable<VideoCard> saveList(Iterable<VideoCard> videoCards) {
        videoCardRepository.saveAll(videoCards);
        return videoCardRepository.findAll();
    }

    @Override
    public void loadListFromJson() {
        List<VideoCard> videoCardList = JsonService.jsonToList(VIDEOCARD_JSON, VideoCard[].class);
        saveList(videoCardList);
    }

    @Override
    public Iterable<VideoCard> findByCoreFrequencyGreaterThanEqual(int coreFrequency) {
        return videoCardRepository.findByCoreFrequencyGreaterThanEqual(coreFrequency);
    }

    @Override
    public Iterable<VideoCard> findByCoreFrequencyLessThan(int coreFrequency) {
        return videoCardRepository.findByCoreFrequencyLessThan(coreFrequency);
    }

    @Override
    public Iterable<VideoCard> findByMemoryBandwidthBetween(double memoryBandwidthStart, double memoryBandwidthEnd) {
        return videoCardRepository.findByMemoryBandwidthBetween(memoryBandwidthStart, memoryBandwidthEnd);
    }

    @Override
    public Iterable<VideoCard> findByNameContainsIgnoreCase(String name) {
        return videoCardRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public long count() {
        return videoCardRepository.count();
    }
}

