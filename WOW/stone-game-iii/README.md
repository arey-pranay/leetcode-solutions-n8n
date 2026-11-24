# Stone Game Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `Dynamic Programming` `Minimax` `Game Theory` `Zero-Sum Game`  
**Time:** O(n)  
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
Stone Game III is a problem where we need to determine the winner of a game given the values of stones. We can solve this problem by using dynamic programming and memoization to keep track of the maximum net advantage for each player.

## Intuition
The key insight here is that we need to find the maximum net advantage for each player, considering that the opponent can play optimally. We can achieve this by using a recursive approach with memoization, where we store the maximum net advantage for each subproblem and reuse it to avoid redundant calculations.

## Algorithm
1. Initialize an array `memo` to store the maximum net advantage for each subproblem.
2. Define a function `getNetAdv` to calculate the maximum net advantage for a given index `i`.
3. In `getNetAdv`, if `i` is out of bounds, return 0.
4. If `memo[i]` is already calculated, return the stored value.
5. Initialize `sum` to 0 and `max` to `Integer.MIN_VALUE`.
6. Iterate over `extra` values from 0 to 2.
7. For each `extra`, calculate the sum of stones from index `i` to `i + extra`.
8. Calculate the net advantage by subtracting the maximum net advantage of the opponent from the current sum.
9. Update `max` if the current net advantage is greater than the previous maximum.
10. Store the maximum net advantage in `memo[i]`.
11. Return the maximum net advantage.

## Concept to Remember
• **Dynamic Programming**: We break down the problem into smaller subproblems and store the solutions to subproblems to avoid redundant calculations.
• **Memoization**: We use a cache to store the solutions to subproblems, allowing us to reuse them instead of recalculating them.
• **Optimal Substructure**: The problem has optimal substructure, meaning that the optimal solution can be constructed from the optimal solutions of its subproblems.

## Common Mistakes
• Failing to initialize the `memo` array properly.
• Not using memoization correctly, leading to redundant calculations.
• Not considering the optimal substructure of the problem.

## Complexity Analysis
- Time: O(n) - We make a single pass through the array to calculate the maximum net advantage for each player.
- Space: O(n) - We use an array of size `n` to store the maximum net advantage for each subproblem.

## Commented Code
```java
class Solution {
    int[] arr;
    int[] memo;

    public String stoneGameIII(int[] stoneValue) {
        arr = stoneValue;
        memo = new int[stoneValue.length];
        Arrays.fill(memo, -1);

        int aliceNetAdv = getNetAdv(0);
        if (aliceNetAdv > 0) return "Alice";
        else if (aliceNetAdv < 0) return "Bob";
        else return "Tie";
    }

    private int getNetAdv(int i) {
        if (i >= arr.length) return 0;
        if (memo[i] != -1) return memo[i];

        int sum = 0, max = Integer.MIN_VALUE;
        for (int extra = 0; extra < 3; extra++) {
            int j = i + extra;
            if (j >= arr.length) break;
            sum += arr[j];
            int netAdv = sum - getNetAdv(j + 1); // Optimized playing (minimax)
            max = Math.max(max, netAdv);
        }
        return memo[i] = max;
    }
}
```

## Interview Tips
• Make sure to initialize the `memo` array properly.
• Use memoization correctly to avoid redundant calculations.
• Consider the optimal substructure of the problem.

## Revision Checklist
- [ ] Initialize `memo` array properly.
- [ ] Use memoization correctly.
- [ ] Consider optimal substructure.

## Similar Problems
• [Stone Game II](https://leetcode.com/problems/stone-game-ii/)
• [Stone Game](https://leetcode.com/problems/stone-game/)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization` `Optimal Substructure`
