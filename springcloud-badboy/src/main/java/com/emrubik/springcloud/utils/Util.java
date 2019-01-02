package com.emrubik.springcloud.utils;

import java.util.Random;

public class Util {

    public static int getRandomValue(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

}
