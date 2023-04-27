package com.javarush.maximov.catalog.powersupply;

import java.util.Optional;

public interface PowerSupplyService {
    Iterable<PowerSupplyDto> findAll();

    void deleteAll();

    Optional<PowerSupplyDto> findDtoById(long id);

    Optional<PowerSupply> findById(long id);

    void save(PowerSupply powerSupply);

    void saveList(Iterable<PowerSupply> powerSupplies);

    void loadListFromJson(String file) ;

    long count();

    Long findMinId();

    Long findMaxId();

    Iterable<PowerSupplyDto> getPowersuppliesFiltred(PowerSupplyFilter powerSupplyFilter);
}
