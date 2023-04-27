package com.javarush.maximov.catalog.videocard;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideoCardMapper {
    VideoCardDto map(VideoCard videoCard);
    List<VideoCardDto> map(List<VideoCard> videoCards);
}
