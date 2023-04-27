package com.javarush.maximov.catalog.motherboard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotherboardFilter {
    private Integer cpuCoreVoltageStart;
    private Integer cpuCoreVoltageEnd;
    private Boolean clockGenerator;
    private Boolean dualBIOS;
    private Boolean biosFlash;
    private Boolean clearCMOS;
    private String name;

    public boolean hasCpuCoreVoltageStartFilter() {
        return cpuCoreVoltageStart != null;
    }

    public boolean hasCpuCoreVoltageEndFilter() {
        return cpuCoreVoltageEnd != null;
    }

    public boolean hasClockGeneratorFilter() {
        return clockGenerator != null && clockGenerator;
    }

    public boolean hasDualBIOSFilter() {
        return dualBIOS != null && dualBIOS;
    }

    public boolean hasBiosFlashFilter() {
        return biosFlash != null && biosFlash;
    }

    public boolean hasClearCMOSFilter() {
        return clearCMOS != null && clearCMOS;
    }

    public boolean hasNameFilter() {
        return name != null && !name.isEmpty();
    }
}
