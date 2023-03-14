package ru.nsu.fit.rafik.task_1_1_2;


import java.util.ArrayList;
import java.util.List;



public class Main {


    public static void main(String[] args) {

        MyStack<Integer> stack2 = new MyStack();
        stack2.push(5);
        stack2.push(5);
        stack2.push(5);


        MyStack<Integer> stack1 = new MyStack();
        stack1.push(1);
        stack1.push(2);
        stack1.push(1);
        stack1.push(2);
        stack1.push(1);
        stack1.push(2);


        stack1.pushStack(stack2);

        while (!stack1.isEmpty()) {
            System.out.println(stack1.pop());


        }
    }
}