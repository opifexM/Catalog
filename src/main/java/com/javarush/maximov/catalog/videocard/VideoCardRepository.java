package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.videocard.VideoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VideoCardRepository extends JpaRepository<VideoCard, Long> {
}
