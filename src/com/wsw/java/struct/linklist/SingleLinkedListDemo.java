package com.wsw.java.struct.linklist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //添加数据
        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero1);//在中间节点添加同一个节点会卡死
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero2);//在尾添加同一个节点会无限循环添加最后一个
        //按编号的顺序加入
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
        //显示一把
        singleLinkedList.list();

        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//
//        System.out.println("修改后的链表情况~~");
//        singleLinkedList.list();


        //删除一个节点
        ///singleLinkedList.delete(1);
//        singleLinkedList.delete(4);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(3);



//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();
//
//        //测试一下 求单链表中有效节点的个数
//        System.out.println("有效的节点个数=" + getLenth(singleLinkedList.getHead()));
//
//        //测试一下看看是否得到了倒数第K个节点
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
//        System.out.println("res=" + res);

        //测试反转的结果
        //reverseList(singleLinkedList.getHead());

        //显示反转后的结果
        //System.out.println("反转后的结果");
        //singleLinkedList.list();

        //使用栈将链表从尾到头打印
        System.out.println("从尾到头打印");
        reversePrint(singleLinkedList.getHead());


    }

    //1)求单链表的有效个数

    /**
     * 获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
     * @param head 单链表的头节点
     * @return 返回的是有效节点的个数
     */
    public static int getLenth(HeroNode head){
        if(head.next == null){
            //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量，这里我们没有统计头节点
        HeroNode curNode = head.next;
        while(curNode != null){
            length++;
            curNode = curNode.next; //后移，遍历
        }
        return length;
    }
    //2)查找单链表中的倒数第k个节点

    /**
     * 思路：
     * 1）编写一个方法，接收head节点，同时接收一个index
     * 2）index 表示是倒数第index个节点
     * 3）先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4）得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * 5）如果找到了，则返回该节点，否则返回null
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index) {
        //判断如果链表为空，返回null
        if(head.next == null){
            return null;//链表为空，没有找到
        }
        //第一个遍历得到链表的长度
        int size = getLenth(head);
        //第二次遍历 size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if(index <= 0 || index > size){
            return null;
        }
        //定义一个辅助变量，for循环定位到倒数的index
        HeroNode curNode = head.next;
        for (int i = 0; i <size -index ; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    //3)单链表的反转
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null ||head.next.next == null) {
            return;
        }
        //定义一个辅助指针（变量），帮助我们遍历原来的链表
        HeroNode curr = head.next;
        HeroNode next = null;//指向当前节点[curr]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前端
        //需要理解
        while (curr != null) {
            next = curr.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            curr.next = reverseHead.next;//将curr的下一个节点指向新的链表的最前端
            reverseHead.next = curr;//将cur 连接到新的链表上
            curr = next; //让curr后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }
    //4)从头到尾打印单链表

    /**
     * 利用栈的数据结构，将各个节点压入到栈中，
     * 然后利用栈的先进后出的特点，就实现了效果
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        //创建一个临时节点
        HeroNode curr = head.next;
        //将链表中的所有节点压入栈中
        while(curr != null){
            stack.push(curr);
            curr = curr.next;//后移，这样就可以压入下一个栈
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size()> 0) {
            //stack的特点是先进后出
            System.out.println(stack.pop());
        }
    }


}

/**
 * 定义SingleLinkedList管理我们的英雄
 */
@SuppressWarnings("all")
class SingleLinkedList {
    //先初始化头节点，头节点不要动，如果在遍历的时候头节点变化了，将来就找不到链表的顶端,
    //不管什么操作，链表的头节点都不能动
    //不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * @return 返回头节点
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表中
     * 思路：当不考虑编号的顺序时
     *    1）找到当前链表的最后节点
     *    2）将最后这个节点的next指向新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }



    /**
     * 第2种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示）
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，我们找到的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认是false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            //上述节点都不满足，继续下一个节点--->后移，遍历当前链表
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp后面。node.next=xxx——>表示node指向下一个xxx。
            // 而node =xxx表示就是xxx的节点（node是xxx）
            heroNode.next = temp.next;
            temp.next = heroNode;
            //特别注意这两个的顺序不能调换。java程序是顺序执行的，
            //调换之后出现（heroNode.next = heroNode）对于单线链表插入来说会有歧义
        }
    }

    /**
     * 修改节点的信息，根据标号no来修改，即no不能改
     * 说明：
     * 根据newHeroNode的no来修改即可
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode){
        //首先判断是否为空,根据head头节点
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量,因为头节点不能动
        HeroNode temp = head.next;//直接拿head的下一个节点
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(temp == null){
                break;//已经遍历完链表
            }
            if(temp.no == newHeroNode.no){//说明已经找到了
                flag = true ;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号%d的节点，不能修改\n",newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路：
     * 1）head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 2）说明我们在比较时，是temp.next.no 和需要删除的节点的no作比较
     * @param no
     */
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点的位置
        while (true) {
            if(temp.next == null){
                //已经到链表的最后了
                break;
            }
            if (temp.next.no == no) {
                //已找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if(flag){
            //找到可以删除temp.next节点
            temp.next =temp.next.next;
        }else{
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }
    /**
     *  显示链表【遍历】
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表的最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp节点后移,一定要后移，不然就是个死循环
            temp = temp.next;
        }
    }
}

/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重写toString(),将字符串转成我们想要的格式
    // 所以当你要想按照你想要的格式取字符串一些对象的时候
    @Override
    public String toString() {
        return "HeroNode[" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                ']';
    }
}
