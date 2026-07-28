class Solution {
    public int leastInterval(char[] tasks, int n) {
        int maxWale = 0;
        int max = 0;
        int[] freq= new int[26];
        int distinct = 0;
        for(char c : tasks) freq[c-'A']++;
        for(int i=0;i<26;i++) if(freq[i] > max){maxWale = 1; max = freq[i];} else if(freq[i]==max) maxWale++;
        return Math.max(tasks.length,(max-1)*(n+1)+maxWale);
    }
}