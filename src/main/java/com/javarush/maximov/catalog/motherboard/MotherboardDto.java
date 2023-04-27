package com.javarush.maximov.catalog.motherboard;

import lombok.Data;

/**
 * A DTO for the {@link Motherboard} entity
 */

@Data
public class MotherboardDto {
    private long id;
    private String chipset;
    private String boardVendor;
    private String boardModel;
    private String formFactor;
    private String cpuCoreVoltageController;
    private String controllerPhaseMode;
    private String cpuCoreVRMDoublers;
    private int cpuCoreVoltagePhaseCount;
    private String mosfetOrPowerstageCombo;
    private String iGPUDoublerQuadruplerVoltageController;
    private int iGPUVoltagePhaseCount;
    private String dualMosfetOrPowerstage;
    private boolean bclkClockGenerator;
    private String debugFeature;
    private boolean dualBIOS;
    private boolean biosFlashWithoutCompatibleCPU;
    private boolean rearIOClearCMOS;
}