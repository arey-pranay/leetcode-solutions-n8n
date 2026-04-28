class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) {
            right = right & (right - 1);
        }
        return right;
    }
}
// Brian Kernighan trick
// right isliye kyonki right se and p bada difference m no. gyb hoga , lekin left kyonki chhoti value hai to bada difference m numbero ka gyb hona ho ni paayge , paryapt ni gyb honge 
// ab gyb kyon kese ho rhe hai - > aur unke hone naa hona equal kese hai ?? -> kyonki 2 no. pados k & ho rhe hai , unse jo 1's 0 ban gye to ab iske sath kuchh bhi kabhi 1 ni hoga 
// 2 pados k hi kyon le rhe hum ? -> becuase It removes the lowest set bit (rightmost 1) from right.

//right contains all the possible 1s that might not survive