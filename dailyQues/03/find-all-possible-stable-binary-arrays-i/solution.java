class Solution {
    int M = (int)1e9 + 7;
    public int numberOfStableArrays(int zero, int one, int limit) {
        int x = 0 ; 
        int y = 0 ; 
        if(zero>0) x = stable(zero-1,one,limit,false);
        if(one>0) y = stable(zero,one-1,limit,true);
        return (x+y)%M;
    }
    
    public int stable(int zeroLeft , int oneLeft, int limit, boolean lastUsedOne){
        int result = 0;
        if(zeroLeft==0 && oneLeft==0) return 1;
        if(lastUsedOne){ 
            for(int i = 1 ; i<=Math.min(zeroLeft,limit);i++){
                result += stable(zeroLeft-i,oneLeft,limit,false);
                result %= M;
            }
        }
        else{
            for(int i = 1 ; i<=Math.min(oneLeft,limit);i++){
                result = (result+stable(zeroLeft,oneLeft-i,limit,true))%M;
            }
        }
        return result;
    }
}