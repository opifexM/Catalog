package com.javarush.maximov.catalog.computer;


import com.javarush.maximov.catalog.videocard.VideoCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        log.info("Fetching all computers");
        List<Computer> computerList = computerRepository.findAll(Sort.by("id"));
        return computerMapper.map(computerList);
    }

    @Override
    public Optional<ComputerDto> findById(long id) {
        log.info("Fetching computer with id: {}", id);
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        return optionalComputer.map(computerMapper::map);
    }

    @Override
    public void deleteAll() {
        log.info("Deleting all computers");
        computerRepository.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting computer with id: {}", id);
        computerRepository.deleteById(id);
    }

    @Override
    public void save(Computer computer) {
        log.info("Saving computer: {}", computer);
        for (VideoCard videoCard : computer.getVideoCards()) {
            List<Computer> computersUsingVideoCard = computerRepository.findAllByVideoCardsContaining(videoCard);
            if (!computersUsingVideoCard.isEmpty()) {
                log.warn("Computer not saved due to video card conflict: {}", computer);
                return;
            }
        }
        computerRepository.save(computer);
        log.info("Computer saved: {}", computer);
    }

    @Override
    public void saveList(Iterable<Computer> computers) {
        log.info("Saving list of computers");
        computerRepository.saveAll(computers);
        log.info("List of computers saved");
    }

    @Override
    public long count() {
        log.info("Counting computers");
        return computerRepository.count();
    }

    @Override
    public Iterable<ComputerDto> getComputerFiltered(ComputerFilter computerFilter) {
        log.info("Fetching filtered computers: {}", computerFilter);
        List<Computer> computersByFilter = computerRepository.findComputersByFilter(computerFilter);
        return computerMapper.map(computersByFilter);
    }
}

