package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindScenesRepresentedByCharacterArray {

    // outputs the length of each scene. Input represents cuts of each scene
    public List<Integer> getSceneLength(List<Character> inputCuts) {

        //build data structures to store the characters and the number of times they appear and the location of their indexes in the array
//        Map<Character, Integer> myChar = new HashMap<>();
        Map<Character, List <Integer>> myPos = new HashMap<>();
        List<Integer> results = new ArrayList<>();

        for(int i=0;i<inputCuts.size();i++) {
            Integer count=0;
//            count = myChar.get(inputCuts.get(i));
//            if(count==null) {
//                count=1;
//            } else {
//                count++;
//            }
//            myChar.put(inputCuts.get(i), count);
            List<Integer> pos = myPos.get(inputCuts.get(i));
            if(pos==null) {
                pos = new ArrayList<Integer>();
                pos.add(i);
            } else {
                pos.add(i);
            }
            myPos.put(inputCuts.get(i), pos);
        }

        // loop through the character array check each character key to see how many times they appear. If they appear more than twice
        // check the start position and end position use it to calculate the length of the scene. If a character that appears more than once
        // between the start and end positions has a end position greater than the original end position than reset end position to that number,
        // and recalculate the length of the scene. New scene starts when the current position is greater than the end position.
        int result=1;
        Integer end=0;
        // int [] charInBetweenScenes = new int[256];

        for(int index=0; index<inputCuts.size(); index++){
            boolean isOne = false;
            Character key = inputCuts.get(index);
//            Integer count = myChar.get(key);
            List<Integer> pos = myPos.get(key);
            Integer start = pos.get(0);
            if(end==0) {
                // if end is equal to 0 and count is less than 2  add the result=1 to results array
                if (pos.size() < 2) {
                    isOne = true;
                    results.add(result);
                } else {
                    end = pos.get(pos.size() - 1);
                    result = 1 + end - start;
                }
            } else {
                if(index <= end) {
//                    charInBetweenScenes[key]++;
                    int tmpEnd = pos.get(pos.size() - 1);
                    if ( end!=0 && tmpEnd > end){
                        result += tmpEnd - end;
                        end = tmpEnd;
                    }
                }
//                else if(charInBetweenScenes[key]>0){
//                    result ++;
//                }
                else {
                    results.add(result);
                    result=1;
                    end=0;
//                    charInBetweenScenes = new int[256];
                    index--;
                }
            }
            if(!isOne && index==inputCuts.size()-1){
                results.add(result);
            }
        }

        return results;
    }

    @Test
    public void testGetSceneLength(){
        FindScenesRepresentedByCharacterArray scenes = new FindScenesRepresentedByCharacterArray();
        Character[] myCuts = new Character[]{'a', 'b', 'a', 'b', 'c', 'b', 'c', 'b', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h', 'i', 'j', 'h', 'i', 'j', 'k', 'l'};
        List<Character> myCharArray = Arrays.asList(myCuts);
        List<Integer> scenesLen = scenes.getSceneLength(myCharArray);

        System.out.println(scenesLen);
    }

    @Test
    public void testGetSceneLength2(){
        FindScenesRepresentedByCharacterArray scenes = new FindScenesRepresentedByCharacterArray();
        Character[] myCuts2 = new Character[]{'a', 'b', 'a', 'b', 'c', 'b', 'c', 'b', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h', 'i', 'j', 'h', 'k', 'l','i','j'};
        List<Character> myCharArray = Arrays.asList(myCuts2);
        List<Integer> scenesLen = scenes.getSceneLength(myCharArray);

        System.out.println(scenesLen);
    }

    @Test
    public void testGetSceneLength3(){
        FindScenesRepresentedByCharacterArray scenes = new FindScenesRepresentedByCharacterArray();
        Character[] myCuts2 = new Character[]{'a', 'b', 'a', 'b', 'c', 'b'};
        List<Character> myCharArray = Arrays.asList(myCuts2);
        List<Integer> scenesLen = scenes.getSceneLength(myCharArray);

        System.out.println(scenesLen);
    }

    @Test
    public void testGetSceneLength4(){
        FindScenesRepresentedByCharacterArray scenes = new FindScenesRepresentedByCharacterArray();
        Character[] myCuts2 = new Character[]{'a', 'b', 'c'};
        List<Character> myCharArray = Arrays.asList(myCuts2);
        List<Integer> scenesLen = scenes.getSceneLength(myCharArray);

        System.out.println(scenesLen);
    }

    @Test
    public void testGetSceneLength5(){
        FindScenesRepresentedByCharacterArray scenes = new FindScenesRepresentedByCharacterArray();
        Character[] myCuts2 = new Character[]{'a'};
        List<Character> myCharArray = Arrays.asList(myCuts2);
        List<Integer> scenesLen = scenes.getSceneLength(myCharArray);

        System.out.println(scenesLen);
    }

    @Test
    public void testGetSceneLength6(){
        FindScenesRepresentedByCharacterArray scenes = new FindScenesRepresentedByCharacterArray();
        Character[] myCuts2 = new Character[]{};
        List<Character> myCharArray = Arrays.asList(myCuts2);
        List<Integer> scenesLen = scenes.getSceneLength(myCharArray);

        System.out.println(scenesLen);
    }

}
