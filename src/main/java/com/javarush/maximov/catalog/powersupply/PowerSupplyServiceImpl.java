package com.javarush.maximov.catalog.powersupply;

import com.javarush.maximov.catalog.utils.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PowerSupplyServiceImpl implements PowerSupplyService {

    private final PowerSupplyRepository powerSupplyRepository;

    private final PowerSupplyMapper powerSupplyMapper;

    @Autowired
    public PowerSupplyServiceImpl(PowerSupplyRepository powerSupplyRepository, PowerSupplyMapper powerSupplyMapper) {
        this.powerSupplyRepository = powerSupplyRepository;
        this.powerSupplyMapper = powerSupplyMapper;
    }


    @Override
    public Iterable<PowerSupplyDto> findAll() {
        log.info("Fetching all power supplies");
        List<PowerSupply> powerSupplyList = powerSupplyRepository.findAll(Sort.by("id"));
        return powerSupplyMapper.map(powerSupplyList);
    }

    @Override
    public void save(PowerSupply powerSupply) {
        log.info("Saving power supply: {}", powerSupply);
        powerSupplyRepository.save(powerSupply);
        log.info("Power supply saved: {}", powerSupply);
    }

    @Override
    public Optional<PowerSupplyDto> findDtoById(long id) {
        log.info("Fetching power supply DTO with id: {}", id);
        Optional<PowerSupply> optionalPowerSupply = powerSupplyRepository.findById(id);
        return optionalPowerSupply.map(powerSupplyMapper::map);
    }

    @Override
    public Optional<PowerSupply> findById(long id) {
        log.info("Fetching power supply with id: {}", id);
        return powerSupplyRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        log.info("Deleting all power supplies");
        powerSupplyRepository.deleteAll();
    }

    @Override
    public void saveList(Iterable<PowerSupply> powerSupplies) {
        log.info("Saving list of power supplies");
        powerSupplyRepository.saveAll(powerSupplies);
        log.info("List of power supplies saved");
    }

    @Override
    public void loadListFromJson(String file) {
        log.info("Loading power supplies from JSON file: {}", file);
        List<PowerSupply> powerSupplyList = JsonService.jsonToList(file, PowerSupply[].class);
        saveList(powerSupplyList);
    }

    @Override
    public long count() {
        log.info("Counting power supplies");
        return powerSupplyRepository.count();
    }

    @Override
    public Long findMinId() {
        log.info("Finding minimum power supply id");
        return powerSupplyRepository.findMinId();
    }

    @Override
    public Long findMaxId() {
        log.info("Finding maximum power supply id");
        return powerSupplyRepository.findMaxId();
    }

    @Override
    public Iterable<PowerSupplyDto> getPowersuppliesFiltred(PowerSupplyFilter powerSupplyFilter) {
        log.info("Fetching filtered power supplies: {}", powerSupplyFilter);
        List<PowerSupply> powerSupplies;
        if (powerSupplyFilter.hasFanSize() && powerSupplyFilter.hasFanSizeOperatorFilter()) {
            switch (powerSupplyFilter.getFanSizeOperator()) {
                case "greaterThanEqual" ->
                        powerSupplies = powerSupplyRepository.findByFanSizeGreaterThanEqual(powerSupplyFilter.getFanSize());
                case "lessThan" ->
                        powerSupplies = powerSupplyRepository.findByFanSizeLessThan(powerSupplyFilter.getFanSize());
                default -> powerSupplies = powerSupplyRepository.findAll();
            }

        } else if (powerSupplyFilter.hasPowerStart() && powerSupplyFilter.hasPowerEnd()) {
            powerSupplies = powerSupplyRepository.findByPowerBetween(powerSupplyFilter.getPowerStart(),
                    powerSupplyFilter.getPowerEnd());

        } else if (powerSupplyFilter.hasNameFilter()) {
            powerSupplies = powerSupplyRepository.findByNameContainsIgnoreCase(powerSupplyFilter.getName());

        } else {
            powerSupplies = powerSupplyRepository.findAll();
        }
        return powerSupplyMapper.map(powerSupplies);
    }
}

