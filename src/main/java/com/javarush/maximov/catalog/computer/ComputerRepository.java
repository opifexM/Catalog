package com.javarush.maximov.catalog.computer;

import com.javarush.maximov.catalog.videocard.VideoCard;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;


@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>, JpaSpecificationExecutor<Computer> {
    long count();
    List<Computer> findAllByVideoCardsContaining(VideoCard videoCard);

    default List<Computer> findComputersByFilter(ComputerFilter filter) {
        return findAll((root, query, builder) -> {
            Stream<Predicate> predicateStream = Stream.<Supplier<Predicate>>of(
                            () -> filter.hasNameFilter()
                                    ? builder.like(builder.lower(root.get("name")),
                                    "%" + filter.getName().toLowerCase() + "%")
                                    : null,
                            () -> filter.hasMotherboardFilter()
                                    ? builder.like(builder.lower(
                                    builder.concat(
                                            builder.concat(
                                                    root.get("motherboard").get("boardVendor"),
                                                    root.get("motherboard").get("chipset")
                                            ),
                                            root.get("motherboard").get("boardModel")
                                    )), "%" + filter.getMotherboard().toLowerCase() + "%")
                                    : null,
                            () -> filter.hasVideoCardsFilter()
                                    ? builder.like(
                                    builder.lower(root.join("videoCards").get("name")),
                                    "%" + filter.getVideoCards().toLowerCase() + "%")
                                    : null,
                            () -> filter.hasTesting()
                                    ? builder.isTrue(root.get("testing"))
                                    : null,
                            () -> filter.hasComputerStatusFilter()
                                    ? builder.equal(root.get("computerStatus"), ComputerStatus.valueOf(filter.getComputerStatus()))
                                    : null
                    )
                    .parallel()
                    .map(Supplier::get)
                    .filter(Objects::nonNull);

            query.orderBy(builder.asc(root.get("id")));
            return builder.and(predicateStream.toArray(Predicate[]::new));
        });
    }
}
