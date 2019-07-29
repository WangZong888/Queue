package com.wsw.java.struct.arrayqueue;

import java.util.Scanner;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-06-10 00:15
 **/
@SuppressWarnings("all")
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        //创建一个队列
        CircleArray queue = new CircleArray(4);//这里设置4，说明其队列的有效数据最大是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g'://取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default :
                    break;
            }
        }
        System.out.println("程序退出......");
    }
}

/**
 * 环形队列，可以重复使用数组
 * 1.当队列满时，条件是(rear+1)%maxSize==front
 * 2.当队列为空时，rear==front
 * 3.当我们这样分析，队列中的有效数据的个数为 (rear+maxSize-front)%maxSize
 *
 */
class CircleArray {
    private int maxSize;//表示数组的最大容量
    //front 变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
    //front的初始值=0
    private int front;//队列头
    //rear 变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做为约定
    //rear的初始值=0
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }


    //判断队列是否满了
    public boolean isFull() {
        return (rear+1)%maxSize==front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满了，不能加入数据....");
            return;
        }
       //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.降临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据s
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据...");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        //动脑
        for (int i = front ; i <front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
    public int size(){
        //例如 rear = 2 ,front = 1 , maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据...");
        }
        return arr[front];
    }
}