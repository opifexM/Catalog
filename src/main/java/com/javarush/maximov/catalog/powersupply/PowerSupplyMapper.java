package com.javarush.maximov.catalog.powersupply;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PowerSupplyMapper {
    PowerSupplyDto map(PowerSupply powerSupply);
    List<PowerSupplyDto> map(List<PowerSupply> powerSupplies);
}
