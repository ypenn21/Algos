import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetWordsFromStringBasedOnArray {

    public static HashMap<String, ArrayList<String>> wordBreakMap = new HashMap<String, ArrayList<String>>();
    public static ArrayList<String> wordBreakAll(Set<String> dictionary, String text){
        //if already computed the current substring text then return from map
        if(wordBreakMap.containsKey(text)){
            return wordBreakMap.get(text);
        }
        ArrayList<String>  result = new ArrayList<String>();

        //if the whole word is in the dictionary then we add this to final result
        if(dictionary.contains(text)){
            result.add(text);
        }

        //try each prefix and extend
        for(int i = 0; i< text.length(); i++){
            String prefix = text.substring(0, i+1);
            if(dictionary.contains(prefix)){
                //extend
                String suffix = text.substring(i+1);
                ArrayList<String> subRes = wordBreakAll(dictionary, suffix);
                for(String word : subRes){
                    result.add(prefix+" "+word);
                }
            }
        }

        wordBreakMap.put(text, result);
        return result;
    }

    public static void main(String argp[]) {
        String [] word_list = {"dog", "cats", "sand", "cat", "and"};
        Set<String> dictionary = new HashSet<>();
        dictionary.add("dog");
        dictionary.add("cats");
        dictionary.add("sand");
        dictionary.add("cat");
        dictionary.add("and");
        String inputString = "catsanddog";
//        getWordsFromInputString(inputString, dictionary);

        List result = wordBreakAll(dictionary,  inputString);
        System.out.println(wordBreakMap.toString());
        System.out.println(result.toString());
    }

    public static List getWordsFromInputString(String inputString, String [] word_list){
        List<String> result = new ArrayList();
        for(String word : word_list) {
            if( inputString.contains(word)) {
                result.add(word);
            }
        }

        String cloneString = new String(inputString);
        List wordGroups = new ArrayList();
        Integer wordsThatRemain = 0;
       do {
           List<String> wordGroup = getWordGroup(inputString, result, new ArrayList(result));
           for (String remove : wordGroup)
            result.remove(remove);
           wordGroups.add(wordGroup);
       } while (result.size() >0);
       return wordGroups;
    }

    public static List<String> getWordGroup(String inputString, List<String> wordsNotAdded, List<String> word_list){
        List<String> wordGroup = new ArrayList();
        String cloneString = new String(inputString);
        for(String word : wordsNotAdded ) {
            if( cloneString.contains(word)) {
                wordGroup.add(word);
                cloneString = cloneString.replace(word, "");
            }
        }
        if (!cloneString.isEmpty()) {
            getWordGroup(cloneString, word_list, word_list);
        }
        return wordGroup;
    }
}
