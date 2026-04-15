class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for(int i=0;i<=n/2;i++){
            if(words[bound(startIndex - i,n)].equals(target) ||  
                words[bound(startIndex + i,n)].equals(target)) 
            return i;
        }
        return -1;
    }
    public int bound(int i, int n){
        return (i % n + n) % n;
    }
}