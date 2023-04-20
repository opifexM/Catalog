package com.javarush.maximov.catalog.powersupply;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "power_supply")
public class PowerSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "total_passport_power")
    private int totalPassportPower;

    @Column(name = "power_on_12v_line")
    private int powerOn12vLine;

    @Column(name = "oem")
    private String oem;

    @Column(name = "platform")
    private String platform;

    @Column(name = "secondary_circuit")
    private String secondaryCircuit;

    @Column(name = "overclocking")
    private boolean overclocking;

    @Column(name = "fan_operating_mode")
    private String fanOperatingMode;

    @Column(name = "noise_level")
    private String noiseLevel;

    @Column(name = "speeds_at_medium_loads")
    private String speedsAtMediumLoads;

    @Column(name = "speeds_at_full_load")
    private String speedsAtFullLoad;

    @Column(name = "fan_bearing")
    private String fanBearing;

    @Column(name = "fan_size_mm")
    private int fanSize;

    @Column(name = "capacitors")
    private String capacitors;

    @Column(name = "level_of_dynamic_stabilization")
    private String levelOfDynamicStabilization;

    @Column(name = "pulsation_level")
    private String pulsationLevel;

    @Column(name = "length_of_power_cables_cm")
    private String lengthOfPowerCables;

    @Column(name = "modular_cable_connection")
    private boolean modularCableConnection;

    @Column(name = "length_mm")
    private int length;
}