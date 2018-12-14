import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yannipeng on 3/26/18.
 */
public class PhraseOverlap {

//    public String findOverlappingPharse (List phrases) {
//
//        String result = "";
//
//        for ( String phrase : (List<String>) phrases ) {
//            //char[] phraseChar = phrase.toCharArray();
//            String [] words = phrase.split(" ");
//            Boolean isOverlappingAll = true;
//            for ( String phrase2 : (List<String>) phrases ) {
//                //char[] phraseChar2 = phrase2.toCharArray();
//                String revert = result;
//                if(!phrase2.equals(phrase)
//                        && (phrase2.contains(phrase)
//                        //|| phrase2.contains(words[0])
//                        || phrase2.contains(words[words.length-1]))){
//                    result = merge(phrase, phrase2);
//                } else {
//                    result = revert;
//                    isOverlappingAll = false;
//                }
//
//            }
//
//        }
//
//        return result;
//    }

    public String findOverlappingPharse (List phrases) {

        String result = "";
        Map< String, List<String>> dictonary = new LinkedHashMap< String, List<String> >();
        int totalSize = 0;
        // build data structure
        for ( String phrase : (List<String>) phrases ) {
            String[] words = phrase.split(" ");
            int lastIndex= -1;
            String subPhrase = phrase;
            for(String word : words) {
                if (lastIndex <  phrase.indexOf(word) ) {
                    subPhrase = phrase.substring(phrase.indexOf(word));
                    List subphraseList = (List) dictonary.get(word) ;
                    if( subphraseList != null ) {
                        subphraseList.add(subPhrase);
                        dictonary.put(word, subphraseList);
                    } else {
                        List tempList = new ArrayList<String>();
                        tempList.add(subPhrase);
                        dictonary.put(word, tempList);
                    }
                }
                totalSize++;
                lastIndex = phrase.indexOf(word);
            }
        }

        String largestPhrase="";
        int largest = 0;
        Set<String> leftOver = new HashSet();
        if (totalSize > dictonary.size()) {
            //find the right half of merged String
            for(Map.Entry<String, List<String>> entry : dictonary.entrySet()){
                List <String> valuesMerge = entry.getValue();
                //size greater than 1 means there is a merge
                if(valuesMerge.size()>1){
                    for (String merge : valuesMerge) {
                        if(largest < merge.length()){
                            largest = merge.length();
                            largestPhrase = merge;
                        }
                    }
                } else {
                    String mergeVal = valuesMerge.get(0);
                    leftOver.add(mergeVal);
//                    if ( !mergeVal.equals("") && mergeVal.lastIndexOf(" ")!=-1)
//                        leftOver.put( !valuesMerge.isEmpty() ? mergeVal.substring(mergeVal.lastIndexOf(" "), mergeVal.length()): "", !valuesMerge.isEmpty() ? mergeVal: "" );
                }
            }
        }


        String findString = largestPhrase.indexOf(" ") < 1 ? largestPhrase : largestPhrase.substring(0, largestPhrase.indexOf(" "));


        String largestLeftOver = "";
        //find the left half of merged String
        for(String leftOverEntry : leftOver){
            if(leftOverEntry.contains(findString)){
                String leftOvertemp = leftOverEntry.substring(0, leftOverEntry.indexOf(findString));
                if (largestLeftOver.length() < leftOvertemp.length()) {
                    largestLeftOver = leftOvertemp;
                }

            }
        }

        if(!largestLeftOver.equals("")){
//            String findStringChk = largestLeftOver.substring(largestLeftOver.indexOf(findString), largestLeftOver.length());
//            int index = largestPhrase.indexOf(findStringChk);
//            //merge left half and right half
            result = largestLeftOver + largestPhrase;
        }

        return result;
    }

    public String merge ( String phrase, String phrase2 ) {
        String result = null;
        String [] words = phrase.split(" ");
        if(phrase2.contains(words[0])) {

        }
        else if(phrase2.contains(words[words.length-1])){
            result = phrase2.replace(words[words.length-1], "");
            //result = phrase2 + phrase;
        }

        return result;
    }

    public static void main (String [] args ) {

        PhraseOverlap phraseOverlap = new PhraseOverlap();

        String phrase1 = "cry boys don't cry";
        String phrase2 = "He don't cry for me. Argentina";
        String phrase3 = "don't cry for me. Argentina is great";
        String phrase4 = "no overlap at all";
        String phrase5 = "Those boys don't cry";

        List<String> pharses = Arrays.asList(phrase1, phrase5, phrase2, phrase3, phrase4);
        //List<String> pharses = Arrays.asList(phrase1, phrase2);
        System.out.println("End Result Should Be: \nThose boys don't cry for me. Argentina is great");
        System.out.println(phraseOverlap.findOverlappingPharse(pharses));

        String phrase6 = "cry no overlap at all";
        String phrase7 = "Those boys don't cry";

        List<String> pharses2 = Arrays.asList(phrase6, phrase7);

        //System.out.println("End Result Should Be: \nThose boys don't cry for me. Argentina is great");
        System.out.println(phraseOverlap.findOverlappingPharse(pharses2));

    }
}
