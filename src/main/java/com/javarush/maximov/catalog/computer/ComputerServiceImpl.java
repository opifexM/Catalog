package com.javarush.maximov.catalog.computer;


import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ComputerServiceImpl implements ComputerService {

    public static final String COMPUTER_JSON = "computer.json";
    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public Iterable<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public boolean existsById(long id) {
        return computerRepository.existsById(id);
    }

    @Override
    public Optional<Computer> findById(long id) {
        return computerRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        computerRepository.deleteAll();
    }

    @Override
    public void save(Computer computer) {
        computerRepository.save(computer);
    }

    @Override
    public Iterable<Computer> saveList(Iterable<Computer> computers) {
        computerRepository.saveAll(computers);
        return computerRepository.findAll();
    }

    @Override
    public void loadListFromJson() {
        List<Computer> computerList = JsonService.jsonToList(COMPUTER_JSON, Computer[].class);
        saveList(computerList);
    }
}

