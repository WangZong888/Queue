package com.wsw.java.algorithm.eightsort;

import java.util.Arrays;

/**
 * @program: Queue
 * @description:
 * 冒泡排序（Bubble Sorting）的基本思想是：通过对待
 * 排序序列从前向后（从下标较小的元素开始）,依次比较
 * 相邻元素的值，若发现逆序则交换，使值较大
 * 的元素逐渐从前移向后部，就象水底下的气泡一样逐渐
 * 向上冒。
 * @author: Mr.Wang
 * @create: 2019-07-18 15:54
 **/
public class BubbleSort {


    public static void main(String[] args) {
        //测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);//生成一个[0,8000000)数
        }
        System.out.println("-------随机生成的数组-------");
        for (int i = 0; i <arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("-------排序后的数组-------");
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序的时间："+(end-start));
        for (int i = 0; i <arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;//临时变量
        //优化：只要某一趟排序中，没有发生一次交换，则提前结束排序，这就是优化
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {//进行数组的长度-1外层循环
            for (int j = 0; j < arr.length - 1 - i; j++) {//每一趟的次数都在减少
                //如果前面的数比后面的数大，则交换
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag) {//在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag = false;//一定要重置！！！，好进行下一次判断
            }
        }
    }
}
