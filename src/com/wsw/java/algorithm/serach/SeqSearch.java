package com.wsw.java.algorithm.serach;

/**
 * @program: Queue
 * @description:
 * 线性查找
 * @author: Mr.Wang
 * @create: 2019-07-15 20:21
 **/
public class SeqSearch {


    public static void main(String[] args) {
        int arr[] ={1,8,-21,43,2,99};
        int index = seqSearch(arr,-12);
        if(index == -1){
            System.out.println("没有找到");
        }else{
            System.out.println("找到了，下标为="+index);
        }
    }
    /**
     * 这里实现的线性查找是找到一个满足条件的值就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
