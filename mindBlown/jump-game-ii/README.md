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
Given an array of non-negative integers representing jump lengths, find the minimum number of jumps to reach the last index.
This is solved using a greedy approach by always jumping to the farthest reachable position.

## Intuition
The core idea is to be as greedy as possible at each step. From the current position, we want to make a jump that allows us to reach the farthest possible index in the *next* step. This ensures we cover the maximum ground with each jump, thus minimizing the total number of jumps. We don't need to explore all possible jump combinations because the greedy choice at each step leads to the optimal solution.

## Algorithm
1. Initialize `jumps` to 0, `current_reach` to 0, and `max_reach` to 0.
2. Iterate through the array from index `i = 0` to `n-2` (since we don't need to jump from the last element).
3. In each iteration, update `max_reach` to be the maximum of its current value and `i + nums[i]`. This represents the farthest index we can reach from the current position `i`.
4. If the current index `i` is equal to `current_reach`, it means we have reached the end of our current jump's coverage.
   a. Increment `jumps` by 1.
   b. Update `current_reach` to `max_reach`. This sets the boundary for our next jump.
   c. If `current_reach` is now greater than or equal to `n-1` (the last index), we have reached the end, so we can break the loop.
5. Return `jumps`.

## Concept to Remember
*   **Greedy Algorithms:** Making the locally optimal choice at each stage to achieve a global optimum.
*   **Breadth-First Search (BFS) Analogy:** This problem can be viewed as a BFS where each "level" represents the number of jumps. We explore all reachable positions within the current jump count to find the farthest reach for the next jump.
*   **Dynamic Programming (DP) - Alternative:** While a greedy approach is optimal here, DP can also solve it by calculating the minimum jumps to reach each index. However, DP is less efficient for this specific problem.

## Common Mistakes
*   **Incorrect Loop Boundary:** Iterating up to `n-1` instead of `n-2` can lead to an unnecessary jump calculation from the last element.
*   **Not Updating `current_reach` Correctly:** Forgetting to update `current_reach` to `max_reach` when `i == current_reach` will prevent the algorithm from progressing to the next jump.
*   **Confusing `max_reach` and `current_reach`:** `max_reach` tracks the farthest possible reach from *any* position within the current jump, while `current_reach` marks the boundary of the *current* jump.
*   **Trying to Implement DP when Greedy is Sufficient:** Overcomplicating the solution with DP when a simpler greedy approach works.

## Complexity Analysis
*   Time: O(n) - reason: We iterate through the array once.
*   Space: O(1) - reason: We only use a few constant extra variables.

## Commented Code
```java
class Solution {
    // Integer[] memo; // This is for a DP approach, commented out as greedy is used.
    public int jump(int[] nums) {
        int n = nums.length; // Get the total number of elements in the array.
        if(n==1) return 0; // If there's only one element, no jumps are needed.

        int current_reach = 0; // This variable tracks the farthest index reachable with the current number of jumps.
        int max_reach = 0;     // This variable tracks the farthest index reachable from any position within the current jump's range.
        int jumps = 0;         // This variable counts the total number of jumps taken.

        // Iterate through the array. We stop at n-2 because we don't need to make a jump from the last element.
        for(int i=0; i<n-1; i++){
            // Update max_reach: calculate the farthest we can reach from the current index 'i'
            // and take the maximum between the current max_reach and this new potential reach.
            max_reach = Math.max(max_reach, i + nums[i]);

            // If we have reached the boundary of our current jump (current_reach)
            if(i == current_reach){
                jumps++; // Increment the jump count because we are starting a new jump.
                current_reach = max_reach; // Update the boundary for the next jump to the farthest point we could reach.

                // Optional optimization: if the new current_reach covers or exceeds the last index, we can stop early.
                // This check is implicitly handled by the loop condition (i < n-1) and the fact that
                // if current_reach >= n-1, the loop will naturally terminate soon after.
                // if (current_reach >= n - 1) {
                //     break;
                // }
            }
        }
        return jumps; // Return the total number of jumps required.

        // The commented out code below represents a Dynamic Programming approach.
        // memo = new Integer[n]; // Initialize memoization table.
        // return canReach(nums,n-1); // Call the recursive helper function to find jumps to reach the end.
    }

    // This is a recursive DP helper function (commented out).
    // public int canReach(int[] nums, int e){
    //     if(e==0) return 0; // Base case: if we are at index 0, 0 jumps are needed.
    //     if(memo[e]!=null) return memo[e]; // If result for index 'e' is already computed, return it.
    //     // Iterate through all previous indices 'i' to find a jump that can reach 'e'.
    //     for(int i = 0; i<e;i++) {
    //         if(i+nums[i]>=e) { // If from index 'i' we can reach or surpass index 'e'.
    //             // The number of jumps to reach 'e' is 1 (from 'i' to 'e') plus the minimum jumps to reach 'i'.
    //             return memo[e] = 1 + canReach(nums,i);
    //         }
    //     }
    //     return 0; // Should not be reached if a solution exists (problem guarantees reachability).
    // }
}
```

## Interview Tips
*   **Explain the Greedy Choice:** Clearly articulate *why* always jumping to the farthest reachable point is optimal. Mention that it maximizes progress per jump.
*   **Walk Through an Example:** Use a small example array (e.g., `[2,3,1,1,4]`) to trace your algorithm's execution step-by-step, showing how `current_reach` and `max_reach` change.
*   **Discuss the DP Alternative (Briefly):** Mention that DP is a possible approach but explain why the greedy solution is more efficient (O(n) time and O(1) space vs. O(n^2) time and O(n) space for a naive DP).
*   **Edge Cases:** Be prepared to discuss the `n=1` case and what happens if the array is empty (though LeetCode constraints usually prevent this).

## Revision Checklist
- [ ] Understand the problem: minimum jumps to reach the end.
- [ ] Identify the greedy strategy: maximize reach at each step.
- [ ] Implement the `current_reach` and `max_reach` logic correctly.
- [ ] Handle loop boundaries (`n-2`).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the greedy choice.
- [ ] Trace execution with examples.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Minimum Number of Refueling Stops (LeetCode 871)
*   Gas Station (LeetCode 134)

## Tags
`Array` `Greedy` `Dynamic Programming`
