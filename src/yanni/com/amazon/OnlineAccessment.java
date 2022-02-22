package yanni.com.amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OnlineAccessment {

    @Test
    public void codingTest1() {
        List<String> arr = new ArrayList<>();

        arr.add("1 2");
        arr.add("1 3");
        arr.add("2 4");
        arr.add("3 5");
        arr.add("7 8");
        System.out.println(set(3, arr));
    }


    @Test
    public void codingTest2() {
        List<String> arr = new ArrayList<>();

        arr.add("1 2");
        arr.add("1 4");
        System.out.println(set(3, arr));
    }

    public static int set(int n, List<String> arr) {
        if (n < 2) {
            return n;
        }
        List<HashSet<Integer>> list = (new ArrayList<>());

        for (int i = 0; i < n; i++) {
            HashSet<Integer> intHash = new HashSet<>();
            intHash.add(i);
            list.add(intHash);
        }

        for (String str : arr) {
            String[] chars = str.split(" ");
            int [] edge = new int [2];
            for (int i=0; i< chars.length; i++) {
                edge[i] = Integer.parseInt(chars[i]);
            }

            HashSet<Integer> value = null;
            for (int i = 0; i < list.size(); i++) {
                HashSet<Integer> item = list.get(i);
                if (item.contains(edge[0])) {
                    if (value == null) {
                        value = item;
                    } else {
                        value.addAll(item);
                        list.remove(item);
                    }
                }
                if (item.contains(edge[1])) {
                    if (value == null) {
                        value = item;
                    } else {
                        value.addAll(item);
                        list.remove(item);
                    }
                }
            }
        }
        int result = 0;
        for (HashSet<Integer> item : list) {
            result = (int) (result + Math.ceil(Math.sqrt(item.size())));
        }
        return result;
    }

}
