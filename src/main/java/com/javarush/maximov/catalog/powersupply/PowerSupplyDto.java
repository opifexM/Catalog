package com.javarush.maximov.catalog.powersupply;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link PowerSupply} entity
 */
@Data
public class PowerSupplyDto {
    private Long id;
    private String model;
    private int totalPassportPower;
    private int powerOn12vLine;
    private String oem;
    private String platform;
    private String secondaryCircuit;
    private boolean overclocking;
    private String fanOperatingMode;
    private String noiseLevel;
    private String speedsAtMediumLoads;
    private String speedsAtFullLoad;
    private String fanBearing;
    private int fanSize;
    private String capacitors;
    private String levelOfDynamicStabilization;
    private String pulsationLevel;
    private String lengthOfPowerCables;
    private boolean modularCableConnection;
    private int length;
}