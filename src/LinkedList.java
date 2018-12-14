
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

	private Node head;

	public Node getHead() {
		return this.head;
	}

	public void addAtStart(Object data) {
		Node newNode = new Node(data);
		newNode.setNextNode(this.head);
		this.head = newNode;
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