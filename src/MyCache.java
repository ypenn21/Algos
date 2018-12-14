import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyCache extends TimerTask{

    class MyNode {
        public MyNode(int value){
            this.value = value;
            createdTime = new Date().getTime();
        }

        int value;
        long createdTime;
        MyNode left;
        MyNode right;

    }

     public MyCache(){
         timer.schedule(this, 3000, 3000);
     }

    @Override
    public void run() {
        cleanNodes();
    }

    Map<Integer, MyNode> cached = new ConcurrentHashMap();

     MyNode start;
     MyNode end;
     private static final Timer timer = new Timer(true);
     private static final Integer MAX_NODES = 4;
     private static final Long TIME_TO_LIVE = new Long (9000);

    public void cleanNodes() {
        while(true) {
            long current  = new Date().getTime();
            for (Map.Entry<Integer, MyNode> cachedEntry : cached.entrySet()){
                long lived = current - cachedEntry.getValue().createdTime;
                if ( (lived) > TIME_TO_LIVE) {
                    System.out.println("REMOVED :"+cachedEntry.getValue().value);
                    cached.remove(cachedEntry.getKey());
                }

            }
        }

    }

     // get entry puts the node at the top again since we have accessed it.
    public MyNode getNode(Integer key) {
        MyNode entry = cached.get(key);
        if(entry!=null) {
            entry.createdTime = new Date().getTime();
            removeNode(entry);
            insertAtTop(entry);
            return entry;
        }else {
            return null;
        }
    }

     public void putNode(Integer value) {
        if(cached.containsKey(value)) {
            MyNode node = cached.get(value);
            node.createdTime = new Date().getTime();
//            removeNode( node );
            insertAtTop( node );
        } else {
            MyNode insertNode = new MyNode(value);
            insertAtTop(insertNode);
            if(MAX_NODES< cached.size()){
                removeNode( end );
                cached.remove(end.value);
            }
            cached.put(value, insertNode);
        }

     }

     private void insertAtTop(MyNode node) {
        node.right = start;
        node.left = null;
        if(start!=null){
            start.left = node;
        }
        start = node;
        if(end == null){
            end = node;
        }
         System.out.println("put at top"+node.value);
     }

     private void removeNode(MyNode node) {
        if(node.left!=null)
            node.left.right = node.right;
        else {
//            node.left = node.right; not necessary but can keep this line
            start = node.right;
        }

        if(node.right!=null)
            node.right.left = node.left;
        else {
//            node.right = node.left;  not necessary but can keep this line
            end = node.left;
        }
         System.out.println("remove"+node.value);
     }

    public static void main(String[] args) {
//        Pattern p = Pattern.compile("^([^|]*)\\|([^|]*)\\|([^|]*)$");
//        Matcher roleEntityMatcher = p.matcher("APReview|Firmwide|Firmwidea");
//        System.out.println( roleEntityMatcher.find() ) ;
//        String hi = null;
//        if(hi!=null && hi.equals("")){
//            System.out.println("no exception");
//        }
//        System.out.println("no exception");
//        String myVariable = null+":"+null;

//        List<String> myList = Arrays.asList("Apple", "Ananas", "Mango", "Banana", "Beer");
//
//        List<String> filteredFruits = myList.stream().filter(item-> item.equals("Apple")).collect(Collectors.toList());
//
//        System.out.println(filteredFruits.toString());
        try {
            MyCache myCache = new MyCache();
    //        Thread myCacheThread = new Thread(myCache);
    //        myCacheThread.setDaemon(true);
            myCache.putNode(5);
            MyNode node5 = myCache.getNode(5);
            myCache.putNode(4);
            myCache.putNode(3);
            myCache.putNode(2);
            myCache.removeNode(node5);
//            myCache.removeNode(myCache.getNode(5));
//            myCache.putNode(1);
            Thread.sleep(1000);
            myCache.getNode(5);
            myCache.putNode(1);
//            timer.schedule(myCache, 3000, 3000);

            Thread.sleep(TIME_TO_LIVE * 10);
        } catch (InterruptedException ex) {
        }
        System.out.println("finished");

        //System.out.println( roleEntityMatcher.group(3) ) ;

//        List<Map> preloadTranactions = new ArrayList<>();
//        Map preload = new HashMap();
//        preload.put(213123, "2017-02-07 15:31:44:000");
//        System.out.println("id is: "+preload.get("213123"));
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        preloadTranactions.add(preload);
//        final Date date;
//        try {
//            date = format.parse("2017-06-07 15:31:44:000");
//            Date date2 = format.parse("2017-06-11 15:31:44:000");
//            System.out.println("date: "+date);
//            System.out.println("compare: "+date2.compareTo(date));
//            boolean isObsolete = preloadTranactions.stream().anyMatch(transaction -> { return ( date2.compareTo(date) > 0 ); });
//            System.out.println("isObsolete: "+isObsolete);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        preloadTranactions.add(preload);

//        final Map<String, Date> preloadTranactionIdUpdateMap = preloadTranactions.stream()
//                    .collect(Collectors.toMap(
//                            preloadTransaction -> String.valueOf( preloadTransaction.get("PreLoadTransactionId") ),
//                            preloadTransaction -> {
//                                try {
//                                    return format.parse((String) preloadTransaction.get("UpdateDate"));
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                            },
//                            (preloadTranaction1, preloadTranaction1Dup) -> preloadTranaction1));
//        System.out.println("transformed: "+preloadTranactionIdUpdateMap.toString());

//        Super sub = new Sub();


    }

    abstract class Super {
        Number aNumber;
    }

    class Sub extends Super {
        Float aNumber;
    }


}

