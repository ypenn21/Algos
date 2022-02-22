package yanni.com.unit2;

import org.junit.jupiter.api.Test;

public class JumpGame {

    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }

    @Test
    public void testJump() {
        JumpGame jGame = new JumpGame();
        int [] input = {1,2,3,4,5};
        int jumps = jGame.jump(input);
        assert(jumps==3);
    }
}
