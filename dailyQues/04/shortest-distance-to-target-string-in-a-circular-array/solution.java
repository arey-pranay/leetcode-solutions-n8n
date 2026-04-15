class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for(int i=0;i<=n/2;i++){
            if(words[insideBound(startIndex - i,n)].equals(target) ||  
                words[insideBound(startIndex + i,n)].equals(target)) 
            return i;
        }
        return -1;
    }
    public int insideBound(int i, int n){
        if(i<n) i = (i % n + n) % n;
        return i%n;
    }
}