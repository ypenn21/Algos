package yanni.com.unit.warmups;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class IsBalancedBrackets {

    public Boolean isBalancedBrackets(String input) {
        boolean isBalanced = true;

        char[] charArray = input.toCharArray();

        Stack<Character> stack = new Stack<>();

        for(Character c : charArray) {
            if('{'==c || '('==c || '['==c) {
                stack.add(c);
                continue;
            }

            if('}'==c) {
                Character sym = stack.pop();
                isBalanced = sym=='{';
            } else if (')'==c) {
                Character sym = stack.pop();
                isBalanced = sym=='(';
            } else if (']'==c) {
                Character sym = stack.pop();
                isBalanced = sym=='[';
            }

            if(!isBalanced) {
                break;
            }
        }


        return isBalanced;
    }


    @Test
    public void testIsBalanced() {
        String input = "{([)}" ;
        assert( !isBalancedBrackets( input) );
    }

    @Test
    public void testIsBalanced2() {
        String input = "{([])}" ;
        assert( isBalancedBrackets( input) );
    }

}
