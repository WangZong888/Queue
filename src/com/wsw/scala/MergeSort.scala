package com.wsw.scala

object MergeSort {

  def main(args: Array[String]): Unit = {

    //测试
   /* //数组
    val arr = Array(9,8,10,6,11,4,3,20,1)
    //临时数组大小等于 arr.length
    var temp = new Array[Int](arr.length)
    mergeAndSplitSort(arr, 0, arr.length - 1, temp)

    println(arr.mkString(","))*/
    val arr = new Array[Int](8000000)
    val temp = new Array[Int](arr.length)
    for(i <- 0 until  8000000){
      arr(i) = (Math.random()*80000000).toInt
    }

    println("排序前------------")
    println(arr.mkString(","))
    val start: Long = System.currentTimeMillis()
    mergeAndSplitSort(arr,0,arr.length-1,temp)
    val end: Long = System.currentTimeMillis()
    println("排序后-----------")
    println(arr.mkString(","))
    println("总共花费了 "+ (end-start))
  }


  //拆分的方法
  /**
    *
    * @param arr 原始数组
    * @param left 数组的最左边的元素的下标
    * @param right 数组最右边的下标
    * @param temp 临时数组
    */
  def mergeAndSplitSort(arr:Array[Int],left:Int,right:Int,temp:Array[Int]): Unit ={
    if(left < right){ //可以分
      var mid = (left+right)/2 //中间
      mergeAndSplitSort(arr,left,mid,temp)//向左边分解
      mergeAndSplitSort(arr,mid +1 ,right,temp) //向右边分解
      //合并
      merge(arr,left,mid,right,temp)
    }
  }

  //合并的方法
  /**
    *
    * @param arr   原始数组
    * @param left  表示第一个有序序列的最左边的的元素下标
    * @param mid   表示第一个有序序列的最右边的元素的下标 mid+1 就是第二个有序序列的最左边的元素的下标
    * @param right 表示第二个有序序列的最右边的元素下标
    * @param temp  临时数组，中转数组
    */
  def merge(arr: Array[Int], left: Int, mid: Int, right: Int, temp: Array[Int]): Unit = {
    var i = left // i表示第一个有序序列的最左边的元素的下标（变化）
    var j = mid + 1 //j表示第二个有序序列的最左边的元素的下标（变化）
    var t = 0 //表示temp数组的索引，每拷贝一个元素，t+1后移

    //第一个阶段
    //将第一个有序序列的元素和第二个有序序列的元素，按照从小到大的顺序
    //拷贝到temp中，注意某个序列会有剩余元素
    while (i <= mid && j <= right) {
      if (arr(i) <= arr(j)) {
        //将arr(i)添加到temp中
        temp(t) = arr(i)
        t += 1
        i += 1
      } else {
        temp(t) = arr(j)
        t += 1
        j += 1
      }
    }

    //第二个阶段
    //如果第一个序列列表有剩余，依次拷贝到temp中
    while (i <= mid) {
      temp(t) = arr(i)
      t+=1
      i+=1
    }
    //如果第二个有序序列有剩余，依次拷贝到temp中
    while(j<=right){
      temp(t) = arr(j)
      t+=1
      j+=1
    }
    //第三个阶段
    //将临时数组中的元素拷贝到原数组中，分别对各个序列的位置
    //每个阶段都有一个合并，并且将合并的数放在原数组中，这样就方便取两个序列的下标

    t = 0
    var tempLeft = left
    while(tempLeft <=right){
      arr(tempLeft)=temp(t)
      t+=1
      tempLeft+=1
    }
  }
}
