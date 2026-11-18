class Solution {
    public int minChanges(String s) {
        int i=0,n=s.length(),count=0;
        while(i<n-1){
            int j= i+1;
            if(s.charAt(i)!=s.charAt(j)) count++;
            i+=2;
        }
        return count;
    }
}