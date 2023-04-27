package com.javarush.maximov.catalog.motherboard;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MotherboardSpecification implements Specification<Motherboard> {

    private final transient MotherboardFilter filter;

    public MotherboardSpecification(MotherboardFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Motherboard> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder builder) {

        Stream<Predicate> predicateStream = Stream.<Supplier<Predicate>>of(
                        () -> filter.hasCpuCoreVoltageStartFilter()
                                ? builder.greaterThanOrEqualTo(root.get("cpuCoreVoltagePhaseCount"),
                                        filter.getCpuCoreVoltageStart())
                                : null,
                        () -> filter.hasCpuCoreVoltageEndFilter()
                                ? builder.lessThanOrEqualTo(root.get("cpuCoreVoltagePhaseCount"),
                                        filter.getCpuCoreVoltageEnd())
                                : null,
                        () -> filter.hasClockGeneratorFilter()
                                ? builder.equal(root.get("bclkClockGenerator"), filter.getClockGenerator())
                                : null,
                        () -> filter.hasDualBIOSFilter()
                                ? builder.equal(root.get("dualBIOS"), filter.getDualBIOS())
                                : null,
                        () -> filter.hasBiosFlashFilter()
                                ? builder.equal(root.get("biosFlashWithoutCompatibleCPU"), filter.getBiosFlash())
                                : null,
                        () -> filter.hasClearCMOSFilter()
                                ? builder.equal(root.get("rearIOClearCMOS"), filter.getClearCMOS())
                                : null,
                        () -> filter.hasNameFilter()
                                ? builder.like(builder.lower(root.get("boardModel")),
                                "%" + filter.getName().toLowerCase() + "%")
                                : null
                )
                .parallel()
                .map(Supplier::get)
                .filter(Objects::nonNull);
        return builder.and(predicateStream.toArray(Predicate[]::new));
    }
}
