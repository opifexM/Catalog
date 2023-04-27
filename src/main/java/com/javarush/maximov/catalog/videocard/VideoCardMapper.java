package com.javarush.maximov.catalog.videocard;

import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.powersupply.PowerSupplyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideoCardMapper {
    VideoCardDto map(VideoCard videoCard);
    List<VideoCardDto> map(List<VideoCard> videoCards);
}
