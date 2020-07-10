package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class AddTwoNumsWithLinkedList
{
    class AddNumNode {
        AddNumNode next;
        int val;

        public AddNumNode (int val){
            this.val = val;
        }
    }

    class AddNumLinkedList {
        AddNumNode root;
        Integer number;
        public AddNumLinkedList (int val) {
            root = new AddNumNode(val);
        }

        public void addNode(int val){
            AddNumNode node = new AddNumNode(val);
            AddNumNode crawl = root;
            if(crawl==null) {
                root = node;
                return;
            }
            while(crawl.next!=null) {
                crawl=crawl.next;
            }
            crawl.next = node;

        }

        public Integer getNumber(){
            AddNumNode crawl = root;
            String number ="";
            while(crawl!=null) {
                number = crawl.val+number;
                crawl=crawl.next;
            }
            return Integer.parseInt(number);
        }
    }

    public AddNumLinkedList create(Integer num){
        String numStr = num.toString();
        AddNumLinkedList linkedList1 = null;
        for (int i=numStr.length()-1;i>=0;i--) {
            if(linkedList1==null)
                linkedList1 = new AddNumLinkedList(Integer.parseInt(numStr.charAt(i)+""));
            else {
                linkedList1.addNode(Integer.parseInt(numStr.charAt(i)+""));
            }
        }

        return linkedList1;
    }

    // number returned is backwards
    public AddNumLinkedList addTwoNums(Integer num1, Integer num2){
        AddNumLinkedList linkedList1 = create(num1);
        AddNumLinkedList linkedList2 = create(num2);

        AddNumNode result = addTwoNums(linkedList1.root, linkedList2.root,0);
        AddNumLinkedList resultLink  = new AddNumLinkedList(result.val);
        resultLink.root = result;
        return resultLink;
    }

    public AddNumNode addTwoNums(AddNumNode node1, AddNumNode node2, int plus){
        AddNumNode result;
        if(node1 == null && node2==null && plus==0){
            return null;
        }

        if(node1 != null || node2!=null){
            Integer added;
            AddNumNode next1;
            AddNumNode next2;
            if(node1==null){
                added = node2.val;
                next1 =null;
                next2 = node2.next;
            } else if (node2==null) {
                added = node1.val;
                next2=null;
                next1 = node1.next;
            } else {
                added = node1.val + node2.val;
                next1 = node1.next;
                next2 = node2.next;
            }
            added = added+plus;
            plus = added / 10;
            int reminder = added % 10;
            result = new AddNumNode(reminder);
            result.next = addTwoNums(next1, next2, plus);
        } else {
            result = new AddNumNode(plus);
        }

        return result;
    }

    @Test
    public void testAddTwoNumsWithLinkedList() {
        AddNumLinkedList linkedList = addTwoNums(425, 575);
        AddNumNode crawl = linkedList.root;
        System.out.println(linkedList.getNumber());
        while(crawl!=null) {
            System.out.println(crawl.val);
            crawl = crawl.next;
        }
    }

    @Test
    public void testAddTwoNumsWithLinkedList2() {
        AddNumLinkedList linkedList = addTwoNums(42, 575);
        AddNumNode crawl = linkedList.root;
        System.out.println(linkedList.getNumber());
        while(crawl!=null) {
            System.out.println(crawl.val);
            crawl = crawl.next;
        }
    }

}
