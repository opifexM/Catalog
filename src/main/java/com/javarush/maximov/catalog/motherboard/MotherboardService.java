package com.javarush.maximov.catalog.motherboard;

import java.util.Optional;

public interface MotherboardService {
    Iterable<MotherboardDto> findAll();

    void deleteAll();

    Optional<Motherboard> findById(long id);

    Optional<MotherboardDto> findDtoById(long id);

    void save(Motherboard motherboard);

    void saveList(Iterable<Motherboard> motherboards);

    void loadListFromJson(String file);

    long count();

    Long findMinId();

    Long findMaxId();

    Iterable<MotherboardDto> getMotherboardFiltered(MotherboardFilter filter);

}
