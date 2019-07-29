package com.wsw.scala
import scala.util.control.Breaks._
object CircleLinkedList {

  def main(args: Array[String]): Unit = {

    val josephu = new Josephu
    //添加
    josephu.add(52)
    //遍历
    josephu.list()
    //约瑟夫
    josephu.boyout(2,2,5)
  }
}

class Josephu {
  //定义一个first 指针
  var first: Boy = null

  //小孩出圈
  //开始位置，数几下，
  def boyout(startNo:Int,countNum:Int,num:Int): Unit ={
    //校验一下
    if(first==null || startNo <1 || startNo > num) {
      println("没有小孩或者输入的参数有错")
      return
    }
    //1.先把temp定位到倒数第一个节点，temp表示是要删除的前一个节点
    //这步的作用是为了从first节点开始数数
    var temp = first
    breakable(while (true) {
      if(temp.next==first){
        break()
      }
      temp = temp.next
    })
    //2.将temp 定位到到startNo个小孩的前一个位置，移动startNo-1次
    //以first作为参照
    for(i <- 0 until  (startNo-1)){
      temp=temp.next
    }
    //3.开始数数，找到要出圈节点的前一个位置temp
   //countNum次，temp 应该移动 temp -1 次
    breakable {
      while (true) {
        for (i <- 0 until (countNum - 1)) {
          temp = temp.next
        }
        printf("\n出圈小孩%d ", temp.next.no)
        //判断是不是最后一个小孩
        if (temp.next == temp) {
          break()
        }
        //删除节点
        temp.next = temp.next.next
      }
    }
    //当循环退出后，说明temp就指向最后一个小孩
    printf("\n最后留在圈中的小孩%d",temp.no)


  }

  //遍历环形链表
  def list(): Unit ={
    if(first==null) {
      println("链表为空")
      return
    }
    var temp = first
    breakable(
      while (true){
        printf("\n no=%d",temp.no)
        if(temp.next==first){
          break()
        }
        temp = temp.next
      }
    )
  }

  //创建一个单向环形链表-并添加
  //num：圈中的个数
  def add(num: Int): Unit = {
    var currBoy: Boy = null
    for (i <- 1 to num) {
      val boy: Boy = new Boy(i)
      if (i == 1) {
        //如果是第一个小孩，构成环形
        first = boy
        first.next = first
        currBoy = first
      }else{
        currBoy.next=boy
        boy.next=first
        currBoy= boy
      }

    }
  }
}

class Boy(bNo: Int) {
  val no = bNo
  var next: Boy = null
}