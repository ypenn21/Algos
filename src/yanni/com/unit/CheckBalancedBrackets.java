package yanni.com.unit;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class CheckBalancedBrackets {

    @Test
    public void checkBrackets () {
        String brackets = "[()]{}{[()()]()}";
        System.out.println("balanced:"+checkBalancedReverse(brackets) );
        String bracketsUnbalanced = "[(])";
        System.out.println("balanced:"+checkBalancedReverse(bracketsUnbalanced) );
    }

    public boolean checkBalancedReverse(String brackets) {
        char [] c= brackets.toCharArray();

        boolean result = true;

        Stack<Character> myBrackets = new Stack<>();

        for(int i=c.length-1; i>=0; i--) {

            if(c[i]==']' || c[i]==')' || c[i]=='}') {
                myBrackets.push(c[i]);
            }

            if(c[i]=='[') {
                result = myBrackets.pop()==']';
            }
            else if(c[i]=='(') {
                result = myBrackets.pop()==')';
            }
            else if(c[i]=='{') {
                result = myBrackets.pop()=='}';
            }
            if(!result)
                break;
        }

        return result;
    }

    public boolean checkBalanced(String brackets) {

        char [] c= brackets.toCharArray();

        boolean result = true;

        Stack<Character> myBrackets = new Stack<>();

        for(int i=0; i<c.length; i++) {
            if(c[i]=='[' || c[i]=='(' || c[i]=='{') {
                myBrackets.push(c[i]);
            }

            if(c[i]==']') {
                result = myBrackets.pop()=='[';
            }
            else if(c[i]==')') {
                result = myBrackets.pop()=='(';
            }
            else if(c[i]=='}') {
                result = myBrackets.pop()=='{';
            }
            if(!result)
                break;
        }

        return result;
    }
}
