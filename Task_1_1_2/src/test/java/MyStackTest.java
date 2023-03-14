
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.rafik.task_1_1_2.MyStack;


public class MyStackTest {
    @Test
    public void pushAndPopTest() {
        MyStack<Integer> stack = new MyStack<>();
        Integer[] arr = {1,2,3,4,5};
        for (int elem : arr) {
            stack.push(elem);
        }

        for (int i = stack.count() - 1; i > 0; i--) {
            Assertions.assertEquals(stack.pop(), arr[i]);
        }
    }
    @Test
    public void pushStackTest() {
        MyStack<Integer> stack1 = new MyStack<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        MyStack<Integer> stack2 = new MyStack<>();
        stack2.push(4);
        stack2.push(5);
        stack2.push(6);
        Integer[] arr = {1,2,3,4,5,6};

        MyStack<Integer> stack = new MyStack<>();
        stack.pushStack(stack1);
        stack.pushStack(stack2);

        for (int i = stack.count() - 1; i > 0; i--) {
            Assertions.assertEquals(stack.pop(), arr[i]);
        }
    }

    @Test
    public void popStackTest() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        MyStack<Integer> stackExp = new MyStack<>();
        stackExp.push(4);
        stackExp.push(5);
        stackExp.push(6);

        MyStack<Integer> stackCur = stack.popStack(3);

        while (!stackCur.isEmpty()) {
            Assertions.assertEquals(stackExp.pop(), stackCur.pop());
        }
    }
    @Test
    public void countTest() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        Assertions.assertEquals(stack.count(), 6);
    }

}
