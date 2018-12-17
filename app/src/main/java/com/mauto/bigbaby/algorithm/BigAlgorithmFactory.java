package com.mauto.bigbaby.algorithm;

import com.mauto.bigbaby.ut.LogSys;

import java.util.Random;

/**
 * Created by haohuidong on 18-11-9.
 */

public class BigAlgorithmFactory {

    // 给定一个整数n，生成n个随机数，不能重复。
    /**
     * 思路：
     * 使用一个大小为n的一元数组array，初始值为1 ～ n。定义一个整数m来代表一个临时的边界，初始值m=n。
     * 然后使用Random的nextInt方法获取m范围内的一个随机数t，然后将这个t用作数组的脚标，
     * 获取对应的值即为返回的随机数。最后将array[t]与array[m-1]的值进行交换。
     * 以此类推，就可以获得n个不重复的随机数。
     * */
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
    // 要求空间复杂度O(n)，时间复杂度O(1)
    /**
     * 思路：
     * 基本的位运算中的异或运算：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
     * 也就是说，一个偶数次出现的书最终会被运算为0,那么数组中就将剩下唯一的一个奇数次整数。
     * */
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
