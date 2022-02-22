package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

public class PalindromeSubStr{

    public String longestPalindrome(String s) {
        if(null == s || s.length()==0) {
            return "";
        }
        char[] myChars = s.toCharArray();
        String chk = "";
        String pal =myChars[0]+"";
        for(int i=0;i<myChars.length;i++) {
            chk=myChars[i]+"";
            for(int ind=i+1;ind<myChars.length;ind++) {
                chk+=myChars[ind]+"";
                if(chk.length()>1) {
                    if(isPalindrome(chk))
                        if(chk.length() > pal.length())
                            pal = chk;
                }
            }
        }

        return pal;
    }

    public boolean isPalindrome(String s) {
        char[] myChars = s.toCharArray();
//        reverseString(myCharsRevert);
        boolean isPalindrome = true;
        int backInd = myChars.length-1;
        for(int i=0;i<myChars.length;i++) {
            isPalindrome = (myChars[i] ==  myChars[backInd]);
            backInd--;
            if(!isPalindrome) {
                break;
            }
        }
        return isPalindrome;
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    @Test
    public void testLongestPalindrome(){
        assert("aca".equals(longestPalindrome("aacabdkacaa")));
    }

    @Test
    public void testLongestPalindrome2(){
        assert("aaa".equals(longestPalindrome("aaa")));
    }

    @Test
    public void testLongestPalindrome3(){
        assert("a".equals(longestPalindrome("a")));
    }

    @Test
    public void testLongestPalindrome4(){
        assert("".equals(longestPalindrome("")));
    }

    @Test
    public void testLongestPalindrome5(){
        assert("abaaba".equals(longestPalindrome("abaaba")));
    }

    @Test
    public void testLongestPalindrome6(){
        assert("bb".equals(longestPalindrome("cbbd")));
    }

    @Test
    public void testLongestPalindrome7(){
        String longest = longestPalindrome2("kztakrekvefgchersuoiuatzlmwynzjhdqqftjcqmntoyckqfawikkdrnfgbwtdpbkymvwoumurjdzygyzsbmwzpcxcdmmpwzmeibligwiiqbecxwyxigikoewwrczkanwwqukszsbjukzumzladrvjefpegyicsgctdvldetuegxwihdtitqrdmygdrsweahfrepdcudvyvrggbkthztxwicyzazjyeztytwiyybqdsczozvtegodacdokczfmwqfmyuixbeeqluqcqwxpyrkpfcdosttzooykpvdykfxulttvvwnzftndvhsvpgrgdzsvfxdtzztdiswgwxzvbpsjlizlfrlgvlnwbjwbujafjaedivvgnbgwcdbzbdbprqrflfhahsvlcekeyqueyxjfetkxpapbeejoxwxlgepmxzowldsmqllpzeymakcshfzkvyykwljeltutdmrhxcbzizihzinywggzjctzasvefcxmhnusdvlderconvaisaetcdldeveeemhugipfzbhrwidcjpfrumshbdofchpgcsbkvaexfmenpsuodatxjavoszcitjewflejjmsuvyuyrkumednsfkbgvbqxfphfqeqozcnabmtedffvzwbgbzbfydiyaevoqtfmzxaujdydtjftapkpdhnbmrylcibzuqqynvnsihmyxdcrfftkuoymzoxpnashaderlosnkxbhamkkxfhwjsyehkmblhppbyspmcwuoguptliashefdklokjpggfiixozsrlwmeksmzdcvipgkwxwynzsvxnqtchgwwadqybkguscfyrbyxudzrxacoplmcqcsmkraimfwbauvytkxdnglwfuvehpxd");
        assert("dtzztd".equals(longest));
    }

    @Test
    public void testLongestPalindrome8(){
        assert("a".equals(longestPalindrome("ac")));
    }

    @Test
    public void testLongestPalindrome9(){
        assert("cc".equals(longestPalindrome("acc")));
    }

    @Test
    public void testLongestPalindrome10(){
        assert("cbc".equals(longestPalindrome("accbc")));
    }

    public void reverseString(char [] word) {

        int size = word.length;
        int m = size/2;
        int reverse = size-1;

        for (int i=0;i<size;i++) {
            if(i==m && m%2!=0) {
                break;
            }  else if (i==m && m%2==0) {
                break;
            }
            char tmp = word[i];
            word[i] = word[reverse];
            word[reverse] = tmp;
            reverse--;
        }
    }
}
