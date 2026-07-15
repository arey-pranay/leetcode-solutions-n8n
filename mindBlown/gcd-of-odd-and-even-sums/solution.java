class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n;
        // 2*1 + 2*2 + 2*3 + 2*4
        // 2 * (n*(n+1)/2) = n*(n+1)

        // (2*1)-1 + (2*2)-1 + (2*3)-1 + (2*4)-1
        // (2* (n*(n+1)/2)) - n = n*(n+1) - n =  n*n + n - n = n*(n)

        // n divides both numbers always, and those quotients are adjacent, so n is the HCF or the GCD.
    }
}

