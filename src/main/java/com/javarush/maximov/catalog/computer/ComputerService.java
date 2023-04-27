package com.javarush.maximov.catalog.computer;

import java.util.Optional;

public interface ComputerService {
    Iterable<ComputerDto> findAll();

    Optional<ComputerDto> findById(long id);

    void deleteAll();

    void save(Computer computer);

    void deleteById(long id);

    long count();

    Iterable<ComputerDto> getComputerFiltered(ComputerFilter computerFilter);
}
