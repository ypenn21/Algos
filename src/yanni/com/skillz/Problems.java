package yanni.com.skillz;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problems {
    public interface MyObject {
        String getFoo();
        void setFoo(String o);
    }
    /**
     * Assuming a threaded environment, and without knowing anything else,
     * what is the potential problem with myMethod()?
     * Fix it in the simplest way.
     */

    public class ClumsyTest {
        private static final String FUBAR = "fubar";

        public boolean myMethod( final MyObject bar) {
            if (bar.getFoo() != null) {
                return bar.getFoo().equals(FUBAR);
            } else {
                return false;
            }
        }

    }


    /**
     * Describe the conditions under which the use of this method can result in
     * reaching an operating system limit.  Fix the method as elegantly as
     * possible.
     */

    public class FileInputStreamTest {
        public int readFirstByte(final File f) throws IOException {
            FileInputStream inStream = new FileInputStream(f);
            int bytes = inStream.read();
            inStream.close();
            return bytes;
        }
    }


    /**
     * Fix the problem in the following code by changing the Point class only,
     * without touching the code in main(). Make only the necessary changes.
     */

    public static final class Point {
        private double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

//        @Override
//        public int hashCode() {
//            return (int) (x*y);
//        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Point p =(Point) o;
            return x == p.x && y == p.y ;
        }
    }


    @Test
    public void testPointEquals() {
            List<Point> pointList = new ArrayList<>();
            pointList.add(new Point(1, 2));
            pointList.add(new Point(3, 4));
            System.out.println(pointList.size());
            // remove the second Point
            pointList.remove(new Point(3, 4));
            System.out.println(pointList.size());
            // Not removed!
    }




    /**
     * Fix the following code so the the map.get() call
     * retrieves the expected value.
     * Do not change the main method.
     */
    public final class Student {
            public Student( String name ) {
                this.name = name;
            }
            private String name;

            @Override
            public int hashCode() {
                return name.hashCode();
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null) return false;
                if (this.getClass() != o.getClass()) return false;
                Student s =(Student) o;
                return s.name.equals(name) ;
            }
    }

    @Test
    public void testStudentHash() {
        Map<Student, String> map = new HashMap<>();
        map.put( new Student( "john"), "present" );
        System.out.println( map.get( new Student( "john" ) ) );
    }


    /**
     * Note: This can be time-consuming, you may want to save it for the end.
     * Implement a subset of Java ArrayList class using the following template.
     * It should be thread-safe, and not use any classes from the java.util package.
     */

    public class MyArrayList<T> {

        public class Node {
            T val;
            Node next;
            Integer index;
        }

        Node root;

        private Integer size = 0;

        /**
         * The size of the list
         * @return the size
         */
        public int size() {
          return size;
        }

        /**
         * Gets the object at the index
         * @param idx The index
         * @return The object
         * @throws ArrayIndexOutOfBoundsException if OOB
         */
        public synchronized T get( int idx ) {
            Node crawl = root;
            while(crawl!=null) {
                if(crawl.index == idx) {
                    break;
                }
                crawl = crawl.next;
            }
            return crawl==null ? null : crawl.val ;
        }

        /**
         * Add the object to the end of the list.
         * @param o The object, may be null.
         */
        public synchronized void add( T o ) {
            if(root==null) {
                root = new Node();
                root.val = o ;
                root.index = 1;
            } else {
                Node crawl = root;
                while(crawl.next!=null) {
                    crawl = crawl.next;
                }
                crawl.next = new Node();
                crawl.next.val = o;
                crawl.next.index = crawl.index+1;
            }
            size++;
        }

        /**
         * Removes the object at the index
         * @param idx The index.
         */
        public synchronized void remove( int idx ) {
            if(root==null) {
                size=0;
                return;
            } else {
                Node crawl = root;
                Node prev = null;
                while(crawl!=null) {
                    if(crawl.index == idx) {
                        break;
                    }
                    prev = crawl;
                    crawl = crawl.next;
                }
                if (crawl.next !=null) {
                    if(prev!=null)
                        prev.next = crawl.next;
                    else {
                        root = crawl.next;
                    }
                } else {
                    prev.next = null;
                }
            }
            reIndex();
            if(size!=0)
                size--;
        }

        public void reIndex () {
            synchronized(this.root) {
                if (root == null) {
                    return;
                }
                Node crawl = root;
                int index = 1;
                while (crawl != null) {
                    crawl.index = index;
                    index++;
                    crawl = crawl.next;
                }
            }
        }
    }


    @Test
    public void testMyArrayListRemove() {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.remove(3);
        assert( list.size() == 3 && list.root.index ==1);
        assert( list.root.next.index ==2 && list.root.next.val ==2);
        assert( list.root.next.next.index ==3);
        list.remove(2);
        assert( list.root.next.val==4 && list.root.next.index==2);
    }


    @Test
    public void testMyArrayListAdd() {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);




        assert( list.size() == 5 );
    }


    /**
     * Fill in the following template so that method second()
     * blocks until isSatisfied() returns true.
     * You can modify any or all of the contents of the three
     * methods as needed, but not the method signatures.
     * The solution should be as efficient as possible,
     * that is, a poll/sleep solution is not very efficient.
     */

    public  class ThreadTest {
        private Boolean satisfied = false;
        private MyArrayList list = new MyArrayList<Integer>();

        public void first() {
            satisfied = true;
            System.out.println("");
            System.out.println("set to true!");
            synchronized (this.list) {
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                System.out.println("list set!");
                System.out.println("list size: "+list.size());
                System.out.println("list index 4: "+list.get(4));
            }
        }

        public synchronized void second() {
                while (!isSatisfied()){
                    System.out.print("locked! ");
                }
                System.out.println("unlocking!");
                list.remove(4);
                System.out.println("list index 4: "+list.get(4));
                System.out.println("list remove finished size: "+list.size());
        }

        public boolean isSatisfied() {
            return satisfied;
        }
    }


    @Test
    public void testThreadList() {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        final ThreadTest b = new ThreadTest();
        b.list = list;
        (new Thread(new Runnable() {
            public void run() {
                b.second();
            }
        })).start();

        (new Thread(new Runnable() {
            public void run() {
                b.first();
            }
        })).start();
    }

    @Test
    public void testThreadTest() {
        final ThreadTest b = new ThreadTest();
        (new Thread(new Runnable() {
            public void run() {
                b.second();
            }
        })).start();

        (new Thread(new Runnable() {
            public void run() {
                b.first();
            }
        })).start();
    }
}
