package com.javarush.maximov.catalog.powersupply;

import com.javarush.maximov.catalog.utils.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PowerSupplyServiceImpl implements PowerSupplyService {

    public static final String POWERSUPPLY_JSON = "powersupply.json";
    private final PowerSupplyRepository powerSupplyRepository;

    @Autowired
    public PowerSupplyServiceImpl(PowerSupplyRepository powerSupplyRepository) {
        this.powerSupplyRepository = powerSupplyRepository;
    }

    @Override
    public Iterable<PowerSupply> findAll() {
        return powerSupplyRepository.findAll(Sort.by("id"));
    }

    @Override
    public boolean existsById(long id) {
        return powerSupplyRepository.existsById(id);
    }

    @Override
    public void save(PowerSupply powerSupply) {
        powerSupplyRepository.save(powerSupply);
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
    public Iterable<PowerSupply> saveList(Iterable<PowerSupply> powerSupplies) {
        powerSupplyRepository.saveAll(powerSupplies);
        return powerSupplyRepository.findAll();
    }

    @Override
    public void loadListFromJson() {
        List<PowerSupply> powerSupplyList = JsonService.jsonToList(POWERSUPPLY_JSON, PowerSupply[].class);
        saveList(powerSupplyList);
    }

    @Override
    public long count() {
        return powerSupplyRepository.count();
    }
}

