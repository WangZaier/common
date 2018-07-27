package com.wangzai.queue;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 栈实现的队列
 *
 * @author wangzai
 * @date 2018/5/12 下午10:25
 */
public class QueueStack<T> {

    Stack<T> stackPush = new Stack<>();

    Stack<T> stackPop = new Stack<>();


    public void push(T data) {
        //正常存入
        stackPush.push(data);
        importPop();
    }

    public T peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new EmptyStackException();
        }
        importPop();
        return stackPop.peek();
    }


    public T pop() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new EmptyStackException();
        }
        importPop();
        return stackPop.pop();

    }

    //检查是否满足倒入pop条件
    private void importPop() {
        //当pop栈wei空的时候
        if (stackPop.isEmpty()) {
            //push栈全部倒入pop栈
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueStack<String> stack = new QueueStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
