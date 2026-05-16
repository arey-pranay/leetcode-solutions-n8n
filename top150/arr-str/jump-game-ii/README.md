# Jump Game Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    Integer[] memo;
    public int jump(int[] nums) {
        int n = nums.length;
        if(n==1) return 0;
        int curr = 0;
        int max = 0;
        int jumps = 0;
        for(int i=0;i<n-1;i++){
            max = Math.max(max, i+nums[i]);
            if(i==curr){
                jumps++;
                curr = max;
            }
        }
        return jumps;
        // memo = new Integer[n];
        // return canReach(nums,n-1);
    }
    // public int canReach(int[] nums, int e){
    //     if(e==0) return 0;
    //     if(memo[e]!=null) return memo[e];
    //     for(int i = 0; i<e;i++) if(i+nums[i]>=e) return memo[e] = 1+canReach(nums,i);
    //     return 0;
    // }
}
```

---

---
## Quick Revision
Given an array of non-negative integers, find the minimum number of jumps to reach the last index.
This can be solved greedily by always jumping to the farthest reachable position.

## Intuition
The core idea is to minimize jumps. At each step, we want to make a jump that allows us to reach the farthest possible index in the *next* step. This greedy approach works because we are always trying to maximize our progress towards the end. If we can reach index `j` from index `i`, and `j` allows us to reach farther than any other index reachable from `i`, then choosing `j` is optimal. We don't need to explore all possible jumps from `i`; we just need to know the maximum reach from the current "jump boundary".

## Algorithm
1. Initialize `jumps` to 0, `current_reach` (the farthest index reachable with the current number of jumps) to 0, and `max_reach` (the farthest index reachable from any position within the current jump's range) to 0.
2. Iterate through the array from index `i = 0` to `n-2` (since we don't need to jump from the last element).
3. In each iteration, update `max_reach` to be the maximum of its current value and `i + nums[i]`. This represents the farthest we can reach from the current position `i`.
4. If the current index `i` reaches `current_reach`, it means we have exhausted all positions reachable with the current number of jumps. We must make another jump.
   - Increment `jumps`.
   - Update `current_reach` to `max_reach`. This sets the new boundary for the next jump.
5. After the loop finishes, `jumps` will hold the minimum number of jumps required to reach the last index. Return `jumps`.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Breadth-First Search (BFS) Analogy:** The problem can be viewed as a BFS where each "level" represents the number of jumps. We explore all reachable nodes (indices) at the current level before moving to the next. `current_reach` marks the end of the current level, and `max_reach` finds the farthest node in the next level.
*   **Dynamic Programming (DP) - Alternative:** While the greedy approach is more efficient, a DP solution (commented out in the provided code) also exists, where `dp[i]` stores the minimum jumps to reach index `i`.

## Common Mistakes
*   **Incorrectly updating `current_reach`:** Updating `current_reach` too early or too late can lead to an incorrect jump count. It should only be updated when `i` reaches the previous `current_reach`.
*   **Off-by-one errors in loop bounds:** The loop should go up to `n-2` because we don't need to jump *from* the last element.
*   **Not handling the `n=1` case:** If the array has only one element, 0 jumps are needed.
*   **Confusing `max_reach` with `current_reach`:** `max_reach` is the potential farthest point, while `current_reach` is the boundary of the current jump.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the array once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables like `jumps`, `current_reach`, and `max_reach`.

## Commented Code
```java
class Solution {
    // Integer[] memo; // This array is for a DP approach, not used in the greedy solution.
    public int jump(int[] nums) {
        int n = nums.length; // Get the total number of elements in the array.
        if(n==1) return 0; // If there's only one element, we are already at the end, so 0 jumps are needed.

        int current_reach = 0; // This variable tracks the farthest index reachable with the current number of jumps.
        int max_reach = 0;     // This variable tracks the farthest index reachable from any position within the current jump's range.
        int jumps = 0;         // This variable counts the total number of jumps made.

        // Iterate through the array. We stop at n-1 because we don't need to jump from the last element.
        for(int i = 0; i < n - 1; i++){
            // Update max_reach: calculate the farthest we can reach from the current position 'i'
            // and take the maximum between the current max_reach and this new potential reach.
            max_reach = Math.max(max_reach, i + nums[i]);

            // If the current index 'i' has reached the boundary of our current jump ('current_reach')...
            if(i == current_reach){
                // ...it means we must make another jump to extend our reach.
                jumps++;
                // The new boundary for our next jump will be the farthest point we could reach from any position within the previous jump's range.
                current_reach = max_reach;

                // Optional optimization: If the new current_reach is already at or beyond the last index, we can stop early.
                // This check is implicitly handled by the loop condition (i < n-1) and the fact that max_reach will eventually cover n-1.
                // if (current_reach >= n - 1) {
                //     break;
                // }
            }
        }
        // After the loop, 'jumps' holds the minimum number of jumps required to reach the last index.
        return jumps;

        // The commented out section below is an alternative Dynamic Programming approach.
        // It's less efficient in terms of time complexity for this specific problem.
        // memo = new Integer[n]; // Initialize memoization table.
        // return canReach(nums,n-1); // Call the recursive helper function to find jumps to reach the last index (n-1).
    }

    // This is a recursive DP helper function (commented out).
    // public int canReach(int[] nums, int e){
    //     if(e==0) return 0; // Base case: If we are at index 0, 0 jumps are needed.
    //     if(memo[e]!=null) return memo[e]; // If result for index 'e' is already computed, return it.
    //     // Iterate through all previous indices 'i' to find a jump that can reach 'e'.
    //     for(int i = 0; i < e; i++){
    //         // If from index 'i', we can reach index 'e' (i.e., i + nums[i] >= e)...
    //         if(i + nums[i] >= e){
    //             // ...then the number of jumps to reach 'e' is 1 (for the jump from 'i' to 'e') plus the minimum jumps to reach 'i'.
    //             // We store this result in memo[e] and return it.
    //             return memo[e] = 1 + canReach(nums,i);
    //         }
    //     }
    //     // If no previous index can reach 'e', it's unreachable (though problem constraints usually guarantee reachability).
    //     return 0; // This return should ideally not be reached if the last index is always reachable.
    // }
}
```

## Interview Tips
*   **Explain the Greedy Choice:** Clearly articulate *why* always jumping to the farthest reachable point is optimal. Use an example to illustrate.
*   **Walk Through an Example:** Trace the execution of your greedy algorithm with a small input array (e.g., `[2,3,1,1,4]`) to demonstrate understanding.
*   **Discuss Alternatives:** Mention that a DP approach is possible but less efficient. This shows broader knowledge.
*   **Edge Cases:** Be prepared to discuss the `n=1` case and what happens if the last index is unreachable (though problem constraints usually prevent this).

## Revision Checklist
- [ ] Understand the problem: minimum jumps to reach the end.
- [ ] Identify the greedy strategy: maximize reach at each step.
- [ ] Implement the `current_reach` and `max_reach` logic correctly.
- [ ] Handle the loop termination condition (`n-1`).
- [ ] Consider the `n=1` edge case.
- [ ] Analyze time and space complexity.
- [ ] Be ready to explain the greedy choice.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Minimum Number of Refueling Stops (LeetCode 871)
*   Gas Station (LeetCode 134)

## Tags
`Array` `Greedy` `Dynamic Programming`
