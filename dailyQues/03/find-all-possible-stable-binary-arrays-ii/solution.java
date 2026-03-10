class Solution {
    int M =(int)1e9 + 7;
    int[][][] memo;
    public int numberOfStableArrays(int zero, int one, int limit) {
        memo = new int[zero+1][one+1][2];
        for(int[][] d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        
        int x = stable(zero,one,limit,0);
        int y = stable(zero,one,limit,1);
        
        return (x+y)%M;
    }
    public int stable(int zeroLeft , int oneLeft, int consecutive, int lastUsed ){
       
        if(zeroLeft < 0 || oneLeft < 0) return 0;
        if(zeroLeft==0 && oneLeft==0) return 1;
        
        if(memo[zeroLeft][oneLeft][lastUsed] != -1) return memo[zeroLeft][oneLeft][lastUsed];
        
        int result = 0;
        if(lastUsed == 1){ 
            result = stable(zeroLeft,oneLeft-1,consecutive,1);
            if(zeroLeft >0) result += stable(zeroLeft,oneLeft-1,consecutive,0);
            if(oneLeft>0) result -= stable(zeroLeft,oneLeft-1-consecutive,consecutive,0);
        }
        else{
            result = stable(zeroLeft-1,oneLeft,consecutive,0);
            if(oneLeft>0)  result += stable(zeroLeft-1,oneLeft,consecutive,1);
            if(zeroLeft >0) result -= stable(zeroLeft-1-consecutive,oneLeft,consecutive,1);
                
        }
        // System.out.print(result);
        // System.out.print(" "+result%M);
        result = (result % M + M) % M;
        // System.out.print(" " +result);
        // System.out.println();
        
        memo[zeroLeft][oneLeft][lastUsed] = result;
        return result;
    }
}
// z,o,u
// 10,10,0

// [9,10,1],[8,10,1],[7,10,1]..
// [9,9,0],[9,8,0]

// 10,8,0
// 10,8,1
// 9,8,0

// z,o,u
// 10,10,1
// [10,9,0],[10,8,0]/////