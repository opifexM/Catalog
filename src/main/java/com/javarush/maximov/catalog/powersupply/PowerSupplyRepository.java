package com.javarush.maximov.catalog.powersupply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {
    long count();

    @Query("SELECT ps FROM PowerSupply ps WHERE LOWER(ps.model) LIKE LOWER(concat('%', :name, '%'))")
    Iterable<PowerSupply> findByNameContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT ps FROM PowerSupply ps WHERE ps.powerOn12vLine BETWEEN :powerStart AND :powerEnd")
    Iterable<PowerSupply> findByPowerBetween(@Param("powerStart") int powerStart, @Param("powerEnd") int powerEnd);

    @Query("SELECT ps FROM PowerSupply ps WHERE ps.fanSize >= :fanSize")
    Iterable<PowerSupply> findByFanSizeGreaterThanEqual(@Param("fanSize") int fanSize);

    @Query("SELECT ps FROM PowerSupply ps WHERE ps.fanSize < :fanSize")
    Iterable<PowerSupply> findByFanSizeLessThan(@Param("fanSize") int fanSize);


}
