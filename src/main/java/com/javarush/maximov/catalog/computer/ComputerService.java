package com.javarush.maximov.catalog.computer;

import com.javarush.maximov.catalog.motherboard.Motherboard;

import java.util.Optional;

public interface ComputerService {
    Iterable<Computer> findAll();

    Optional<Computer> findById(long id);

    boolean existsById(long id);

    void deleteAll();

    void save(Computer computer);

    Iterable<Computer> saveList(Iterable<Computer> computers);

    void loadListFromJson();
}
