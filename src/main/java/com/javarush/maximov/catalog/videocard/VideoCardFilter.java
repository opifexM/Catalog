package com.javarush.maximov.catalog.videocard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoCardFilter {
    private Integer core;
    private String operator;
    private Double memoryBandwidthStart;
    private Double memoryBandwidthEnd;
    private String name;

    public boolean hasCoreFilter() {
        return core != null;
    }

    public boolean hasOperatorFilter() {
        return operator != null && !operator.isEmpty();
    }

    public boolean hasMemoryBandwidthStartFilter() {
        return memoryBandwidthStart != null;
    }

    public boolean hasMemoryBandwidthEndFilter() {
        return memoryBandwidthEnd != null;
    }

    public boolean hasNameFilter() {
        return name != null && !name.isEmpty();
    }
}
