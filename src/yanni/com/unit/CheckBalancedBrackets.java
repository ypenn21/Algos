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

    private boolean checkBalancedReverse (String checkBalanced ) {

        boolean result = true;

        char[] characters = checkBalanced.toCharArray();

        Stack<Character> stack = new Stack<>();

        for(int i=characters.length-1; i >= 0; i--) {
            Character character = characters[i];
            if(character.equals(']') || character.equals('}') || character.equals(')')) {
                stack.add(character);
            }

            Character chk;
            if(character.equals('[') || character.equals('{') || character.equals('(')) {
                if(stack.isEmpty()) {
                    return false;
                }
                chk = stack.pop();
                if(character.equals('[') && !chk.equals(']')) {
                    return false;
                } else if(character.equals('{') && !chk.equals('}')) {
                    return false;
                } else if(character.equals('(') && !chk.equals(')')) {
                    return false;
                }
            }

        }

        return result;

    }

    private boolean checkBalanced (String checkBalanced ) {

        boolean result = true;

        char[] characters = checkBalanced.toCharArray();

        Stack<Character> stack = new Stack<>();

        for(Character character : characters) {

            if(character.equals('[') || character.equals('{') || character.equals('(')) {
                stack.add(character);
            }

            Character chk;
            if(character.equals(']') || character.equals('}') || character.equals(')')) {
                if(stack.isEmpty()) {
                    return false;
                }
                chk = stack.pop();
                if(character.equals(']') && !chk.equals('[')) {
                    return false;
                } else if(character.equals('}') && !chk.equals('{')) {
                    return false;
                } else if(character.equals(')') && !chk.equals('(')) {
                    return false;
                }
            }

        }

        return result;

    }
}
