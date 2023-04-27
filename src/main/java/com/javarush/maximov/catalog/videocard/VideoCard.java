package com.javarush.maximov.catalog.videocard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "video_cards")
public class VideoCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "video_сhip")
    private String name;

    @Column(name = "core_frequency_mhz")
    private int coreFrequency;

    @Column(name = "num_pixel_pipelines")
    private int numberOfPixelPipelines;

    @Column(name = "peak_fillrate_mpixs")
    private int peakFillrateMPixPerSec;

    @Column(name = "num_texture_modules_per_pipeline")
    private int numberOfTextureModulesPerPipeline;

    @Column(name = "textures_per_clock")
    private int texturesPerClock;

    @Column(name = "peak_fillrate_mtexels")
    private int peakFillrateMTexelsPerSec;

    @Column(name = "textures_per_pass")
    private int texturesPerPass;

    @Column(name = "memory_frequency_mhz")
    private int memoryFrequency;

    @Column(name = "memory_bus_width_bits")
    private int memoryBusWidth;

    @Column(name = "memory_bandwidth_gbs")
    private double memoryBandwidth;
}
