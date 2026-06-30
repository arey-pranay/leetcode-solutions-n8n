class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        if (n < 3) return 0;

        int ans = 0;

        // sliding window counts
        int[] cnt = new int[3];
        int left = 0;

        for (int right = 0; right < n; right++) {
            cnt[s.charAt(right) - 'a']++;

            // If window has all 3 characters
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                // all substrings starting at left and ending at right..n-1 are valid
                ans += n - right;

                // shrink from left
                cnt[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}