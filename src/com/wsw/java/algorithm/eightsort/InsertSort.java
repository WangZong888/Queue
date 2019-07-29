package com.wsw.java.algorithm.eightsort;

import java.util.Arrays;

/**
 * @program: Queue
 * @description: 插入排序（Insertion Sorting）的基本思想是：
 * 把n个待排序的元素看成为一个有序表和一个无序表，
 * 开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，
 * 把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表。
 * @author: Mr.Wang
 * @create: 2019-07-18 23:33
 **/
public class InsertSort {


    public static void main(String[] args) {
        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("插入排序前");
        System.out.println(Arrays.toString(arr));

        long start = System.currentTimeMillis();
        inserSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("插入排序后");
        System.out.println(Arrays.toString(arr));
        System.out.println("插入排序的时间："+(end -start));
    }

    public static void inserSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            //定义要插入的数
            insertVal = arr[i];
            insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertVal找到插入的位置
            //说明
            //1.insertIndex>=0保证在给insertVal找插入位置，不越界
            //2.isnertVal < arr[insertIndex]待插入的数，还没找到插入的位置
            //3.就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//<升序,>降序
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex+1
            //这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
