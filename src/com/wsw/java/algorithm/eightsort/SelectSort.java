package com.wsw.java.algorithm.eightsort;

import java.util.Arrays;

/**
 * @program: Queue
 * @description:思路 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：
 * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
 * 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，
 * 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
 * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，
 * 得到一个按排序码从小到大排列的有序序列。
 * @author: Mr.Wang
 * @create: 2019-07-18 16:56
 **/
public class SelectSort {


    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序时间："+(end-start));
    }

    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {//外循环的次数是数组长度-1
            int minIndex = i;//假设最小为的下标为i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //说明需要交换最小值de位置
                int min = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = min;
            }
        }
    }
}
