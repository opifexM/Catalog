package com.javarush.maximov.catalog.computer;


import com.javarush.maximov.catalog.videocard.VideoCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerServiceImpl implements ComputerService {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;
    

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository, ComputerMapper computerMapper) {
        this.computerRepository = computerRepository;
        this.computerMapper = computerMapper;
    }

    @Override
    public Iterable<ComputerDto> findAll() {
        List<Computer> computerList = computerRepository.findAll(Sort.by("id"));
        return computerMapper.map(computerList);
    }

    @Override
    public Optional<ComputerDto> findById(long id) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        return optionalComputer.map(computerMapper::map);
    }

    @Override
    public void deleteAll() {
        computerRepository.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        computerRepository.deleteById(id);
    }

    @Override
    public void save(Computer computer) {
        for (VideoCard videoCard : computer.getVideoCards()) {
            List<Computer> computersUsingVideoCard = computerRepository.findAllByVideoCardsContaining(videoCard);
            if (!computersUsingVideoCard.isEmpty()) {
                return;
            }
        }
        computerRepository.save(computer);
    }

    @Override
    public void saveList(Iterable<Computer> computers) {
        computerRepository.saveAll(computers);
    }

    @Override
    public long count() {
        return computerRepository.count();
    }

    @Override
    public Iterable<ComputerDto> getComputerFiltered(ComputerFilter computerFilter) {
        List<Computer> computersByFilter = computerRepository.findComputersByFilter(computerFilter);
        return computerMapper.map(computersByFilter);
    }
}

