package com.kxw.algorithm.Queue;

/**
 * Created by kingsonwu on 18/3/22.
 */

import java.util.Stack;

public class Queue {

    private Stack stack1;
    private Stack stack2;

    public Queue() {

        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void push(Integer element) {
        stack1.push(element);
    }

    public Integer pop() {

        if (stack2.size() == 0) {
            while (stack1.size() > 0) {
                stack2.push(stack1.pop());
            }
        }

        return (Integer)stack2.pop();

    }

    public int size() {
        return stack1.size() + stack2.size();
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        System.out.println(queue.pop());
        System.out.println(queue.pop());

        queue.push(5);
        queue.push(6);
        queue.push(7);
        queue.push(8);

        while (queue.size() > 0) {
            System.out.println(queue.pop());
        }

    }

}



