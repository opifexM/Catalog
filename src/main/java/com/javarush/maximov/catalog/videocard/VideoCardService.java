package com.javarush.maximov.catalog.videocard;

import java.util.Optional;

public interface VideoCardService {
    Iterable<VideoCardDto> findAll();

    Optional<VideoCardDto> findDtoById(long id);

    Optional<VideoCard> findById(long id);

    void deleteAll();

    void save(VideoCard videoCard);

    void saveList(Iterable<VideoCard> videoCards);

    void loadListFromJson(String file);

    long count();

    Long findMinId();

    Long findMaxId();

    Iterable<VideoCardDto> getVideoCardFiltered(VideoCardFilter videoCardFilter);
}
