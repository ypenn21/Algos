/* package whatever; // don't place package name! */

import java.util.HashMap;
class Entry {
	int value;
	int key;
	Entry left;
	Entry right;
}
public class LRUCache {

	HashMap<Integer, Entry> hashmap;
	Entry start, end;
	int LRU_SIZE = 4; // Here i am setting 4 to test the LRU cache
						// implementation, it can be dynamic
	public LRUCache() {
		hashmap = new HashMap<Integer, Entry>();
	}

	public int getEntry(int key) {
		if(hashmap.containsKey(key)) {
			Entry entry = hashmap.get(key);
			removeNode(entry);
			addAtTop(entry);
			return entry.value;
		}
		return -1;
	}

	public void putEntry(int key, int value) {
		Entry newnode = new Entry();
		newnode.left = null;
		newnode.right = null;
		newnode.value = value;
		newnode.key = key;
		if (hashmap.containsKey(key)){
			removeNode(hashmap.get(key));
			addAtTop(newnode);
		} else {
			addAtTop(newnode);
			hashmap.put(key, newnode);
		}

		if (hashmap.size() > LRU_SIZE) {
			removeNode(end);
		}
	}

	public void addAtTop(Entry node) {
		if(start==null) {
			start = node;
		} else {
			start.left = node;
			if(start.right==null) {
				end = start;
			}
			node.left = null;
			node.right = start;
			start = node;
		}
	}

	public void removeNode(Entry removeNode) {
		if (removeNode.left==null) {
			start = removeNode.right;
			removeNode.right=null;
		}

		Entry leftNode = removeNode.left;

		if(removeNode.right==null){
			end = leftNode;
			//removeNode.left=null;
		} else {
			leftNode.right = removeNode.right;
			removeNode.right.left = leftNode;
		}
		//removeNode=null;
	}



	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		LRUCache lrucache = new LRUCache();
		lrucache.putEntry(1, 1);
		lrucache.putEntry(10, 15);
		lrucache.putEntry(15, 10);
		lrucache.putEntry(10, 16);
		lrucache.putEntry(12, 15);
		lrucache.putEntry(18, 10);
		lrucache.putEntry(13, 16);

		System.out.println(lrucache.getEntry(1));
		System.out.println(lrucache.getEntry(10));
		System.out.println(lrucache.getEntry(15));

	}
}