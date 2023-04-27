package com.javarush.maximov.catalog.motherboard;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        List<Motherboard> motherboardList = motherboardRepository.findAll(Sort.by("id"));
        return motherboardMapper.map(motherboardList);
    }

    @Override
    public void save(Motherboard motherboard) {
        motherboardRepository.save(motherboard);
    }

    @Override
    public Optional<Motherboard> findById(long id) {
        return motherboardRepository.findById(id);
    }

    @Override
    public Optional<MotherboardDto> findDtoById(long id) {
        Optional<Motherboard> motherboardOptional = motherboardRepository.findById(id);
        return motherboardOptional.map(motherboardMapper::map);
    }

    @Override
    public void deleteAll() {
        motherboardRepository.deleteAll();
    }

    @Override
    public void saveList(Iterable<Motherboard> motherboards) {
        motherboardRepository.saveAll(motherboards);
    }

    @Override
    public void loadListFromJson(String file) {
        List<Motherboard> motherboardList = JsonService.jsonToList(file, Motherboard[].class);
        saveList(motherboardList);
    }

    @Override
    public long count() {
        return motherboardRepository.count();
    }

    @Override
    public Long findMinId() {
        return motherboardRepository.findMinId();
    }

    @Override
    public Long findMaxId() {
        return motherboardRepository.findMaxId();
    }

    @Override
    public Iterable<MotherboardDto> getMotherboardFiltered(MotherboardFilter filter) {
        Specification<Motherboard> specification = new MotherboardSpecification(filter);
        List<Motherboard> motherboardList = motherboardRepository.findAll(specification);
        return motherboardMapper.map(motherboardList);
    }
}




