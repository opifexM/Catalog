package com.javarush.maximov.catalog.powersupply;

import java.util.Optional;

public interface PowerSupplyService {
    Iterable<PowerSupply> findAll();

    void deleteAll();

    Optional<PowerSupply> findById(long id);

    boolean existsById(long id);

    void save(PowerSupply powerSupply);

    Iterable<PowerSupply> saveList(Iterable<PowerSupply> powerSupplies);

    void loadListFromJson();

    long count();

    Iterable<PowerSupply> findByNameContainsIgnoreCase(String name);

    Iterable<PowerSupply> findByPowerBetween(int powerStart, int powerEnd);

    Iterable<PowerSupply> findByFanSizeGreaterThanEqual(int fanSize);

    Iterable<PowerSupply> findByFanSizeLessThan(int fanSize);
}
