class Solution {
    public String smallestSubsequence(String text) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        
        for (char c : text.toCharArray()) count[c-'a']++;
        for ( char c : text.toCharArray()) {
            count[c-'a']--;
            if (used[c-'a']) continue; // sbko exactly 1 hi baar use krna hai
            while (sb.length() > 0 && last(sb) > c && count[last(sb)-'a'] > 0) {
                //agr lexicographically bda character last pe already rkha hai, and wo humko aage firse mil skta hai, to usko hata do
                used[last(sb)-'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            used[c-'a'] = true;
            sb.append(c);
        }
        return sb.toString();
    }

    private char last(StringBuilder sb) {
        return sb.charAt(sb.length() - 1);
    }
}