package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PhoneNumberSpelling {

//    lettersMaps = {
//        1: [],
//        2: ['a', 'b', 'c'],
//        3: ['d', 'e', 'f'],
//        4: ['g', 'h', 'i'],
//        5: ['j', 'k', 'l'],
//        6: ['m', 'n', 'o'],
//        7: ['p', 'q', 'r', 's'],
//        8: ['t', 'u', 'v'],
//        9: ['w', 'x', 'y', 'z'],
//        0: []
//    }
//
//    validWords = ['dog', 'fish', 'cat', 'fog']
    private static final Map<Integer, List<Character>> LETTERSMAPS = new HashMap() {{
        put(1, Arrays.asList(new Character[] {}));
        put(2, Arrays.asList(new Character[] {'a', 'b', 'c'}));
        put(3, Arrays.asList(new Character[] {'d', 'e', 'f'}));
        put(4, Arrays.asList(new Character[] {'g', 'h', 'i'}));
        put(5, Arrays.asList(new Character[] {'j', 'k', 'l'}));
        put(6, Arrays.asList(new Character[] {'m', 'n', 'o'}));
        put(7, Arrays.asList(new Character[] {'p', 'q', 'r', 's'}));
        put(8, Arrays.asList(new Character[] {'t', 'u', 'w'}));
        put(9, Arrays.asList(new Character[] {'w', 'x', 'y', 'z'}));
        put(0, Arrays.asList(new Character[] {}));
    }};

    private static final Set<String> words = new HashSet() {{
        add("dog");
        add("fish");
        add("cat");
        add("fog");
    }};

    public List getSpellWords (String number, List<Character> toTry) {
        List<String> results = new ArrayList<>();
        String word=toTry.stream().map(Object::toString).collect(Collectors.joining(""));
        if (words.contains(word)) {
            results.add(word);
            return results;
        }
        if(number.length() == 0) {
            return results;
        }
        char[] myChar = number.toCharArray();
        int num1 = Integer.parseInt(myChar[0]+"");


        List<Character> chars = LETTERSMAPS.get(num1);

        for (char c : chars) {
            toTry.add(c);
            results.addAll(getSpellWords (number.substring(1), toTry));
            toTry.remove(toTry.size() - 1);
        }
        return results;
    }

    @Test
    public void testGetSpellWords() {

        List myResults = getSpellWords ("364", new ArrayList<>());
        System.out.println(myResults);
    }


}
