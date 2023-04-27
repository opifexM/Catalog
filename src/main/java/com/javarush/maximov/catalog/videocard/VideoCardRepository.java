package com.javarush.maximov.catalog.videocard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface VideoCardRepository extends JpaRepository<VideoCard, Long> {
    long count();
    @Query("SELECT MIN(vc.id) FROM VideoCard vc")
    Long findMinId();

    @Query("SELECT MAX(vc.id) FROM VideoCard vc")
    Long findMaxId();

    List<VideoCard> findByCoreFrequencyGreaterThanEqual(int coreFrequency);

    List<VideoCard> findByCoreFrequencyLessThan(int coreFrequency);

    List<VideoCard> findByMemoryBandwidthBetween(double memoryBandwidthStart, double memoryBandwidthEnd);

    List<VideoCard> findByNameContainsIgnoreCase(String name);
}
