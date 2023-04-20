package com.javarush.maximov.catalog.videocard;

import java.util.Optional;

public interface VideoCardService {
    Iterable<VideoCard> findAll();

    Optional<VideoCard> findById(long id);

    boolean existsById(long id);

    void deleteAll();

    void save(VideoCard videoCard);

    Iterable<VideoCard> saveList(Iterable<VideoCard> videoCards);

    void loadListFromJson();
}
