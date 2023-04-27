package com.javarush.maximov.catalog.powersupply;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerSupplyFilter {
    private Integer powerStart;
    private Integer powerEnd;
    private Integer fanSize;
    private String fanSizeOperator;
    private String name;

    public boolean hasPowerStart() {
        return powerStart != null;
    }

    public boolean hasPowerEnd() {
        return powerEnd != null;
    }

    public boolean hasFanSize() {
        return fanSize != null;
    }

    public boolean hasFanSizeOperatorFilter() {
        return fanSizeOperator != null && !fanSizeOperator.isEmpty();
    }

    public boolean hasNameFilter() {
        return name != null && !name.isEmpty();
    }
}
