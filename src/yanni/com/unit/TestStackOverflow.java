package yanni.com.unit;

import java.util.ArrayList;
import java.util.List;

public class TestStackOverflow {



    public static void main (String args[]) {
        // this will cause out of memory error: java heap space
        String test = "Yannnni is the best senior developer in the world!!!!!!!!!";
        List<String> testStrs = new ArrayList<String> ();
        while(true) {
            testStrs.add(test);
        }

    }
}
