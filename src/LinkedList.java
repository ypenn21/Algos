import org.junit.jupiter.api.Test;

public class LinkedList {

	public static void main(String[] args) {

		// Default constructor - let's put "0" into head element.
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.addAtStart("1");
		crunchifyList.addAtStart("2");
		crunchifyList.addAtStart("3");
		crunchifyList.addAtStart("4");
		crunchifyList.addAtStart("5");

		/*
		 * Please note that primitive values can not be added into LinkedList directly. They must be converted to their
		 * corresponding wrapper class.
		 */


		System.out.println("Print: crunchifyList: \t\t" + crunchifyList);
		System.out.println(".size(): \t\t\t\t" + crunchifyList.length());
		System.out.println(".get(3): \t\t\t\t" + crunchifyList.find("3") + " (get element at index:3 - list starts from 0)");
		System.out.println("Print again: crunchifyList: \t" + crunchifyList);
	}


	@Test
	public void testReverseLinkedList(){
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.addAtStart("1");
		crunchifyList.addAtStart("2");
		crunchifyList.addAtStart("3");
		crunchifyList.addAtStart("4");
		crunchifyList.addAtStart("5");
		crunchifyList.reverseLinkedList();
		System.out.println(crunchifyList.head.data);
		System.out.println(crunchifyList.head.next.data);
		System.out.println(crunchifyList.head.next.next.data);
		System.out.println(crunchifyList.head.next.next.next.data);
		System.out.println(crunchifyList.head.next.next.next.next.data);
		System.out.println(crunchifyList.head.next.next.next.next.next);
	}

	@Test
	public void testRemoveNthFromEnd(){
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.addAtStart("1");
		crunchifyList.addAtStart("2");
		crunchifyList.addAtStart("3");
		crunchifyList.addAtStart("4");
		crunchifyList.addAtStart("5");
		Node head = crunchifyList.removeNthFromEnd(crunchifyList.head, 2);
		System.out.println(head.data);
		System.out.println(head.next.data);
		System.out.println(head.next.next.data);
		System.out.println(head.next.next.next.data);
//		System.out.println(head.next.next.next.next.data);
	}

	@Test
	public void testRemoveNthFromEnd2(){
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.addAtStart("2");
		crunchifyList.addAtStart("1");
		Node head = crunchifyList.removeNthFromEnd(crunchifyList.head, 2);
		System.out.println(head.data);
		assert(head.next==null);
//		System.out.println(head.next.data);
//		System.out.println(head.next.next.next.next.data);
	}


	@Test
	public void testRemoveNthFromEnd3(){
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.add("1");
		crunchifyList.add("2");
		crunchifyList.add("3");
		crunchifyList.add("4");
		crunchifyList.add("5");
		String num = "";
		Node head = crunchifyList.swap(crunchifyList.head);
		System.out.println(head.data);
		System.out.println(head.next.data);
		System.out.println(head.next.next.data);
		System.out.println(head.next.next.next.data);
		System.out.println(head.next.next.next.next.data);
	}

	@Test
	public void addTwoNumbers(){
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.add(9);


		LinkedList crunchifyList2 = new LinkedList();
		crunchifyList2.add(1);
		crunchifyList2.add(9);
		crunchifyList2.add(9);
		crunchifyList2.add(9);
		crunchifyList2.add(9);
		crunchifyList2.add(9);crunchifyList2.add(9);crunchifyList2.add(9);

		Node head = crunchifyList.addTwoNumbers(crunchifyList.head, crunchifyList2.head);
		System.out.println(head.data);
		System.out.println(head.next.data);
		System.out.println(head.next.next.data);
		System.out.println(head.next.next.next.data);
		System.out.println(head.next.next.next.next.data);
		System.out.println(head.next.next.next.next.next.data);
		System.out.println(head.next.next.next.next.next.next.data);
		System.out.println(head.next.next.next.next.next.next.next.data);
		System.out.println(head.next.next.next.next.next.next.next.next.data);
//		System.out.println(head.next.next.next.next.data);
	}

	public boolean isL2GreaterL1(Node l1, Node l2) {
		Node crawl = l1;
		Node crawl2 = l2;
		boolean greater=false;
		while(crawl!=null && crawl2!=null) {
			crawl = crawl.next;
			crawl2 = crawl2.next;
			if(crawl ==null && crawl2 !=null) {
				return true;
			}
		}
		return greater;
	}

	public Node addTwoNumbers(Node l1, Node l2) {
		Node crawl = l1;
		Node crawl2 = l2;
		boolean isL2Greater = isL2GreaterL1(l1, l2);
		if ( isL2Greater ) {
			crawl = l2;
			crawl2 = l1;
		}
		boolean add1=false;
		while( (crawl!=null && crawl2!=null) || add1) {
			Integer num = 0;
			if (crawl!=null && crawl2!=null)
			 num = (Integer) crawl.data+ (Integer) crawl2.data;
			else if (crawl!=null) {
				num = (Integer) crawl.data;
			}
			if(add1) {
				num++;
			}
			if(num>=10) {
				add1 = true;
			} else {
				add1 = false;
			}
			if (crawl != null)
				crawl.data = Integer.parseInt( num.toString().substring(num.toString().length()-1) );
			if (crawl.next == null && add1) {
				crawl.next = new Node(1);
				break;
			}
			crawl= crawl.next;
			if(crawl2!=null)
				crawl2 = crawl2.next;
		}

		return isL2Greater ? l2 : l1;
	}

