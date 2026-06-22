class Solution {
    public int divide(int a, int b) {
        // Fast path: division by 1
        if (b == 1) {
            return a;
        }

        // Handle overflow case:
        // abs(Integer.MIN_VALUE) > Integer.MAX_VALUE
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign of answer
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);

        // Convert both numbers to negative.
        // Negative range is larger than positive range,
        // which helps avoid overflow for Integer.MIN_VALUE.
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;

        int ans = 0;
// 2147483648
// -2147483648

// 32
// 2^31
// 0
// 2^31 -1

// 2 * 2^31 = 2^32
        // Repeatedly subtract the largest possible multiple of divisor
        // -10 
        // -3
        while (a <= b) {
            int x = b;      // current multiple of divisor
            int qt = 1;    // corresponding quotient contribution

            // Keep doubling divisor until it can no longer fit inside the remaining dividend
            while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
                x <<= 1;    // double divisor
                qt <<= 1;  // double quotient contribution
            }
// 3->6->12->24->48->96
// 96/3 = 32

            // Remove this chunk from dividend
            ans += qt;
            a -= x;
        }

        // Apply correct sign
        return sign ? ans : -ans;
    }
}
// Is approach mein hum dono numbers ko negative bana dete hain kyunki Integer.MIN_VALUE ka positive version represent nahi ho sakta aur negative range ek value zyada store kar sakti hai. Fir direct divisor ko baar-baar subtract karne ke bajay uska largest possible power-of-2 multiple (double, quadruple, etc.) dhoondte hain jo current dividend ke andar fit ho sake. Har step mein divisor ko repeatedly double karke sabse bada valid multiple nikalte hain, us multiple ko dividend se subtract kar dete hain aur quotient mein uska corresponding count add kar dete hain. Isse ek baar mein bahut bada chunk remove ho jata hai, isliye linear subtraction ki jagah logarithmic number of operations lagte hain. End mein shuru mein nikala hua sign apply karke answer return kar dete hain.