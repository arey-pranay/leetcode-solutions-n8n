class Solution {
    public int maximumProduct(int[] nums) {
        int a = -1001, b = a, c = b; //largest 3 numbers
        int X = 1001, Y=X; // smallest 2 numbers
        for(int num : nums){
            int pa = a, pb = b, px = X;//previous values of a,b, and X. for comparison
            a = Math.max(a,num);
            b = Math.max(b,Math.min(pa,num));
            c = Math.max(c,Math.min(pb,num)); 
            
            X = Math.min(X,num);
            Y = Math.min(Y,Math.max(px,num));
        }
        return Math.max(a*b*c, a*X*Y);
    }
}
