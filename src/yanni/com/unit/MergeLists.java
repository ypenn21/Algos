package yanni.com.unit;/* Java program to merge two
   sorted linked lists */
import org.junit.jupiter.api.Test;

import java.util.*;

      
class MergeLists  
{

    /* Link list node */
    static class Node
    {
        int data;
        Node next;
        Node(int d) {data = d;
            next = null;}
    }
    Node head;
  
/* Method to insert a node at  
   the end of the linked list */
    public void addToTheLast(Node node)
{
    if (head == null)
    {
        head = node;
    }
    else
    {
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = node;
    }
}

    /* Method to print linked list */
    void printList()
    {
        Node temp = head;
        while (temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    static Node sortedMerge(Node headA, Node headB)
    {

    /* a dummy first node to
       hang the result on */
        Node dummyNode = new Node(0);

    /* tail points to the
    last result node */
        Node tail = dummyNode;
        while(true)
        {

        /* if either list runs out,
        use the other list points to the rest of the other linked list node or nodes (if there are more than 1 before it reaches null) */
            if(headA == null)
            {
                tail.next = headB;
                break;
            }
            if(headB == null)
            {
                tail.next = headA;
                break;
            }

        /* Compare the data of the two
        lists whichever lists' data is
        smaller, append it into tail and
        advance the head to the next Node
        */
            if(headA.data <= headB.data)
            {
                tail.next = headA;
                headA = headA.next;
            }
            else
            {
                tail.next = headB;
                headB = headB.next;
            }

            /* Advance the tail */
            tail = tail.next;
        }
        return dummyNode.next;
    }


    @Test
    public  void testMergeLinkLists()
    {
        /* Let us create two sorted linked
           lists to test the methods
           Created lists:
               llist1: 5->10->15,
               llist2: 2->3->20
        */

        //List 1
        List<String> namesList = Arrays.asList( "alex", "brian", "charles");

        //List 2
        ArrayList<String> otherList = new ArrayList<>();

        List<Integer> myList = new ArrayList(){
            {
                add(2);
                add(9);
                add(3);
                add(8);
                add(1);
            }
        };
        myList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        MergeLists llist1 = new MergeLists();
        MergeLists llist2 = new MergeLists();

        // Node head1 = new Node(5);
        llist1.addToTheLast(new Node(5));
        llist1.addToTheLast(new Node(10));
        llist1.addToTheLast(new Node(15));

        // Node head2 = new Node(2);
        llist2.addToTheLast(new Node(2));
        llist2.addToTheLast(new Node(3));
        llist2.addToTheLast(new Node(20));


        llist1.head = MergeLists.sortedMerge(llist1.head,
                                            null);
        llist1.printList();

    }
} 
