package com.wsw.java.struct.circlesinglelinkedlist;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-11 15:15
 **/


public class Josephu {
    /**
     * Josephu(约瑟夫、约瑟夫环)  问题
     * Josephu  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
     * 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，
     * 直到所有人出列为止，由此产生一个出队编号的序列。
     */

    public static void main(String[] args) {
        //测试构建环形链表和遍历是否OK
        CircleSingleLinkedList circle = new CircleSingleLinkedList();
        circle.addBoy(5);//加入5个小孩节点
        circle.showBoy();

        System.out.println("----开始出出圈-----");
        //测试小孩出圈是否正确
        circle.countBoy(2,1,52);
    }

}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first;

    /**
     * 添加小孩节点，构成一个环形链表
     *
     * @param nums：要添加的节点个数
     */
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //定义一个辅助指针，帮助构建环形链表
        Boy currBoy = null;
        //使用for来构建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                currBoy = first;//让currBoy指向第一个小孩
            } else {
                currBoy.setNext(boy);//指向下一个
                boy.setNext(first);//构成环
                currBoy = boy;//currBoy移动，指向新的节点
            }
        }
    }

    /**
     * 遍历当前环形链表
     */
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩了");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy currBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", currBoy.getNo());
            if (currBoy.getNext() == first) {//说明已经遍历完毕
                break;
            }
            currBoy = currBoy.getNext();//currBoy后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩的出圈的顺序
     * @param startNo  ： 表示第几个小孩开始数数
     * @param countNum ： 表示数几下
     * @param nums     ：表示最初有几个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入的参数有误！请重新输入");
            return;
        }
        //创建一个辅助指针，帮助小孩完成出圈
        Boy helper = first;
        //需要创建一个辅助指针（变量）helper,事先应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {//说明helper指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动startNo-1次,因为需要到指定小孩的位置开始
        for (int i = 0; i < startNo - 1; i++) {
//            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动 countNum-1次，然后出圈
        //这里是循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {//说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动countNum-1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点(数到的节点)
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",first.getNo());
    }
}


//创建一个Boy类，表示一个节点
class Boy {

    private int no;   //编号
    private Boy next; //指向下一个节点，默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}