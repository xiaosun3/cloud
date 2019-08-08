package com.cloud.sort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.BitSet;

/**
 * Created by sunhaidi on 2019-05-20.
 */
@RunWith(SpringRunner.class)
public class BitmapTest {
    @Test
    public void testBitSet() {
        int[] array = new int[]{2, 4, 7, 10};
        BitSet bitSet = new BitSet(1);
        //因为底层是long数组实现，long长度为64 所以这里size也是64
        System.out.println(bitSet.size());
        //将数组内容组bitmap,这里其实最大空间10bit  实际使用4bit就可以表示array的4个数字是否存在
        for (int i = 0; i < array.length; i++) {
            bitSet.set(array[i], true);
        }

        System.out.println("下面开始遍历BitSet：");
        for (int i = 0; i < bitSet.size(); i++) {
            System.out.println(bitSet.get(i));
        }
    }

    private static final int CAPACITY = 16;//数据容量

    // 定义一个byte数组缓存所有的数据
    static int[] intArr = new int[]{3, 7, 5, 9, 1, 4, 2, 6, 8, 10, 15, 16, 13, 14, 12, 11};
    private byte[] dataBytes = new byte[2 << 3];
}
