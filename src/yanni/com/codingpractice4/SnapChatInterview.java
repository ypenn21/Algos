package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Stack;

public class SnapChatInterview {
    class MyStack {

        Stack<Integer> stack = new Stack();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Integer min;

        synchronized public void  add (Integer num) {
            if(min==null) {
                min = num;
            } else if (min > num) {
                min = num;
            }
            stack.add(num);
            queue.add(num);
        }

        synchronized public Integer pop () {
            Integer number = stack.pop();
            queue.remove(number);
            if(number==min) {
                recalculateMin();
            }
            return number;
        }

        private void  recalculateMin(){
            min = queue.peek();
        }

        public Integer getMin(){
            return min;
        }


    }


    @Test
    public void test1() {
        MyStack mystack = new MyStack();
        mystack.add(1);
        mystack.add(2);
        mystack.add(3);
        mystack.add(4);
        assert (mystack.getMin()==1);

    }
    @Test
    public void test2() {
        MyStack mystack = new MyStack();
        mystack.add(1);
        mystack.add(2);
        mystack.add(3);
        mystack.add(4);
        Integer number = mystack.pop();
        assert(number== 4);
        assert (mystack.getMin()==1);

    }

    @Test
    public void test3() {
        MyStack mystack = new MyStack();
        mystack.add(4);
        mystack.add(3);
        mystack.add(2);
        mystack.add(1);
        Integer number = mystack.pop();
        assert(number == 1);
        assert (mystack.getMin()==2);

    }

    @Test
    public void test4() {
        MyStack mystack = new MyStack();
        mystack.add(2);
        mystack.add(3);
        mystack.add(2);
        mystack.add(10);
        Integer number = mystack.pop();
        assert(number == 10);
        number = mystack.pop();
        assert(number == 2);
        assert (mystack.getMin()==2);

    }
}