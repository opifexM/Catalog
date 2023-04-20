package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.videocard.VideoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface VideoCardRepository extends JpaRepository<VideoCard, Long> {
    Iterable<VideoCard> findByCoreFrequencyGreaterThanEqual(int coreFrequency);

    Iterable<VideoCard> findByCoreFrequencyLessThan(int coreFrequency);

    Iterable<VideoCard> findByMemoryBandwidthBetween(double memoryBandwidthStart, double memoryBandwidthEnd);

    Iterable<VideoCard> findByNameContainsIgnoreCase(String name);

    long count();

}
