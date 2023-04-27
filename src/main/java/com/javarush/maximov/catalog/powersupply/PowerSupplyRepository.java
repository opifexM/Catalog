package com.javarush.maximov.catalog.powersupply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {
    long count();
    @Query("SELECT MIN(ps.id) FROM PowerSupply ps")
    Long findMinId();

    @Query("SELECT MAX(ps.id) FROM PowerSupply ps")
    Long findMaxId();

    @Query("SELECT ps FROM PowerSupply ps WHERE LOWER(ps.model) LIKE LOWER(concat('%', :name, '%'))")
    List<PowerSupply> findByNameContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT ps FROM PowerSupply ps WHERE ps.powerOn12vLine BETWEEN :powerStart AND :powerEnd")
    List<PowerSupply> findByPowerBetween(@Param("powerStart") int powerStart, @Param("powerEnd") int powerEnd);

    @Query("SELECT ps FROM PowerSupply ps WHERE ps.fanSize >= :fanSize")
    List<PowerSupply> findByFanSizeGreaterThanEqual(@Param("fanSize") int fanSize);

    @Query("SELECT ps FROM PowerSupply ps WHERE ps.fanSize < :fanSize")
    List<PowerSupply> findByFanSizeLessThan(@Param("fanSize") int fanSize);
}
