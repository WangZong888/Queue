package com.wsw.java.algorithm.eightsort;

import java.util.Arrays;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-29 23:04
 **/
public class RadixSort {

    public static void main(String[] args) {

//        int arr[] ={45,4,567,122,34,8,23,67,1234};

        //8000*10000 * 11 * 4 /1024 /1024 /1024 =3.3G 左右
        int arr[] = new int[6000 * 10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000 * 10000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序后的时间: " + (end - start));
    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        //TODO 1.求出数组中的最大位数
        int max = arr[0]; //先假定arr[0]是最大值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //根据最大值拿到最大位数
        int maxLength = (max + "").length();

        //TODO 2.定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明：
        // a.二维数组包含了10个一维数组
        // b.为了防止放入数的时候，数据溢出，则每个一位数组(桶), 大小定为arr.length
        // c.明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //TODO 3.为了记录每个桶中，实际存放了多少个数据，定义一个一位数组来记录各个桶的放入数据的个数
        //可以理解为：
        //比如：bucketElementCounts[0]=5,记录的就是 bucket[0] 0号桶放入数据的个数是5个
        int[] bucketElementCounts = new int[10];

        //开始循环
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //TODO 4.针对每个元素的对应位数进行排序处理，第一次是个位，第二次是10位，第三次是100位...
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应的位数的值
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //TODO 5.按照这个桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
            int index = 0; //原数组的初始下标
            //遍历每一桶，并将桶中的数据放入到原来的数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {//桶的个数 10
                //只有桶中有数据，我们才放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环改桶即第k个桶（即第k个一维数组） 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }
                //第(i+1)轮处理后，需要将每个bucketElementCounts[k] = 0 !!!!
                //否则或出现数据原数组数据增多 ,切记 切记
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮的排序处理 arr="+ Arrays.toString(arr));
        }
    }
}