	public Node swap(Node head) {
		Node crawl = head;
		Node prev = null;
		Node setHead = null;
		int n=1;
		Node prevprev = null;
		if(head==null || head.next==null) {
			return head;
		}
		while(crawl != null ) {
			if(n%2==0) {
				Node tmp = crawl.next;        // prev -> crawl -> crawl.next
				prev.next = tmp;
				crawl.next = prev;
				if(n>2) {
					prevprev.next = crawl;
				}
				                         // crawl -> prev -> crawl.next -> crawl.next.next
				//
										// crawl -> prev -> crawl.next.next -> crawl.next -> null


				if(setHead==null) {
					setHead = crawl;
//					prev.next = tmp;
				}
//				crawl = tmp;
				prevprev = prev;
				crawl = crawl.next;
			}
			n++;

				prev = crawl;
				crawl = crawl.next;

		}
		return setHead;
	}


//	public ListNode reverseKGroup(ListNode head, int k) {
//		ListNode crawl = head;
//		ListNode swapNode = head;
//
//		while(crawl!=null) {
//			if(crawl.val % k == 0 && swapNode!=crawl ) {
//				swapPlaces(swapNode,  crawl, head);
//				swapNode= crawl.next;
//			}
//			crawl = crawl.next;
//		}
//
//		return head;
//
//	}

	public Node swapPlaces(Node swapNode, Node swapNode2, Node head) {
		Node crawl = head ;

		Node prev = null;

		Node result = head;

		Node tmp1 = swapNode.next == swapNode2? swapNode : swapNode.next;
		Node prevTmp1 = null;
		Node tmp2 = swapNode2.next;
		Node prevTmp2 = null;


		while (crawl!=null) {
			if(swapNode==crawl && prev !=null) {
				prevTmp1 = prev;
			}

			if(swapNode2==crawl && prev !=null) {
				prevTmp2 = prev;
			}

//			if( prev==null || swapNode2==head || swapNode == head ) {
//				result = swapNode2==head ? swapNode : swapNode2;
//			}

			prev = crawl;
			crawl = crawl.next;
		}

		if (prevTmp1 ==null) {
			result = swapNode2;
			this.head = swapNode2;
		} else {
			prevTmp1.next = swapNode2;
		}

		if (prevTmp2 ==null) {
			result = swapNode;
			this.head = swapNode;
		} else {
			prevTmp2.next = swapNode;
		}

		swapNode2.next = tmp1;
		swapNode.next = tmp2;

		return result;
	}

	@Test
	public void testSwapLinkedList(){
		LinkedList crunchifyList = new LinkedList();

		// add more elements to LinkedList
		crunchifyList.addAtStart("1");
		crunchifyList.addAtStart("2");
		crunchifyList.addAtStart("3");
		crunchifyList.addAtStart("4");
		crunchifyList.addAtStart("5");
		"".toCharArray();
		crunchifyList.swapPlaces(crunchifyList.head, crunchifyList.head.next, crunchifyList.head);
		System.out.println(crunchifyList.head.data);
		System.out.println(crunchifyList.head.next.data);
		System.out.println(crunchifyList.head.next.next.data);
		System.out.println(crunchifyList.head.next.next.next.data);
		System.out.println(crunchifyList.head.next.next.next.next.data);
//		System.out.println(crunchifyList.head.next.next.next.next.next);
	}

	public Node removeNthFromEnd(Node head, int n) {
		Node crawl = head;
		int count = 0;

		while(crawl !=null ) {
			count++;
			if(crawl.next==null) {
				break;
			}
			crawl = crawl.next;
		}

		int nth = count-n;

		int count2=0;
		Node prev=null;
		crawl = head;
		while(crawl !=null ) {
			if(n==1 && count==1) {
				head = crawl.next;
			}
			if(count2==nth) {
				Node tmp = crawl.next;
//				crawl.next = null;
				if(prev!=null)
					prev.next=tmp;
				else {
					head = crawl.next;
				}
			}
			count2++;
			prev = crawl;
			crawl = crawl.next;
		}
		return head;
	}

	public void reverseLinkedList() {
		Node crawl = head;

		Node prev = null;
		Node next = null;

		while(crawl!=null) {
			next = crawl.next;
			crawl.next = prev;

			prev = crawl;
			crawl = next;
		}
		head = prev;
	}




	private Node head;

	public Node getHead() {
		return this.head;
	}

	public void addAtStart(Object data) {
		Node newNode = new Node(data);
		newNode.setNextNode(this.head);
		this.head = newNode;
	}

	public void add(Object data) {
		Node newNode = new Node(data);
		if(head==null) {
			head = newNode;
			return;
		}
		Node crawl = head;
		while(crawl!=null) {
			if(crawl.next==null) {
				crawl.next= newNode;
				break;
			}
			crawl = crawl.next;
		}
	}

	public Node deleteAtStart() {
		Node toDel = this.head;
		this.head = this.head.getNextNode();
		return toDel;
	}

	public Node find(Object data) {
		Node curr = this.head;
		while (curr != null) {
			Object myData = curr.getData();
			if (myData.equals(data)) {
				return curr;
			}
			curr = curr.getNextNode();
		}
		return null;
	}

	public int length() {
		if (head == null)
			return 0;
		int length = 0;
		Node curr = this.head;
		while (curr != null) {
			length += 1;
			curr = curr.getNextNode();
		}
		return length;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public class Node {
		// reference to the next node in the chain, or null if there isn't one.
		Node next;

		// data carried by this node. could be of any type you need.
		Object data;

		// Node constructor
		public Node(Object dataValue) {
			next = null;
			data = dataValue;
		}

		// another Node constructor if we want to specify the node to point to.
		@SuppressWarnings("unused")
		public Node(Object dataValue, Node nextValue) {
			next = nextValue;
			data = dataValue;
		}

		// these methods should be self-explanatory
		public Object getData() {
			return data;
		}

		@SuppressWarnings("unused")
		public void setData(Object dataValue) {
			data = dataValue;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node nextValue) {
			next = nextValue;
		}

	}
}