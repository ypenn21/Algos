import org.junit.jupiter.api.Test;

public class ReverseString {

    public void reverse(String myString){
        if (myString == null ){
            return;
        }
        char [] myCharacters = myString.toCharArray();

        int beg = 0;
        for ( int end = myCharacters.length-1; end >= 0 ; end-- ) {
//            if (myCharacters.length % 2 == 0 && beg-1 == end) {
//                break;
//            } else if (beg == end) {
//                break;
//            }
            char temp = myCharacters[beg];
            myCharacters[beg] = myCharacters[end];
            myCharacters[end] = temp;
            if (beg == end) {
                break;
            } else if ( myCharacters.length % 2 == 0 && beg +1 == end ) { //if its even length then beg and end will offset each other by one and never equal each other
                                                                          // when incremented and decremented depending on where you put the if statement
                break;
            }
            beg++;
        }
        String output = new String(myCharacters);
        System.out.println(output);
    }

    @Test
    public void testReverseStrEven(){
        reverse("fuckit");
    }

    @Test
    public void testReverseStrOdd(){
        reverse("fuckits");
    }

    @Test
    public void testReverseStrempty(){
        reverse("");
    }


}
