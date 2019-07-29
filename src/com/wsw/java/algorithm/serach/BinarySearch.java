package com.wsw.java.algorithm.serach;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Queue
 * @description:
 * 二分查找：
 * 注意：使用二分查找的前提是 该数组是有序的.
 * @author: Mr.Wang
 * @create: 2019-07-15 20:28
 **/
public class BinarySearch {


    public static void main(String[] args) {
        //注意：使用二分查找的前提是 该数组是有序的.
        int arr[] ={1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,20};
        int resultIndex = binarySearch(arr, 0, arr.length - 1, 19);
        System.out.println("resultIndex="+resultIndex);
//
//        int arr[] = { 2,2,3,3,4,4,4,1, 8, 10, 89,1000,1000,1000, 1234 };
//        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 100);
//        System.out.println("list="+list);
    }

    /**
     * 二分查找
     * @param arr
     * 数组
     * @param left
     * 左边索引
     * @param right
     * 右边索引
     * @param findVal
     * 要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        //left > right时，说明递归整个数组，但是没有找到
       /* if (left > right ) {
            return -1;
        }*/
        int mid = (left+right)/2;//中间索引
        int midVal = arr[mid];//中间值

        if(findVal > midVal){//说明要找的值在中间值的右边，所以向右递归
            return binarySearch(arr,mid+1,right,findVal);
        } else if (findVal < midVal) {//要找的值在中间的左边，向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }

    /**
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        //当left>right时，说明递归整个数组，没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right)/2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch2(arr,mid+1,right,findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        }else{
//          思路分析
//			1. 在找到mid 索引值，不要马上返回
//			2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			4. 将Arraylist返回
            List<Integer> indexList = new ArrayList<>();

            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid -1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则，就吧temp 放入到indexList中
                indexList.add(temp);
                temp -= 1;//temp 左移
            }
            //把第一次找到的添加到数组中(中间值)
            indexList.add(mid);

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;

            while (true) {
                if (temp > arr.length-1 || arr[temp] != findVal) {
                    break;
                }
                ////否则，就temp 放入到 resIndexlist
                indexList.add(temp);
                temp++;//temp 右移
            }
            return indexList;
        }
    }
}
