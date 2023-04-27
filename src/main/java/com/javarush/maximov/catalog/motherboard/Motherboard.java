package com.javarush.maximov.catalog.motherboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "motherboards")
public class Motherboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chipset")
    private String chipset;

    @Column(name = "board_vendor")
    private String boardVendor;

    @Column(name = "board_model")
    private String boardModel;

    @Column(name = "form_factor")
    private String formFactor;

    @Column(name = "cpu_core_voltage_controller")
    private String cpuCoreVoltageController;

    @Column(name = "controller_phase_mode")
    private String controllerPhaseMode;

    @Column(name = "cpu_core_vrm_Doublers")
    private String cpuCoreVRMDoublers;

    @Column(name = "cpu_core_voltage_phase_count")
    private int cpuCoreVoltagePhaseCount;

    @Column(name = "mosfet_or_powerstage_combo")
    private String mosfetOrPowerstageCombo;

    @Column(name = "igpu_doubler_quadrupler_voltage_controller")
    private String iGPUDoublerQuadruplerVoltageController;

    @Column(name = "igpu_voltage_phase_count")
    private int iGPUVoltagePhaseCount;

    @Column(name = "dual_mosfet_or_Powerstage")
    private String dualMosfetOrPowerstage;

    @Column(name = "bclk_clock_generator")
    private boolean bclkClockGenerator;

    @Column(name = "debug_feature")
    private String debugFeature;

    @Column(name = "dual_bios")
    private boolean dualBIOS;

    @Column(name = "bios_flash_without_compatible_cpu")
    private boolean biosFlashWithoutCompatibleCPU;

    @Column(name = "rear_io_clear_cmos")
    private boolean rearIOClearCMOS;
}