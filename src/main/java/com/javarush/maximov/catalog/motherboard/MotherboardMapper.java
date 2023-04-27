package com.javarush.maximov.catalog.motherboard;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MotherboardMapper {
    MotherboardDto map(Motherboard motherboard);
    List<MotherboardDto> map(List<Motherboard> motherboards);
}
