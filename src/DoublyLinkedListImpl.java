import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
 
public class DoublyLinkedListImpl<E> {
 
    private Node head;
    private Node tail;
    private int size;
     
    public DoublyLinkedListImpl() {
        size = 0;
    }
    /**
     * this class keeps track of each element information
     * @author java2novice
     *
     */
    private class Node {
        E element;
        Node next;
        Node prev;
 
        public Node(E element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    /**
     * returns the size of the linked list
     * @return
     */
    public int size() { return size; }
     
    /**
     * return whether the list is empty or not
     * @return
     */
    public boolean isEmpty() { return size == 0; }
     
    /**
     * adds element at the starting of the linked list
     * @param element
     */
    public void addFirst(E element) {
                                   //next
        Node tmp = new Node(element, head, null);
        // need to change pointer for prev node since tmp will be first now and head will be 2nd.
        if(head != null ) {
            head.prev = tmp;
        }
        head = tmp;
        if(tail == null) { tail = tmp;}
        size++;
        System.out.println("adding: "+element);
    }


    public void addFirst2(E element) {
        //next
        Node tmp = new Node(element, head, null);
        // need to change pointer for prev node since tmp will be first now and head will be 2nd.
        if(head == null ) {
            head = tmp;
        } else {
            tmp.next = head;
            head = tmp;
        }
        if(tail == null) { tail = tmp;}
        size++;
        System.out.println("adding: "+element);
    }

    public void addLast2(E element) {

        Node tmp = new Node(element, null, tail);
        if(tail==null && head==null) {
            tail = tmp;
            head = tmp;
        }

        if (tail!=null) {
            tail.next = tmp;
            tmp.next = null;
            tail = tmp;
        }
        size++;

    }


     
    /**
     * adds element at the end of the linked list
     * @param element
     */
    public void addLast(E element) {
         
        Node tmp = new Node(element, null, tail);
        // need to change pointer for next node since tmp will be last now and tail will be 2nd to last.
        if(tail != null) {
            Node swap = tail;
            tail = tmp;
            tail.prev = swap;
            tail.next = null;
            swap.next = tail;
//            if(swap.prev==null) {
//                swap.prev=head;
//            }
            if(head.next==null) {
                head.next= tmp;
            }
        } else {
            tail = tmp;
        }
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding: "+element);
    }
     
    /**
     * this method walks forward through the linked list
     */
    public void iterateForward(){
         
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.next;
        }
    }
     
    /**
     * this method walks backward through the linked list
     */
    public void iterateBackward(){
         
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.prev;
        }
    }
     
    /**
     * this method removes element from the start of the linked list
     * @return
     */
    public E removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        head = head.next;
        head.prev = null;
        size--;
        System.out.println("deleted: "+tmp.element);
        return tmp.element;
    }
     
    /**
     * this method removes element from the end of the linked list
     * @return
     */
    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        System.out.println("deleted: "+tmp.element);
        return tmp.element;
    }

    public static void swap(Integer a, Integer b, Map map) {
        System.out.println("first: "+a+":"+b);
        Integer tmp=a;
        a=b;
        b=tmp;
        System.out.println("last "+a+":"+b);
        b=4;
        map.put(1,100000);
    }

    public void reverseLinkedList() {
        Node crawl = head;


        while(crawl!=null) {
            Node tmp = crawl.next;
            crawl.next = crawl.prev;
            crawl.prev = tmp;
            if (tmp==null) {
                head = crawl;
            }
            crawl = tmp;
        }
    }

    @Test
    public void testReverseLinkedList(){
        DoublyLinkedListImpl<Integer> dll = new DoublyLinkedListImpl<Integer>();
        dll.addLast(1);
        dll.addLast(56);
        dll.addLast(364);
        dll.addLast(3643);
        dll.reverseLinkedList();
        assert(dll.head.element == 3643);
    }
     
    public static void main(String a[]){
         
        DoublyLinkedListImpl<Integer> dll = new DoublyLinkedListImpl<Integer>();
        dll.addFirst(10);
        dll.addFirst(34);
        dll.addLast(56);
        dll.addLast(364);
        dll.iterateForward();
        dll.removeFirst();
        dll.removeLast();
        dll.iterateBackward();
        Integer b = 3;
        Integer c = 4;
        HashMap<Integer, Integer> myMap = new HashMap<>();
        myMap.put(1, 5);
        swap( b,c, myMap );
        System.out.println("outside the method: " + b+":"+c);

        DoublyLinkedListImpl<Integer> dll2 = new DoublyLinkedListImpl<Integer>();
        dll2.addLast2(20);
        dll2.addFirst2(10);
        dll2.addFirst2(11);
        dll2.addLast2(1);

        System.out.println(dll2.head.element.intValue());
        System.out.println(dll2.head.next.element.intValue());
        System.out.println(dll2.tail.element.intValue());


    }
}