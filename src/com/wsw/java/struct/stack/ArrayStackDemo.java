package com.wsw.java.struct.stack;

import java.util.Scanner;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-12 19:12
 **/
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(如栈)");
            System.out.println("pop:表示从栈中取出数据");
            System.out.println("请输入您的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

//定义一个ArrayStack 表示栈
class ArrayStack{
    private int maxSize ;//栈的大小
    private int[] stack;//使用数组模拟栈
    private int top = -1;//top表示栈顶，初始值为-1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满了
        if(isFull()){
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,将栈顶数据返回
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况，遍历栈，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}