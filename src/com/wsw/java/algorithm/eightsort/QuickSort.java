package com.wsw.java.algorithm.eightsort;

import java.util.Arrays;

/**
 * @program: Queue
 * @description:
 * 快速排序（Quicksort）是对冒泡排序的一种改进。基本思想是：
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * @author: Mr.Wang
 * @create: 2019-07-23 16:01
 **/
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        long start = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        System.out.println("排序时间："+(end-start));
    }


    public static void quickSort(int[] arr, int left, int right) {

        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left+right)/2];//中轴值
        int temp = 0;//临时变量，作为交换时使用
        //while循环的目的是让比pivot值小放在左边
        //比pivot大的放在右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot) {
                l++;
            }
            ////在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot) {
                r--;
            }
            //如果 l >= r 说明pivot两边的值已经按照左边全部小于等于pivot值
            //右边全部大于等于pivot值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //下面这2步目的是跳出当前位置，防止死循环
            //如果交换完后，发现这个arr[l] == pivot值，相等 r--,前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现这个arr[r] == pivot值，相等 l++,后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        //如果 l ==r ,必须l++,r--,否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (left < r) {
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }


    }
}
