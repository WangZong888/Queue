package com.wsw.java.struct.doublelinkedlist;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-11 10:07
 **/
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        System.out.println("双链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "林冲", "豹子头");


        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //doubleLinkedList.list();   //没添加数据
        //doubleLinkedList.add(hero1);//同一个节点只能添加一次
       /* doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero5);*/

        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        //doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero5);

        //显示双向链表
        doubleLinkedList.list();

//        //修改链表，根据no
//        doubleLinkedList.update(new HeroNode2(4,"公孙胜","入云龙"));
//        System.out.println("修改后的链表情况");
//        doubleLinkedList.list();
        //删除节点测试
        doubleLinkedList.delete(4);
        System.out.println("删除后的链表");
        doubleLinkedList.list();

    }
}

@SuppressWarnings("all")
//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化头节点，头节点不要动，不存放任何数据
    private HeroNode2 head2 = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead2() {
        return head2;
    }


    /**
     * 添加的第一种方式
     * 按添加顺序添加
     * 添加一个节点到双链表的最后
     * @param heroNode2
     */
    public void add(HeroNode2 heroNode2) {
        //因为头节点不能动，因此需要一个辅助变量来遍历 temp
        HeroNode2 temp = head2;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp 后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了最后
        //开始形成双向链表
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    /**
     * 添加的第二种方式
     * 根据排名将英雄添加到指定的位置
     * 如果有这个排名，则添加失败，给出提示
     * @param heroNode2
     */
    public void addByOrder(HeroNode2 heroNode2) {

        //定义一个辅助变量来遍历temp
        HeroNode2 temp = head2;
        boolean flag = false;//标识要添加的编号no是否已经存在，默认是false
        while (true) {
            if (temp.next == null) {//说明到链表的最后了
                break;
            }
            if (heroNode2.no < temp.next.no) {//位置找到，在temp后面插入
                break;
            } else if (heroNode2.no == temp.next.no) {//说明希望添加的节点的编号no已存在
                flag = true;//标识已存在
                break;
            }
            //上述节点都不满足，继续下一个节点--->后移
            temp = temp.next;
        }
        //根据flag判断
        if (flag) {//不能添加，说明编号已经存在
            System.out.printf("要添加的英雄编号 %d 已经存在，不能再添加\n",heroNode2.no);
        }else{
            //插入链表中，在temp后面,还得注意这是双向链表
            //先->next添加，再pre添加,绑定一个节点与一个节点的关系后，再去绑定其他的关系
            //也就是说两各节点先确定双向关系
            heroNode2.next = temp.next;
            //代码这里有问题
            //如果是最后一个节点，就不要执行下面这行代码，否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = heroNode2;
            }
            //---------------
            temp.next = heroNode2;
            heroNode2.pre = temp;
        }
    }

    /**
     * 修改一个节点的内容，双向链表的修改与单向链表的一样
     * 只是修改 节点类型变成了HeroNode2
     * @param heroNode2
     */
    public void update(HeroNode2 heroNode2) {
        //判断链表是否为空
        if(head2.next == null){
            System.out.println("链表为空~");
            return;
        }
        //根据no编号，找到需要修改的节点
        //定义一个辅助变量来遍历
        HeroNode2 temp = head2.next;
        boolean flag = false;//标识是否已经找到该节点
        while (true) {
            if(temp == null){
                break;//已经遍历完链表
            }
            if (temp.no == heroNode2.no) {
                flag =true;//说明找到该节点
                break;
            }
            temp = temp.next;//后移
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode2.name;
            temp.nickName = heroNode2.nickName;
        }else{
            //没有找到
            System.out.printf("没有找到编号 %d 的节点，不能修改\n",heroNode2.no);
        }
    }


    /**
     * 从双向链表中删除一个节点
     * 说明
     * 1,对于双向链表，我们可以直接找到要删除的这个节点
     * 2，找到后自我删除就好
     * @param no
     */
    public void delete(int no ) {
        //判断当前链表是否为空
        if (head2.next == null) {//空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        //定义辅助变量，遍历
        HeroNode2 temp = head2.next;
        boolean flag = false;//标识是否找到要删除的节点
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {//已经找到要删除的节点
                flag = true;
                break;
            }
            //不满足，继续遍历下一个节点
            temp = temp.next;//后移
        }
        //根据flag判断是否找到
        if (flag) {
            //找到节点temp，删除节点
            temp.pre.next = temp.next;
            //这里代码有点问题？
            //如果是最后一个节点，就不要执行下面的代码，否则会出现空指针异常
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }

        }else{
            //没找到要删除的节点
            System.out.printf("要删除的%d 节点不存在\n",no);
        }
    }

    /**
     * 遍历，显示双向链表的方法
     */
    public void list() {
        if (head2.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要定义一个辅助变量来遍历
        HeroNode2 temp = head2.next;
        while (temp != null) {
            System.out.println(temp);//输出节点的信息
            temp=temp.next;//后移，遍历
        }

    }
}




//定义HeroNode2，每个节点就是一个HeroNode
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点，默认是null
    public HeroNode2 pre;//指向前一个节点，默认是null

    //构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //为了显示，重写toString

    @Override
    public String toString() {
        return "HeroNode2 [no="+no+", name="+name+", nickName="+nickName+"]";
    }
}