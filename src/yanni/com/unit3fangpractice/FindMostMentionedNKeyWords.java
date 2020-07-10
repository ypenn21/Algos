package yanni.com.unit3fangpractice;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FindMostMentionedNKeyWords {


    public String[] findMostMentionedNKeyWords(String[] possFeatures, String[] keyWords, Integer n) {

        if(possFeatures==null || keyWords==null || n==null || n==0) {
            return new String[0];
        }

        Map<String, Integer> keyWordCount= new HashMap<>();
        String[] results = new String[n];

        for(String feature : possFeatures){
            for(String keyWord:keyWords) {
                while (feature.contains(keyWord)) {
                    if (feature.contains(keyWord)) {
                        Integer count = keyWordCount.get(keyWord);
                        if (count == null)
                            count = 0;
                        count++;
                        keyWordCount.put(keyWord, count);
                    }
                    feature = feature.replaceFirst(keyWord, "");
                }
            }
        }

        for(int i=0;i<n;i++) {
           String key = getMostUsedKeyWord(keyWordCount);
            keyWordCount.remove(key);
            results[i] = key;
        }

        return results;
    }


    public String getMostUsedKeyWord(Map<String, Integer> keyWordCount) {
        Integer most=null;
        String mostKey = null;
        for (String key : keyWordCount.keySet()) {
            if (most==null) {
                most = keyWordCount.get(key);
                mostKey= key;
            } else if (most<keyWordCount.get(key)) {
                most = keyWordCount.get(key);
                mostKey= key;
            }
        }
        return mostKey;
    }

    @Test
    public void testFindMostMentionedNKeyWords() {

        String[] possFeatures = new String [] {"Alexa is a bitch. Alexa and alexa are cool", "I have amazon prime", "prime is awesome", "I use prime all the time", "Today I like Alexa",
                                                "What is the best way to search", "search needs improvement", "search is wack", "amazon sucks dick", "jeff bezos is a turd"};
        String[] keyWords = new String [] {"Alexa", "prime","search","amazon", "bitch"};

        String[] results = findMostMentionedNKeyWords( possFeatures, keyWords, 4);

        for (String result: results)
            System.out.println(result);

    }


}
