package com.wsw.java.algorithm.eightsort;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-30 00:35
 **/
public class MergeSort {

    public static void main(String[] args) {
        int arr[] = new int[6000 * 10000];
        int temp[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000 * 10000);
        }
        long start = System.currentTimeMillis();
        splitAndMeger(arr,0,arr.length-1,temp);
        long end = System.currentTimeMillis();
        System.out.println("排序后的时间: " + (end - start));
    }


    /**
     * 拆分并合并
     * @param arr 原始数组
     * @param left 数组的最左边的下标
     * @param right 数组的最右边的下标
     * @param temp 临时数组
     */
    public static void splitAndMeger(int[] arr,int left,int right,int[] temp){
        if(left < right){ //可以拆分
            int mid = (left+right) /2;//取出中间索引
            splitAndMeger(arr,left,mid,temp); //向左拆分
            splitAndMeger(arr,mid+1,right,temp);//向右拆分
            //合并
            merge(arr,left,mid,right,temp);
        }
    }
    /**
     * 合并方法
     *
     * @param arr   原始数组
     * @param left  第一个有序序列的最左边的下标
     * @param mid   第一个有序序列的最右边的下标，且mid+1 是第二个有序序列的最左边的下标
     * @param right 是第二个有序序列的最右边的下标
     * @param temp  临时数组，中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //变化的第一个有序序列的最左边的下标
        int j = mid + 1; //变化的是第二个有序序列的最左边的下标
        int t = 0; //临时数组的下标

        //TODO 1.第一阶段：
        //TODO 将第一和第二个有序序列的元素，按照从小到大的顺序
        //TODO 拷贝到temp中，注意某个序列有剩余的元素
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //TODO 2.第二阶段
        //TODO 将序列中剩余的元素依次拷贝到temp中
        //如果第一个有序序列有剩余，则依次拷贝到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        //如果第二个有序序列有剩余，则依次拷贝到temp中
        while(j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }
        //TODO 3.第三阶段
        //TODO 将临时数组中元素拷贝到原始数组中，开始插入的位置与各个序列的位置对应
        //TODO 每个阶段都有合并，并且将合并的数放在原数组中，这样方便取两个序列的下标
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
