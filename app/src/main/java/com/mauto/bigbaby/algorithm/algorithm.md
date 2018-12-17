### Algorithm

#### 1 给定一个整数边界值n，生成n个不重复的随机数。[](#algorithm_0)
&emsp;&emsp;**基本思路** ：使用一个大小为n的一元数组array，初始值为1 ～ n。定义一个整数m来代表一个临时的边界，初始值m=n。然后使用Random的nextInt方法获取m范围内的一个随机数t，然后将这个t用作数组的脚标，获取对应的值即为返回的随机数。最后将array\[t\]与array\[m-1\]的值进行交换。以此类推，就可以获得n个不重复的随机数。
```java
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

#### 2 一个整数数组中只有一个数出现过奇数次，其余的都出现过偶数次，找到这个出现奇数次的数，要求空间复杂度O(n)，时间复杂度O(1)。[](#algorithm_1)
&emsp;&emsp;**基本思路** ：基本的位运算中的异或运算：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。也就是说，一个偶数次出现的书最终会被运算为0,那么数组中就将剩下唯一的一个奇数次整数。   
```java
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
```   
> 按位与运算(&)：两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0。  
>
>按位或运算符（|)：两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0。
>
>位非运算符（~）：如果位为0，结果是1，如果位为1，结果是0.


#### 3 从一个有序数组中删除重复项, 并返回新数组的长度，要求空间复杂度O(n)，时间复杂度O(1)。[](#algorithm_2)
```java
public int RemoveDuplicates(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int len = 0;
        for (int i=1;i<array.length;i++) {
            if (array[len] != array[i])
                array[++len] = array[i];
        }
        return len;
    }
```

#### 4 从一个旋转的不重复的有序数组中查找某个值的位置，要求空间复杂度O(n)，时间复杂度O(1)。[](#algorithm_3)
&emsp;&emsp;**基本思路** ：对于一个有序数组采用二分法是正确的，但是难点在于怎么确定边界的取值。
```java
public int SearchInRotatedSortedArray(int[] array, int target) {
        if (array == null || array.length == 0)
            return -1;
        int first = 0, last = array.length;
        while (first  != last) {
            int mid = first + (last - first)/2;

            if (array[mid] == target)
                return mid;

            if (array[first] < array[mid]) {
                if (array[first] <= target && target <= array[mid])
                    last = mid;
                else  first = mid + 1;
            } else {
                if (array[first] <= target && target <= array[mid])
                    last = mid;
                else  first = mid + 1;
            }
        }
        return -1;
    }
```

