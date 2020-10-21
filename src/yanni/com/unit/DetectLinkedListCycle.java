package yanni.com.unit;


import org.junit.jupiter.api.Test;

public class DetectLinkedListCycle {

    class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
         }
      }


    public ListNode detectCycle(ListNode head) {

        ListNode crawl = head;
        ListNode crawlFast = head;
        boolean isCycle = false;
        ListNode prev = crawl;
        if(crawl ==null || crawl.next==null || crawl.next.next == null){
            return null;
        }
        ListNode prevPrev = null;
        while (crawlFast!=null && crawlFast.next!=null) {
            crawlFast = crawlFast.next.next;
            crawl = crawl.next;
            if(crawlFast == crawl) {
                ListNode crawlFromHead = head;
                while (crawlFromHead != crawlFast) {
                    crawlFromHead = crawlFromHead.next;
                    crawlFast = crawlFast.next;
                }
                return crawlFast;
            }
            // if(prev==crawl.next || crawl.next==prevPrev) {
            //     //System.out.println(crawl.next.val);
            //     return crawl.next;
            // }
            // prevPrev = prev;
            // prev = crawl;
            // crawl = crawl.next;
        }
        return null;

    }

    @Test
    public void testDetectCycle(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = head.next.next;
        ListNode foundit = detectCycle(head);
        assert(foundit.val==3);
    }

}