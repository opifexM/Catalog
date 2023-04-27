package com.javarush.maximov.catalog.powersupply;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        List<PowerSupply> powerSupplyList = powerSupplyRepository.findAll(Sort.by("id"));
        return powerSupplyMapper.map(powerSupplyList);
    }

    @Override
    public void save(PowerSupply powerSupply) {
        powerSupplyRepository.save(powerSupply);
    }

    @Override
    public Optional<PowerSupplyDto> findDtoById(long id) {
        Optional<PowerSupply> optionalPowerSupply = powerSupplyRepository.findById(id);
        return optionalPowerSupply.map(powerSupplyMapper::map);
    }

    @Override
    public Optional<PowerSupply> findById(long id) {
        return powerSupplyRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        powerSupplyRepository.deleteAll();
    }

    @Override
    public void saveList(Iterable<PowerSupply> powerSupplies) {
        powerSupplyRepository.saveAll(powerSupplies);
    }

    @Override
    public void loadListFromJson(String file) {
        List<PowerSupply> powerSupplyList = JsonService.jsonToList(file, PowerSupply[].class);
        saveList(powerSupplyList);
    }

    @Override
    public long count() {
        return powerSupplyRepository.count();
    }

    @Override
    public Long findMinId() {
        return powerSupplyRepository.findMinId();
    }

    @Override
    public Long findMaxId() {
        return powerSupplyRepository.findMaxId();
    }

    @Override
    public Iterable<PowerSupplyDto> getPowersuppliesFiltred(PowerSupplyFilter powerSupplyFilter) {
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

