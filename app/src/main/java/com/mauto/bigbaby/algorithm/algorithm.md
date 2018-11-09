### Algorithm

#### [1 给定一个整数边界值n，生成n个不重复的随机数。](#algorithm_0)  
&emsp;&emsp;**基本思路** ：使用一个大小为n的一元数组array，初始值为1 ～ n。定义一个整数m来代表一个临时的边界，初始值m=n。然后使用Random的nextInt方法获取m范围内的一个随机数t，然后将这个t用作数组的脚标，获取对应的值即为返回的随机数。最后将array[t]与array[m-1]的值进行交换。以此类推，就可以获得n个不重复的随机数。   
```
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
```

#### 2 一个整数数组中只有一个数出现过奇数次，其余的都出现过偶数次，找到这个出现奇数次的数，要求空间复杂度O(n)，时间复杂度O(1)。
