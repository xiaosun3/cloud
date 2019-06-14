package con.cloud.sort;

/**
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
}
