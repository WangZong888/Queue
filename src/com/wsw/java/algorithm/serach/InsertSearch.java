package com.wsw.java.algorithm.serach;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-15 21:26
 **/
public class InsertSearch {

    public static void main(String[] args) {

        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
       // int arr[] = {1223, 123, 122, 121, 120, 111, 100, 99, 78};
        int index = insertValueSearch(arr, 0, arr.length - 1, 123);//找了一次
        //int index = binarySearch(arr, 0, arr.length-1, 1);//找了三次
        System.out.println("index = " + index);

        //System.out.println(Arrays.toString(arr));
    }


    //调用二分查找
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        System.out.println("二分查找被调用··");

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 编写插值算法
     * 插值查找算法，也要求数组是有序的
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 找到 返回index ，否则 返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("插值查找次数··");

        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界


        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }


        //求出mid，自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal || findVal < midVal) {//向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal || findVal > midVal) {//向左边递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


}
