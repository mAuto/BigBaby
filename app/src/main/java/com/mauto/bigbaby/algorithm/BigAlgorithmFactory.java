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

            if (randomMark < boundaryPointer - 1) {
                int boundaryNum = randomDomain[boundaryPointer - 1];
                randomDomain[boundaryPointer - 1] = randomNum;
                randomDomain[randomMark] = boundaryNum;
            }

            LogSys.print("--> algorithm <--", i+" -> "+randomNum);
            boundaryPointer -= 1;
        }
    }

    // 给出一个整数数组，其中只有一个整数出现过奇数次，找出这个整数。
    public static int findOdd(int... array) {
        if (array == null || array.length == 0)
            return -1;

        int len = array.length;
        int result = 0;
        for (int i=0;i<len;i++) {
            result ^= array[i];
        }

        return result;
    }

}
