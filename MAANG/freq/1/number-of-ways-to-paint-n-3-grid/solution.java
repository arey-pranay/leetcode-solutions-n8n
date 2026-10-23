class Solution {
    int MOD = 1_000_000_007;
    public int numOfWays(int n) {
        long X = 6, Y = 6; // X -> ABC, Y -> ABA
        for(int i=2;i<=n;i++){
            long newX = (2*X + 2*Y)%MOD;
            long newY = (2*X + 3*Y)%MOD;
            X = newX;
            Y = newY;
        }
        return (int) ((X+Y)%MOD);
    }
}

// prev => ABC, curr => ABC wale 2, ABA wale 2
// prev => ABA, curr => ABC wale 2, ABA wale 3

// "r g y"
//  g y r
//  y r g
 
//  and
//  g y g
//  g r g

// "r g r" 
//  g r g
//  g y g 
//  y r y
 
//  and 
 
//  y r g
//  g r y 
 
 