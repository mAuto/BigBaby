### Linear List



#### 1 从一个有序数组中删除重复项, 并返回新数组的长度，要求空间复杂度O(n)，时间复杂度O(1)。[](#algorithm_2)
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

#### 2 从一个旋转的不重复的有序数组中查找某个值的位置，要求空间复杂度O(n)，时间复杂度O(1)。[](#algorithm_3)
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