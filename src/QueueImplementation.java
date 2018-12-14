/* Java Program to implement a queue using two stacks */
// Note that Stack class is used for Stack implementation
 
import java.util.Stack;
 
public class QueueImplementation
{
    /* class of queue having two stacks */
    static class Queue
    {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        /* Function to push an item to stack*/
        public void push(Stack<Integer> top_ref, int new_data)
        {
            //Push the data onto the stack
            top_ref.push(new_data);
        }

        /* Function to pop an item from stack*/
        private int pop(Stack<Integer> top_ref)
        {
        /*If stack is empty then error */
            if(top_ref.isEmpty())
            {
                System.out.println("Stack Overflow");
                System.exit(0);
            }
            //pop the data from the stack
            return top_ref.pop();
        }
        //Function to enqueue an item to the queue
        public void enQueue(int x)
        {
            push(this.stack1, x);
        }

        public int getNext()
        {
            copyStack1ToStack2();
            return this.stack2.peek();
        }


        /* Function to dequeue an item from queue */
        public int deQueue()
        {
            copyStack1ToStack2();
            int x = pop(this.stack2);
            return x;
        }

        private void copyStack1ToStack2(){
            int x;
        /* If both stacks are empty then error */
            if(this.stack1.isEmpty() && this.stack2.isEmpty() )
            {
                System.out.println("Q is empty");
                System.exit(0);
            }

        /* Move elements from stack1 to stack 2 only if
        stack2 is empty */
            if(this.stack2.isEmpty())
            {
                while(!this.stack1.isEmpty())
                {
                    x = pop(this.stack1);
                    push(this.stack2, x);

                }
            }

        }


    }

     
    /* Driver function to test anove functions */
    public static void main(String args[]) 
    {
        /* Create a queue with items 1 2 3*/
        Queue q= new Queue();
        q.enQueue( 1);
        q.enQueue( 2);
        q.enQueue( 3);
        System.out.print( q.getNext()+" ");
        /* Dequeue items */
        System.out.print( q.deQueue()+" ");
        System.out.print( q.deQueue()+" ");
        System.out.println( q.deQueue()+" ");
    }
}