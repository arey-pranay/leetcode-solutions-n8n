class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        for(char c : text.toCharArray()) freq[c-'a']++;
        int max = Integer.MAX_VALUE;
        for (char c : "balon".toCharArray()) max = Math.min(max, freq[c - 'a'] / ((c == 'l' || c == 'o') ? 2 : 1));
        return max == Integer.MAX_VALUE ? 0 : max;
    }
}