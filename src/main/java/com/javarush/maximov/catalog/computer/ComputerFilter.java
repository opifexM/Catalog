package com.javarush.maximov.catalog.computer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerFilter {
    private String name;
    private String motherboard;
    private String videoCards;
    private Boolean testing;
    private String computerStatus;

    public boolean hasNameFilter() {
        return name != null && !name.isEmpty();
    }

    public boolean hasMotherboardFilter() {
        return motherboard != null && !motherboard.isEmpty();
    }

    public boolean hasVideoCardsFilter() {
        return videoCards != null && !videoCards.isEmpty();
    }

    public boolean hasTesting() {
        return testing != null && testing;
    }

    public boolean hasComputerStatusFilter() {
        return computerStatus != null && !computerStatus.isEmpty();
    }
}
