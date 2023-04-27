package com.javarush.maximov.catalog.videocard;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link VideoCard} entity
 */
@Data
public class VideoCardDto {
    private long id;
    private String name;
    private int coreFrequency;
    private int numberOfPixelPipelines;
    private int peakFillrateMPixPerSec;
    private int numberOfTextureModulesPerPipeline;
    private int texturesPerClock;
    private int peakFillrateMTexelsPerSec;
    private int texturesPerPass;
    private int memoryFrequency;
    private int memoryBusWidth;
    private double memoryBandwidth;
}