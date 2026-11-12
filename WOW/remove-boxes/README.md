# Remove Boxes

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Memoization`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    int max = 0;
    int[][][] memo;
    public int removeBoxes(int[] boxes) {
        // remove => abhi tk jitne mile utne count krke reset
        // keep it for later => baad me merge krege, abhi bs rkhlo
        int n =boxes.length;
        memo = new int[n][n][n];
        for(int[][]d2 : memo) for(int[] d1 : d2) Arrays.fill(d1,-1);
        return func(boxes,0,boxes.length-1,0);
    }
    public int func(int[]boxes, int l, int r, int k){
        if(l>r) return 0;
        if(memo[l][r][k]!=-1) return memo[l][r][k];
        int max  = (k+1)*(k+1) + func(boxes,l+1,r,0);
        for(int i=l+1;i<=r;i++) if(boxes[i]==boxes[l]) max = Math.max(max, func(boxes,l+1,i-1,0)+func(boxes,i,r,k+1));
        
        return memo[l][r][k] = max;
   }
}
// 223234241243242
// [0,1,6,5,4]
```

---

---
## Quick Revision
Given an array of boxes with different colors, remove boxes to maximize points where removing $k$ contiguous boxes of the same color yields $k^2$ points.
Solved using Dynamic Programming with Memoization by tracking the left bound
