package com.javarush.maximov.catalog.motherboard;

import java.util.Optional;

public interface MotherboardService {
    Iterable<Motherboard> findAll();

    void deleteAll();

    Optional<Motherboard> findById(long id);

    boolean existsById(long id);

    void save(Motherboard motherboard);

    Iterable<Motherboard> saveList(Iterable<Motherboard> motherboards);

    void loadListFromJson();
}
