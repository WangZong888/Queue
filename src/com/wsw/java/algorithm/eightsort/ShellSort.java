package com.wsw.java.algorithm.eightsort;

import java.util.Arrays;

/**
 * @program: Queue
 * @description:
 * 希尔排序法介绍：
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，
 * 也称为缩小增量排序。
 *
 * 希尔排序法基本思想：
 * 希尔排序是把记录按下标的一定增量分组，
 * 对每组使用直接插入排序算法排序；随着增量逐渐减少，
 * 每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * @author: Mr.Wang
 * @create: 2019-07-19 00:30
 **/
public class ShellSort {


    public static void main(String[] args) {
        int[] arr = new int[4000*10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000*10000);
        }

        long start = System.currentTimeMillis();
//        shellSort1(arr);//交换式
        shellSort2(arr);//移位式--性能好很多
        long end = System.currentTimeMillis();
        System.out.println("排序时间："+(end-start));
    }
    /**
     * 希尔排序时，对有序序列在插入时采用交换法
     *  性能比较差
     * @param arr
     */
    public static void shellSort1(int[] arr) {

        int temp =0;
//        int count = 0;

        for (int grp = arr.length /2 ;  grp > 0 ; grp /= 2) {//分组
            for (int i = grp; i <arr.length ; i++) {//分组内排序
                //遍历各组中所有的元素（共gap组，每组有多个元素），步长gap
                for (int j = i - grp; j >=0 ; j -=grp) {
                    //如果当前元素大于加上步长后的这个元素说明交换
                    if (arr[j] > arr[j + grp]) {
                        temp = arr[j];
                        arr[j] = arr[j+grp];
                        arr[j+grp] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

    /**
     * 对交换式的希尔排序进行优化->移位法
     * 也称缩小增量排序
     * @param arr
     */
    public static void shellSort2(int[] arr) {

        //增量grp,并逐步缩小增量
        for (int grp = arr.length / 2; grp >0 ; grp /= 2) {
            //从第grp个元素，逐个对其所在组进行直接插入排序 
            for (int i = grp; i <arr.length ; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - grp]) {
                    while (j - grp >= 0 && temp < arr[j - grp]) {
                        //移动
                        arr[j] = arr[j-grp];
                       j -= grp;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] =temp;
                }
            }
        }
    }

}
