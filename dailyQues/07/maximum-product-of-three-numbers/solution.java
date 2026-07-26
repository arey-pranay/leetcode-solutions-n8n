class Solution {
    public int maximumProduct(int[] nums) {
        int a = -1001, b = a, c = b;
        int X = 1001, Y=X;
        for(int num : nums){
            int p1 = a, p2 = b, p3 = X;
            a = Math.max(a,num);
            b = Math.max(b,Math.min(p1,num));
            c = Math.max(c,Math.min(p2,num)); 
            X = Math.min(X,num);
            Y = Math.min(Y,Math.max(p3,num));
        }
        return Math.max(a*b*c, a*X*Y);
    }
}
