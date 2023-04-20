package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return videoCardRepository.findAll();
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
}

