package com.javarush.maximov.catalog.motherboard;

import com.javarush.maximov.catalog.utils.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MotherboardServiceImpl implements MotherboardService {

    private final MotherboardRepository motherboardRepository;

    private final MotherboardMapper motherboardMapper;

    @Autowired
    public MotherboardServiceImpl(MotherboardRepository motherboardRepository, MotherboardMapper motherboardMapper) {
        this.motherboardRepository = motherboardRepository;
        this.motherboardMapper = motherboardMapper;
    }

    @Override
    public Iterable<MotherboardDto> findAll() {
        log.info("Fetching all motherboards");
        List<Motherboard> motherboardList = motherboardRepository.findAll(Sort.by("id"));
        return motherboardMapper.map(motherboardList);
    }

    @Override
    public void save(Motherboard motherboard) {
        log.info("Saving motherboard: {}", motherboard);
        motherboardRepository.save(motherboard);
        log.info("Motherboard saved: {}", motherboard);
    }

    @Override
    public Optional<Motherboard> findById(long id) {
        log.info("Fetching motherboard with id: {}", id);
        return motherboardRepository.findById(id);
    }

    @Override
    public Optional<MotherboardDto> findDtoById(long id) {
        log.info("Fetching motherboard DTO with id: {}", id);
        Optional<Motherboard> motherboardOptional = motherboardRepository.findById(id);
        return motherboardOptional.map(motherboardMapper::map);
    }

    @Override
    public void deleteAll() {
        log.info("Deleting all motherboards");
        motherboardRepository.deleteAll();
    }

    @Override
    public void saveList(Iterable<Motherboard> motherboards) {
        log.info("Saving list of motherboards");
        motherboardRepository.saveAll(motherboards);
        log.info("List of motherboards saved");
    }

    @Override
    public void loadListFromJson(String file) {
        log.info("Loading motherboards from JSON file: {}", file);
        List<Motherboard> motherboardList = JsonService.jsonToList(file, Motherboard[].class);
        saveList(motherboardList);
    }

    @Override
    public long count() {
        log.info("Counting motherboards");
        return motherboardRepository.count();
    }

    @Override
    public Long findMinId() {
        log.info("Finding minimum motherboard id");
        return motherboardRepository.findMinId();
    }

    @Override
    public Long findMaxId() {
        log.info("Finding maximum motherboard id");
        return motherboardRepository.findMaxId();
    }

    @Override
    public Iterable<MotherboardDto> getMotherboardFiltered(MotherboardFilter filter) {
        log.info("Fetching filtered motherboards: {}", filter);
        Specification<Motherboard> specification = new MotherboardSpecification(filter);
        List<Motherboard> motherboardList = motherboardRepository.findAll(specification);
        return motherboardMapper.map(motherboardList);
    }
}




