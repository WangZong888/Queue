package com.wsw.java.struct.stack;

/**
 * @program: Queue
 * @description:
 * @author: Mr.Wang
 * @create: 2019-07-12 19:53
 **/
public class Calculator {

    public static void main(String[] args) {
        //处理多位数的
        String expression = "2*4-10+4/2*5+4-2*4+3";

        //创建2个栈，一个数栈，一个符号栈
        ArrayStack2 numStack2 = new ArrayStack2(100);
        ArrayStack2 operStack2 = new ArrayStack2(100);

        //定义相关变量
        int index = 0;//用于扫描
        int num1 ;
        int num2 ;
        int oper ;
        int res ;
        char ch ;//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数

        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字节
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack2.isOper(ch)) {//如果是运算符
                //判断当前的操作符是否为空
                if (!operStack2.isEmpty()) {
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到结果入数栈，然后将当前的操作符入符号栈
                    if (operStack2.priority(ch) <= operStack2.priority(operStack2.peek())) {
                        num1 = numStack2.pop();
                        num2 = numStack2.pop();
                        oper = operStack2.pop();
                        res = numStack2.cal(num1, num2, oper);
                        System.out.println("res1= "+res);
                        //把运算结果入数栈
                        numStack2.push(res);
                        //然后将当前的操作符入符号栈
                        operStack2.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack2.push(ch);
                    }
                } else {
                    //如果为空直接入符号栈
                    operStack2.push(ch);
                }
            }else {//如果是数，直接入数栈
                //分析思路：
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2.在处理数，就需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量 字符串 ，用于拼接

                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack2.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意看后一位，不是index++
                    if (operStack2.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack2.push(Integer.parseInt(keepNum));
                        //keepNum全局变量，必须清空，不然数据一直累加
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack2.isEmpty()) {
                break;
            }
            num1 = numStack2.pop();
            num2 = numStack2.pop();
            oper = operStack2.pop();
            res = numStack2.cal(num1,num2,oper);

            System.out.println("res2= "+res);
            numStack2.push(res);//入栈
        }
        //将数栈的最后数，pop出就是结果
        int res2 = numStack2.pop();
        System.out.printf("表达式 %s = %d ",expression,res2);
    }
}

//定义一个ArrayStack 表示栈
@SuppressWarnings("all")
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//使用数组模拟栈
    private int top = -1;//top表示栈顶，初始值为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回当前栈顶的值，但是不是真的pop
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满了
        if (isFull()) {
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
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符优先级，优先级由程序员来确定，优先级使用数字表示
     * 数字越大，优先级越高
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//目前只考虑+、-、*、/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;

    }
}