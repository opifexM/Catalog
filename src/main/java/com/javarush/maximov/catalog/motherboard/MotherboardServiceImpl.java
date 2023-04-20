package com.javarush.maximov.catalog.motherboard;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MotherboardServiceImpl implements MotherboardService {

    public static final String MOTHERBOARD_JSON = "motherboard.json";
    private final MotherboardRepository motherboardRepository;

    @Autowired
    public MotherboardServiceImpl(MotherboardRepository motherboardRepository) {
        this.motherboardRepository = motherboardRepository;
    }

    @Override
    public Iterable<Motherboard> findAll() {
        return motherboardRepository.findAll();
    }

    @Override
    public boolean existsById(long id) {
        return motherboardRepository.existsById(id);
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
    public void deleteAll() {
        motherboardRepository.deleteAll();
    }

    @Override
    public Iterable<Motherboard> saveList(Iterable<Motherboard> motherboards) {
        motherboardRepository.saveAll(motherboards);
        return motherboardRepository.findAll();
    }

    @Override
    public void loadListFromJson() {
        List<Motherboard> motherboardList = JsonService.jsonToList(MOTHERBOARD_JSON, Motherboard[].class);
        saveList(motherboardList);
    }
}

