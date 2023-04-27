package com.javarush.maximov.catalog.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Randomizer {
    private Randomizer() {
    }

    public static int randomIntFromToNotInclude(int min, int max) {
        log.info("Generating random integer between {} and {}", min, max);
        if (min == max) {
            return min;
        }
        int randomInt = ThreadLocalRandom.current().nextInt(min, max);
        log.info("Generated random integer: {}", randomInt);
        return randomInt;
    }
}
