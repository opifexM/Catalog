package com.javarush.maximov.catalog.motherboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long>, JpaSpecificationExecutor<Motherboard> {
    long count();
    @Query("SELECT MIN(m.id) FROM Motherboard m")
    Long findMinId();
    @Query("SELECT MAX(m.id) FROM Motherboard m")
    Long findMaxId();
}
