package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class LinkedList {

    public Node head;
    public Node tail;
    public Integer size=0;

    public class Node {

        public Node (Integer value) {
            this.value  = value;
        }

        public Integer value;
        public Node next;
        public Node prev;
    }

    public void insert (Node node) {
        if(node == null) {
            return;
        }
        if(head==null) {
            head = node;
            head.prev = null;
        } else {
            Node crawl = head;
            while (crawl.next!=null) {
                crawl = crawl.next;
            }
            crawl.next = node;
            node.prev = crawl;
            tail = node;
        }
        size++;
    }

    public void remove (Node node) {
        if(head.value == node.value){
            if ( head.next == null ) {
                head = null ;
            }
            head.next.prev = null;
            head = head.next;
            size--;
            return;
        }
        Node crawl = head;
        while (crawl.next!=null) {
            if (crawl.value == node.value) {
                break;
            }
            crawl = crawl.next;
        }

        if (crawl==null) {
            return;
        } else {
            if ( crawl.next == null ) {
                crawl.prev.next = null;
                if(crawl.prev!=head)
                    tail = crawl.prev;
                else
                    tail =null;
            } else {
                crawl.prev.next = crawl.next;
                crawl.next.prev = crawl.prev;
            }
            size--;
        }
    }

    public void reverseInplaceLinkedList () {
        Node crawl = tail.prev;
//        int indexStart =0;
//        int indexEnd = size;
//        Node intermediateNodeHead = head;
        head = tail;
        head.prev = null;
//        tail = intermediateNodeHead;
        while (crawl!=null) {
            crawl.next=null;
            insert (new Node (crawl.value));
            crawl = crawl.prev;
        }
//        tail = crawl;
    }

    public void print () {
        Node crawl = head;
        while (crawl!=null) {
            System.out.println(crawl.value);
            crawl = crawl.next;
        }
    }

    /* Function to reverse the single linked list */
    public void reverseDoubleLinkedList()
    {
        Node prev = null;
        Node current = this.head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            current.prev = next;
            prev = current;
            current = next;
        }
        this.head = prev;
        this.print();
    }

    /* Function to reverse the single linked list */
    Node reverse(Node node)
    {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    @Test
    public  void testDoubleLinkListsReverse2()
    {
        LinkedList linkedList = new LinkedList();
        linkedList.insert( new Node(3) );
        linkedList.insert( new Node(4) );
        linkedList.insert( new Node(5) );
        linkedList.insert( new Node(6) );
        linkedList.reverseDoubleLinkedList();
    }

    @Test
    public  void testDoubleLinkListsReverse()
    {
        LinkedList linkedList = new LinkedList();
        linkedList.insert( new Node(7) );
        linkedList.insert( new Node(8) );
        linkedList.insert( new Node(9) );
        linkedList.insert( new Node(10) );
        linkedList.reverseInplaceLinkedList();
        linkedList.print();
    }


    @Test
    public  void testDoubleLinkLists()
    {
        LinkedList linkedList = new LinkedList();
        linkedList.insert( new Node(1) );
        linkedList.insert( new Node(2) );
        linkedList.insert( new Node(3) );
        linkedList.insert( new Node(4) );
        linkedList.remove( new Node(1) );
        linkedList.remove( new Node(4) );
        linkedList.remove( new Node(2) );
        linkedList.insert( new Node(5) );
        linkedList.insert( new Node(6) );
        linkedList.remove( new Node(1) );
        linkedList.print();
        linkedList.remove( new Node(5) );
        linkedList.print();
    }



}
