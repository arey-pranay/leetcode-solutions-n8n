class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder("");
        for(String word : words){
            int res = 0;
            for(char c : word.toCharArray()) res += weights[c-'a'];
            res %= 26;
            sb.append((char)('z'-res));
        }
        return sb.toString();
    }
}