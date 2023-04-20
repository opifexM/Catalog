package com.javarush.maximov.catalog.computer;

import com.javarush.maximov.catalog.motherboard.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
    long count();
}
