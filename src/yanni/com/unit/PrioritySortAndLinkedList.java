package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrioritySortAndLinkedList {

    public class LinkedListNode {
        public NodeNestedChild head;

        public void add(NodeNestedChild node) {
            if(head==null) {
                head = node;
                return;
            }
            NodeNestedChild currentNode = head;
            while(currentNode!=null) {
                if( currentNode.next==null){
                    currentNode.next= node;
                    return;
                }
                currentNode = currentNode.next;

            }
        }

        public NodeNestedChild searchNodesAndChildren(int key) {
            if(this.head ==null) {
                return null;
            }
            return searchNodesAndChildren(this.head, key );
        }

        private NodeNestedChild searchNodesAndChildren(NodeNestedChild find, int key) {
            NodeNestedChild currentNode = find;
            if(currentNode==null){
                System.out.println("this is null braaaaa");
            }
            while(currentNode!=null) {
                if(currentNode.key ==key){
                    System.out.println("found");
                    return currentNode;
                }
                System.out.println("the node we are on is:"+currentNode.key);
                NodeNestedChild foundNode=searchNodesAndChildren(find.child, key);
                if(foundNode!=null) {
                    return foundNode;
                }
                foundNode=searchNodesAndChildren(find.next, key);
                if(foundNode!=null) {
                    return foundNode;
                }
                currentNode = currentNode.next;

            }
            return null;
        }

    }

    public class NodeNestedChild {
        int key = 99;
       public NodeNestedChild child;
       public NodeNestedChild next;

        public NodeNestedChild(int key) {
            this.key = key;
            if(key == 0){
                return;
            }
            child = new NodeNestedChild(key-1);
        }

//        public NodeNestedChild findNode(){
//
//        }
    }
    public int getPriority(Integer code){
        if(code<= 10 && code >0) {
            return 1;
        }
        if(code<= 20 && code >10) {
            return 2;
        }
        if(code<= 30 && code >20) {
            return 3;
        }

        return 4;
    }

    @Test
    public void testNestedChild(){
        NodeNestedChild node = new NodeNestedChild(3);
        NodeNestedChild node2 = new NodeNestedChild(5);
        NodeNestedChild node3 = new NodeNestedChild(10);
        LinkedListNode linkedListNode = new LinkedListNode();
        linkedListNode.add(node);
        linkedListNode.add(node2);
        linkedListNode.add(node3);
        NodeNestedChild found = linkedListNode.searchNodesAndChildren(7);
        System.out.println(found==null? "not found": "found "+found.key);
//        while(node.child!=null){
//            System.out.println(node.key);
//            node=node.child;
//        }
    }

    @Test
    public void testPrioritySort(){
        List<Integer> myList = new ArrayList<>();
        IntStream.range(35, 44).forEach(i -> {
            myList.add(i);
        });
        IntStream.range(20, 25).forEach(i -> {
            myList.add(i);
        });
        IntStream.range(25, 34).forEach(i -> {
            myList.add(i);
        });
        List<Integer> myList2 = new ArrayList<>();
        myList2.addAll(myList);
        // check function getPriority
        myList2.sort((a, b)->{return getPriority(a)-getPriority(b);});
        System.out.println(myList2.toString());
        PriorityQueue< Integer > queue = new PriorityQueue< >((a, b) -> {
            return getPriority(a)-getPriority(b);
        });
        for (Integer integer : myList) {
            queue.add(integer);
        }
        int i=0;
        while(!queue.isEmpty()){
            myList.set(i, queue.poll());
            i++;
        }
        System.out.println(myList.toString());
        myList.sort((a, b)-> {return a-b;});
        System.out.println(myList.toString());
    }

    @Test
    public void test () {

        Map<String, List<String>> people = new HashMap<>();
        people.put("John", Arrays.asList("555-1123", "555-3389"));
        people.put("Mary", Arrays.asList("555-2243", "555-5264"));
        people.put("Steve", Arrays.asList("555-6654", "555-3242"));

        List<String> phones = people.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<List<String>> items = people.values().stream()
                .map(item -> item)
                .collect(Collectors.toList());
    }

    @Test
    public void test2dArrayRotation() {
        // NOTE: The following input values will be used for testing your solution.
        int a1[][] = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[][] result1 = new int [0] [0];
        int[][] result2 = new int [0] [0];
        // 0,0  1,0  0,1  1,1
        result1 = rotate(a1, 3);
        // should return:
        // [[7, 4, 1],
        //  [8, 5, 2],
        //  [9, 6, 3]]

        int a2[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        //result2 = rotate(a2, 4);
        System.out.println(result1+" "+result2);
        // should return:
        // [[13, 9, 5, 1],
        //  [14, 10, 6, 2],
        //  [15, 11, 7, 3],
        //  [16, 12, 8, 4]]
    }

    // Implement your solution below.
    public int[][] rotate(int[][] a, int n) {
        // n/2 gives us floor(n/2)
        // and n/2 + n%2 gives us ceiling(n/2)
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 4 corners rotate the corners
                int[] tmp = new int[4];
                int currentI = i;
                int currentJ = j;
                for (int k = 0; k < 4; k++){
                    tmp[k] = a[currentI][currentJ];
                    //I  = row &  J = col
//                   rotateSub(int i, int j, int n) {
//                      int[] newCoordinates = new int[2];
//                      newCoordinates[0] = j;
//                      newCoordinates[1] = n - 1 - i;
//                      return newCoordinates;
//                   }
                    // this for loop is building the tmp array which has 4 positions you want to rotate
                    int[] newCoordinates = rotateSub(currentI, currentJ, n);
                    currentI = newCoordinates[0];
                    currentJ = newCoordinates[1];
                }

                for (int k = 0; k < 4; k++) {
                    //System.out.println("(k + 3) % 4 ="+((k + 3) % 4));
                    // 3, 0, 1, 2
                    a[currentI][currentJ] = tmp[(k + 3) % 4];
                    System.out.println("currentI:"+currentI+" currentJ:"+currentJ);
                    int[] newCoordinates = rotateSub(currentI, currentJ, n);
                    currentI = newCoordinates[0];
                    currentJ = newCoordinates[1];
                }
            }
        }
        return a;
    }

    // Implement your solution below.
    public int[][] rotate2(int[][] a, int n) {
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = a[i][j];
            }
        }
        return rotated;
    }

    public int[] rotateSub(int i, int j, int n) {
        int[] newCoordinates = new int[2];
        newCoordinates[0] = j;
        newCoordinates[1] = n - 1 - i;
        return newCoordinates;
    }
}
