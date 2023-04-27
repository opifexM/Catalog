package com.javarush.maximov.catalog.computer;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComputerMapper {
    ComputerDto map(Computer computer);
    List<ComputerDto> map(List<Computer> computers);
}
