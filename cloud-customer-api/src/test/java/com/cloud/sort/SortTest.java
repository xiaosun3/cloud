package com.cloud.sort;

/**
 * https://www.cnblogs.com/onepixel/p/7674659.html
 * Created by sunhaidi on 2019-05-13.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("de")
public class SortTest {

    static int[] array = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

    public static void main(String[] args) {
//        int[] arry = quit(array, 0, 9);
//        for (int i : arry) {
//            System.out.println(i);
//        }
//        System.out.println("-------------");
//        System.out.println(binarySearch(array, 8));
        System.out.println("------二分-------");
        binarySort();
//        System.out.println("\n-------冒泡------");
//        bubbleSort();
        System.out.println("\n-------选择------");
        selectionSort();
    }

    /**
     * 快排
     * 时间复杂度 最好：O（n） 最坏：O（n²）
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] quit(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int key = arr[l];  //先找出一个数作为基准数（这里取数组最中间的一位）

        while (l < h) {
            while (l < h && arr[h] >= key) {
                h--; //从后向前：寻找比基准数小的数据，如果找到，停下来
            }
            if (l < h) {  //“探测”到了符合要求的数据，则交换数据，继续顺着方向寻找
                arr[l] = arr[h];
                l++;
            }

            while (l < h && arr[l] <= key) {
                l++; //从前向后：寻找比基准数大的数据，如果找到，停下来
            }
            if (l < h) { ////“探测”到了符合要求的数据，则交换数据，继续顺着方向寻找
                arr[h] = arr[l];
                h--;
            }

        }
        arr[l] = key;
        if (l > low) quit(arr, low, l - 1);
        if (h < high) quit(arr, h + 1, high);
        return arr;
    }

    public static void ss(){


    }

    /**
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     *
     * @param arr
     */
    public static void insertionSort(int[] arr){
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    /**
     * 冒泡排序
     * 两两比较，大者交换位置，则每一圈比较最大的数就会冒到最后，循环直至遍历完所有
     * 时间复杂度O（n²）
     */
    private static void bubbleSort() {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }

    /**
     * 选择排序(Selection-sort)
     * 是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，
     * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     */
    public static void selectionSort() {
        int len = array.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIndex]) {     // 寻找最小的数
                    minIndex = j;                 // 将最小数的索引保存
                }
            }
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }


    /**
     * 二分法排序
     *
     * @return
     */
    public static void binarySort() {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];  //要插入的第i个元素
            int low = 0;
            int high = i - 1; //插入目标元素的前 i-1 个元素
            int mid = -1;
            while (low <= high) {
                mid = low + (high - low) / 2;
                if (array[mid] > temp) {
                    high = mid - 1;
                } else { // 元素相同时，也插入在后面的位置
                    low = mid + 1;
                }
            }
            // 目标位置 之后的元素 整体移动一位
            for (int j = i - 1; j >= low; j--) {
                array[j + 1] = array[j];
            }
            array[low] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }

    /**
     * 二分法查找,得是有序数组
     *
     * @param orderArray
     * @param searchValue
     * @return
     */
    public static int binarySearch(int[] orderArray, int searchValue) {
        if (orderArray == null) {
            return -1;
        }
        int start = 0;
        int end = orderArray.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (orderArray[middle] == searchValue) {
                return middle;
            }
            if (orderArray[middle] < searchValue) {
                start = middle + 1;
            }
            if (orderArray[middle] > searchValue) {
                end = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，
     * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * @return
     */
    public void quickSort(int array[], int top, int tail) {
        if (top >= tail) {
            return;
        }
        int left = top,
            right = tail;
        int temp = array[left];// 数组的第一作为中轴
        while (left < right) {
            while (left < right && array[right] >= temp) {
                right--;
            }
            array[left] = array[right];// 比中轴小的记录移动到低端
            while (left < right && array[left] < temp) {
                left++;
            }
            array[right] = array[left];
        }
        if (left == right) {
            array[left] = temp;
        }
        if (left != top) {
            quickSort(array, 0, left - 1);
        }
        if (right != tail) {
            quickSort(array, right + 1, array.length - 1);
        }
    }
}
