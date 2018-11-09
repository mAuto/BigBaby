package com.mauto.bigbaby.algorithm;

import com.mauto.bigbaby.ut.LogSys;

import java.util.Random;

/**
 * Created by haohuidong on 18-11-9.
 */

public class BigAlgorithmFactory {

    // 给定一个整数n，生成n个随机数，不能重复。
    public static void realRandom(int boundary) {
        int[] randomDomain = new int[boundary];

        for (int i=0;i<boundary;i++) {
            randomDomain[i] = i+1;
        }

        Random random = new Random();
        int boundaryPointer = boundary;
        for (int i=0;i<boundary;i++) {
            int randomMark = random.nextInt(boundaryPointer);
            int randomNum = randomDomain[randomMark];
            int boundaryNum = randomDomain[boundaryPointer - 1];
            randomDomain[boundaryPointer - 1] = randomNum;
            randomDomain[randomMark] = boundaryNum;
            LogSys.print("--> algorithm <--", i+" -> "+randomNum);
            boundaryPointer -= 1;
        }
    }

}
