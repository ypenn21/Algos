package yanni.com.codingpractice4;

import org.junit.jupiter.api.Test;

public class DivisionWithUsingDivideOrMulti {

    private static int HALF_INT_MIN = -1073741824;
    public int divide2(int dividend, int divisor) {

        // Special cases: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        /* We want to find the largest doubling of the divisor in the negative 32-bit
         * integer range that could fit into the dividend.
         * Note if it would cause an overflow by being less than HALF_INT_MIN,
         * then we just stop as we know double it would not fit into INT_MIN anyway. */
        int maxBit = 0;
        while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
            maxBit += 1;
            divisor += divisor;
        }

        int quotient = 0;
        /* We start from the biggest bit and shift our divisor to the right
         * until we can't shift it any further */
        for (int bit = maxBit; bit >= 0; bit--) {
            /* If the divisor fits into the dividend, then we should set the current
             * bit to 1. We can do this by subtracting a 1 shifted by the appropriate
             * number of bits. */
            if (divisor >= dividend) {
                quotient -= (1 << bit);
                /* Remove the current divisor from the dividend, as we've now
                 * considered this part. */
                dividend -= divisor;
            }
            /* Shift the divisor to the right so that it's in the right place
             * for the next positon we're checking at. */
            divisor = (divisor + 1) >> 1;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            quotient = -quotient;
        }
        return quotient;
    }




        public long divide(int dividend, int divisor) {
            long sum = 0;
            long index=0;

            long absDiv = getAbs(dividend);
            long absDivisor = getAbs(divisor);



            while(sum<= absDiv) {
                sum = sum + absDivisor;
                index++;
            }

            int multi = 1;

            if( (dividend < 0 || divisor < 0) && !(dividend < 0  && divisor < 0 )) {
                multi =-1;
            }

            index = (index-1);
            return index*multi;
        }

        public long getAbs(long num){
            if(num<0) {
                num = num*-1;
            }

            return num;
        }


        @Test
        public void testDivide() {
            long result  = divide(100, -3);
            assert(result==-33);

        }


        @Test
        public void testDivide3() {
            long result  = divide(-100, -3);
            assert(result==33);
        }

        @Test
        public void testDivide2() {
            long result  = divide(-2147483648, -1);
            assert(result==new Long("2147483648"));
        }

        @Test
        public void testDivide4() {
            long result  = divide(100, 3);
            assert(result==33);
        }

        @Test
        public void testDivide5() {
            long result  = divide(10, 2);
            assert(result==5);
        }

}
