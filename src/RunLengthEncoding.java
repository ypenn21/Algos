import java.util.HashSet;
import java.util.Set;

public class RunLengthEncoding {


    public String runLengthEncoding(String input) {
        int len = input.length();
        int index = 0;
        String output="";
        char[] myChars = input.toCharArray();

        while(index<len) {
            int count=1;
            char currentCharacter = myChars[index];
            while( index<len-1 && myChars[index]==myChars[index+1] ){
                count++;
                index++;
            }
            output+=""+currentCharacter+(count);
            index++;
        }
        return output;
    }

    public String runLengthEncodingNotConsecutive(String input) {
        int len = input.length();
        int index = 0;
        String output="";
        char[] myChars = input.toCharArray();
        int[] charBucket = new int[235];
        Set<Character> uniqueChar = new HashSet();

        while(index<len) {
            char currentCharacter = myChars[index];
            charBucket[currentCharacter]++;
            index++;
        }

        for(Character chara : myChars) {
            if(uniqueChar.contains(chara)){
                continue;
            } else {
                uniqueChar.add(chara);
            }
            output+=chara+""+charBucket[chara];
        }

        return output;
    }


    public static void main(String[] args)
    {
        String str = "wwwwaaadexxxxxxywwwwa";
        RunLengthEncoding encoding = new RunLengthEncoding();
        String output = encoding.runLengthEncoding(str);
        System.out.println(output);


        String output2 = encoding.runLengthEncodingNotConsecutive(str);
        System.out.println(output2);
    }


}
