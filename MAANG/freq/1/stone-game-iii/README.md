# Stone Game Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `Dynamic Programming` `Minimax` `Game Theory` `Zero-Sum Game`  
**Time:** O(n^3)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    int[] memo;
    public String stoneGameIII(int[] stoneValue) {
        arr = stoneValue;
        memo = new int[stoneValue.length];
        Arrays.fill(memo,-1);
        int aliceNetAdv = getNetAdv(0); 
        //alice ne apna pick krke, bob ko chance diya. so 1,2,3 teeno picks me se max jo alice le skti thi, wo humne use kiya
        if(aliceNetAdv > 0) return "Alice";
        else if(aliceNetAdv < 0) return "Bob";
        else return "Tie";
        
        // take -> 1,2,3
        // curr += stoneValue[i];
        
        // algo -> i index se start krke, current player humesha apna max find krega, aur next player ko chance dega
        //         take can be 1,2,3. teeno case me sum me wo val add hogi, and humusme se, jo net nuksaan hoga, (opponent ka score, wo hata dege)
        //         agr humara +ve bacha, to hum jeet gye, nhi to opponent jeet gya.
    }
    private int getNetAdv(int i){ 
        if(i>=arr.length) return 0;
        if(memo[i]!=-1) return memo[i];
        int sum =0,  max=Integer.MIN_VALUE;
        for(int extra=0;extra<3;extra++){
            int j=i+extra;
            if(j >= arr.length) break;
            sum += arr[j];
            int netAdv = sum - getNetAdv(j+1); //optimized playing (minimax)
            max = Math.max(max,netAdv);
        }
        return memo[i]=max;
    }
}
// 1 2 
// 3 4 6 100
```

---

---

## Quick Revision
The problem is about finding the outcome of a stone game where two players, Alice and Bob, take turns picking stones from a pile. The player with the maximum net advantage at the end of the game wins. We can solve this problem by using dynamic programming and memoization to optimize the game tree.

## Intuition
The key insight is that the maximum net advantage for Alice at any point is the maximum value that she can achieve by picking stones from the current position to the end of the game. This can be computed by iterating over all possible picks (1, 2, or 3 stones) and selecting the maximum net advantage.

## Algorithm
1. Initialize an array `arr` to store the stone values and a memoization array `memo` to store the maximum net advantage at each position.
2. Define a function `getNetAdv` to compute the maximum net advantage at a given position `i`.
3. In `getNetAdv`, if the current position `i` is out of bounds, return 0.
4. If the maximum net advantage at position `i` is already computed and stored in `memo`, return it.
5. Iterate over all possible picks (1, 2, or 3 stones) and compute the maximum net advantage by recursively calling `getNetAdv` with the next position `j` and adding the current stone value to the sum.
6. Store the maximum net advantage in `memo` and return it.

## Concept to Remember
* Dynamic programming: breaking down the problem into smaller sub-problems and storing the solutions to sub-problems to avoid redundant computation.
* Memoization: using a cache to store the results of expensive function calls to avoid recomputing them.

## Common Mistakes
* Not initializing the memoization array properly.
* Not considering all possible picks (1, 2, or 3 stones) when computing the maximum net advantage.
* Not handling the base case of the recursion correctly (i.e., when the current position is out of bounds).

## Complexity Analysis
- Time: O(n^3) where n is the number of stones, as we have a triple loop to consider all possible picks and recursive calls.
- Space: O(n) for the memoization array.

## Commented Code
```java
class Solution {
    int[] arr;
    int[] memo;
    public String stoneGameIII(int[] stoneValue) {
        arr = stoneValue;
        memo = new int[stoneValue.length];
        Arrays.fill(memo,-1);
        int aliceNetAdv = getNetAdv(0);
        if(aliceNetAdv > 0) return "Alice";
        else if(aliceNetAdv < 0) return "Bob";
        else return "Tie";
        
        private int getNetAdv(int i){ 
        if(i>=arr.length) return 0;
        if(memo[i]!=-1) return memo[i];
        int sum =0,  max=Integer.MIN_VALUE;
        for(int extra=0;extra<3;extra++){
            int j=i+extra;
            if(j >= arr.length) break;
            sum += arr[j];
            int netAdv = sum - getNetAdv(j+1); //optimized playing (minimax)
            max = Math.max(max,netAdv);
        }
        return memo[i]=max;
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and identify the key insight (in this case, dynamic programming and memoization).
* Pay attention to the time and space complexity of your solution.
* Practice implementing the solution from scratch to improve your coding skills.
* Review common mistakes and try to identify them early in the solution.

## Revision Checklist
- [ ] Review the problem statement and key insight.
- [ ] Implement the solution from scratch.
- [ ] Review common mistakes and edge cases.
- [ ] Practice solving similar problems.

## Similar Problems
* 486. Predict the Winner
* 1221. Split Array into Consecutive Sum Sequences

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization`
