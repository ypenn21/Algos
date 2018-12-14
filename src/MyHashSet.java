import java.time.Period;
import java.util.ArrayList;

/**
 * Created by yannipeng on 12/1/17.
 */
public class MyHashSet {

    public static void main(String args[]) {
        String[] myBuckets = new  String[100000];
        String apple = "a";
        String candy = "c";

        myBuckets[apple.hashCode()] = apple;
        myBuckets[candy.hashCode()] = candy;

        System.out.println(myBuckets[apple.hashCode()]);
        Integer a = apple.hashCode();
//        Integer temp = a;
//        Integer b = 2;
//        Integer temp2 = b;

//        b = temp;
//        a = temp2;

        System.out.println("a="+a);

//        Person person1 = new Person(1);
//        Person personTemp1 =new Person(1);
//        Person person2 = new Person(1);
//        Person personTemp2 =new Person(1);
    }
}
