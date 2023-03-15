package ru.nsu.fit.rafik.task_1_1_2;

public class MyStack<A> {

    // number of the element in the stack
    private int count = 0;


    // stack elements themselves
    private Object[] values;

    // size
    private int size = 10000;

    public MyStack() {
        this.values = new Object[size];
    }

    // the stack is empty or not ?
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else
            return false;
    }


    // if there is any free space on the stack and adds a new element
    public void push(A element) {
        if (count < size) {
            values[count] = element;
            count++;
        } else {
            this.increaseStackSize();
            throw new ArrayIndexOutOfBoundsException("stack overflow");
        }
    }

    private void increaseStackSize() {
        int newSize = this.size * 2;
        Object[] newValues = (A[]) new Object[newSize];

        for (int i = 0; i < this.size; i++) {
            newValues[i] = this.values[i];
        }

            this.values = newValues;
            this.size = newSize;
        }
    public void pushStack(MyStack anotherStack) {

        int anotherStackCount = anotherStack.count;
        A[] arr = (A[]) new Object[anotherStackCount];

        for (int i = (anotherStackCount - 1); i >= 0; i--) {
            arr[i] = (A) anotherStack.pop();
        }

        for (var element: arr) {
            this.push(element);
        }
    }
    public A pop() {
        if (count > 0) {
            A value = (A) values[count-1];
            count--;
            return value;
        }
        else {
            throw new ArrayIndexOutOfBoundsException("stack is empty");
        }
    }
    public MyStack popStack (int sizeOfNewStack) {
        MyStack newStack = new MyStack();
        A[] arr = (A[]) new Object[sizeOfNewStack];

        for (int i = (sizeOfNewStack - 1); i >= 0; i--) {
            arr[i] = (A) this.pop();
        }

        for (var element: arr) {
            newStack.push(element);
        }

        return newStack;
    }



     //the number of elements on the stack at the moment.

    public int count() {
        return this.count;
    }



}
