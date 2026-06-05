class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {

            // Case 1: Odd length palindrome (center at i)
            count += expandFromCenter(s, i, i);

            // Case 2: Even length palindrome (center between i and i+1)
            count += expandFromCenter(s, i, i + 1);
        }

        return count;
    }

    private int expandFromCenter(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            count++;     // Found one palindrome
            left--;      // Expand outward
            right++;
        }

        return count;
    }
}
