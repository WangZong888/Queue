package com.wsw.scala

import scala.util.control.Breaks._

object SingleLinkedListDemo {

  def main(args: Array[String]): Unit = {
    var list = new SingleLinkedList
  var node1= new HeroNode(1,"宋江","及时雨")
    val node2= new HeroNode(3,"吴用","智多星")
    val node3= new HeroNode(2,"武松","行者")
     val node4= new HeroNode(4,"林冲","豹子头")

//    list.add(node1)
//    list.add(node2)
//    list.add(node3)
//    list.add(node4)
    list.addByOrder(node1)
    list.addByOrder(node2)
    list.addByOrder(node3)
    list.addByOrder(node4)

    println("遍历数据")
   list.list()

  }

}

//定义一个单链表的类
class SingleLinkedList {

  //操作的思路 创建(插入)->遍历->修改->删除
  //定义头节点
  private val node = new HeroNode(-1, "", "")


  //按顺序添加
  def addByOrder(heroNode: HeroNode): Unit ={
    var temp = node
    var flag = false //标识是否已经存在
    breakable(
      while(true){
        if(temp.next==null){
          break()
        }
        if(temp.next.no > heroNode.no){
          break()
        }
        if(temp.next.no == heroNode.no){
          flag = true
          break()
        }
        temp=temp.next
      }
    )
    if(flag){
      printf("\n要插入的编号%d已经存在了",heroNode.no)
    }else{
      heroNode.next=temp.next
      temp.next=heroNode
    }
  }

  //添加节点
  def add(heroNode: HeroNode): Unit = {
    //辅助变量
    var temp = node
    breakable {
      while (true) {
        if (temp.next == null) {
          break()
        }
        temp = temp.next
      }
    }
    //退出while说明已经找到了
    temp.next = heroNode
  }

  //遍历单链表
  def list(): Unit = {
    if (node.next == null) {
      println("链表为空")
      return
    }
    //定义临时变量
    var temp = node.next
    breakable(
      while(true){
        if(temp==null){
          break()
        }
        printf("\nno=%d name=%s nickname=%s", temp.no, temp.name, temp.nickname)
        temp = temp.next
      }
    )


  }

}

//创建节点对象
class HeroNode(hNo: Int, hName: String, hNickname: String) {
  val no = hNo
  val name = hName
  val nickname = hNickname
  //定义next，初始为null
  var next: HeroNode = null
}