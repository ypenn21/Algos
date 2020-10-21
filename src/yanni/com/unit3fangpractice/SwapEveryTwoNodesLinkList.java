package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

public class SwapEveryTwoNodesLinkList {


    class SwapNodeLink{
        SwapNodeLink next;
        Integer value;

        public SwapNodeLink(int val) {
            this.value = val;

        }
    }

    public SwapNodeLink swapEveryTwoNodesLinkList(SwapNodeLink swapNodeLink) {
        if (swapNodeLink==null || swapNodeLink.next==null)
            return swapNodeLink;
        SwapNodeLink prev=null;
        SwapNodeLink prevPrev=null;
        SwapNodeLink next=null;
        SwapNodeLink current=swapNodeLink;

        int index=1;
        while(current!=null){
            next = current.next;

            if(index%2==0 && index!=0){
                SwapNodeLink tmp = current;
                prev.next=next;
                tmp.next= prev;
                if(prevPrev!=null)
                    prevPrev.next=tmp;
                if(index==2)
                    swapNodeLink = tmp;
                prev = current.next;
                current=next;
            } else {


                if (prev != null) {
                    prevPrev = prev;
                }
                prev = current;
                current = next;
            }
            index++;

        }

        return swapNodeLink;
    }

    @Test
    public void testSwapEveryTwoNodesLinkList3() {
        SwapNodeLink swapNodeLink = new SwapNodeLink(1);
        swapNodeLink.next = new SwapNodeLink(3);
        SwapNodeLink result = swapEveryTwoNodesLinkList(swapNodeLink);
        SwapNodeLink crawler = result;
        while(crawler!=null){
            System.out.println(crawler.value);
            crawler = crawler.next;
        }

    }


    @Test
    public void testSwapEveryTwoNodesLinkList2() {
        SwapNodeLink swapNodeLink = new SwapNodeLink(1);
        swapNodeLink.next = new SwapNodeLink(2);
        swapNodeLink.next.next = new SwapNodeLink(3);
        swapNodeLink.next.next.next = new SwapNodeLink(4);
        swapNodeLink.next.next.next.next = new SwapNodeLink(5);
        SwapNodeLink result = swapEveryTwoNodesLinkList(swapNodeLink);
        SwapNodeLink crawler = result;
        while(crawler!=null){
            System.out.println(crawler.value);
            crawler = crawler.next;
        }

    }





    @Test
    public void testSwapEveryTwoNodesLinkList() {
        SwapNodeLink swapNodeLink = new SwapNodeLink(1);
        swapNodeLink.next = new SwapNodeLink(2);
        swapNodeLink.next.next = new SwapNodeLink(3);
        swapNodeLink.next.next.next = new SwapNodeLink(4);
        swapNodeLink.next.next.next.next = new SwapNodeLink(5);
        swapNodeLink.next.next.next.next.next = new SwapNodeLink(6);
        SwapNodeLink result = swapEveryTwoNodesLinkList(swapNodeLink);
        SwapNodeLink crawler = result;
        while(crawler!=null){
            System.out.println(crawler.value);
            crawler = crawler.next;
        }

    }



}
