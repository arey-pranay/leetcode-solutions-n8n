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
    public int jump(int[] nums) {

        int jumps = 0;
        int currEnd = 0;
        int farthest = 0;

        for(int i = 0; i < nums.length - 1; i++) {

            farthest = Math.max(farthest, i + nums[i]);

            if(i == currEnd) {
                jumps++;
                currEnd = farthest;
            }
        }

        return jumps;
    }
}

// class Solution {
//     int n ;
//     int[] memo ;
//     public int jump(int[] nums) {
//         n = nums.length;
//         memo = new int[n];
//         Arrays.fill(memo,10001);
//         return func(0,nums);
//     }
//     public int func(int i , int[] nums){
//         if(i>=n-1) return 0;
//         if(memo[i]!=10001) return memo[i];
//         for(int j=1;j<=nums[i];j++)  memo[i]=Math.min(memo[i],1+func(i+j,nums));
//         return memo[i];
//     }
// }
```

---

---
## Quick Revision
Given an array of non-negative integers, find the minimum number of jumps to reach the last index.
This can be solved greedily by always jumping to the farthest reachable position.

## Intuition
The core idea is to be as greedy as possible at each step. From the current position, we want to make a jump that allows us to reach the farthest possible index in the *next* step. This is because reaching farther gives us more options and potentially fewer overall jumps. We don't need to explore all possible jumps from the current position; we just need to know the maximum reach we can achieve from *any* position within our current jump's range.

## Algorithm
1. Initialize `jumps` to 0 (the number of jumps taken).
2. Initialize `currEnd` to 0 (the end of the current jump's reach).
3. Initialize `farthest` to 0 (the farthest index reachable from any position within the current jump).
4. Iterate through the array from index `i = 0` up to `nums.length - 2` (we don't need to consider the last element itself, as we've already reached it).
5. In each iteration, update `farthest` to be the maximum of its current value and `i + nums[i]` (the farthest we can reach from the current position `i`).
6. If the current index `i` reaches the end of the current jump's reach (`i == currEnd`):
    a. Increment `jumps` because we are forced to make a new jump.
    b. Update `currEnd` to `farthest`. This sets the boundary for our next jump.
7. After the loop finishes, return `jumps`.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Breadth-First Search (BFS) Analogy:** The problem can be viewed as a BFS where each "level" represents a jump. We explore all reachable nodes (indices) within a jump and then move to the next level.
*   **Range Management:** Efficiently tracking the maximum reachable index within the current jump's scope.

## Common Mistakes
*   **Incorrect Loop Boundary:** Iterating up to `nums.length - 1` instead of `nums.length - 2`, which might lead to an unnecessary jump count if the last element is reached exactly at `currEnd`.
*   **Not Updating `currEnd` Correctly:** Failing to update `currEnd` to `farthest` when `i == currEnd`, which breaks the logic of defining the next jump's boundary.
*   **Overthinking with DP/Recursion:** Trying to use dynamic programming or recursion for every possible jump, which is less efficient than the greedy approach for this specific problem.
*   **Off-by-one errors:** Miscalculating `i + nums[i]` or the conditions for updating jumps.

## Complexity Analysis
*   Time: O(n) - The code iterates through the array once.
*   Space: O(1) - Only a few variables are used, regardless of the input size.

## Commented Code
```java
class Solution {
    public int jump(int[] nums) { // Define the method to find the minimum jumps.

        int jumps = 0; // Initialize the count of jumps taken to 0.
        int currEnd = 0; // Initialize the end of the current jump's reach to index 0.
        int farthest = 0; // Initialize the farthest index reachable from any position within the current jump to 0.

        // Iterate through the array. We stop at nums.length - 1 because if we reach this index, we are done.
        // The loop condition i < nums.length - 1 ensures we don't process the last element itself,
        // as we only care about reaching it.
        for(int i = 0; i < nums.length - 1; i++) {

            // Update the farthest reachable index from the current position 'i'.
            // 'i + nums[i]' is the maximum index we can reach by jumping from 'i'.
            farthest = Math.max(farthest, i + nums[i]);

            // If the current index 'i' has reached the end of the current jump's reach ('currEnd').
            // This means we must make a new jump to continue moving forward.
            if(i == currEnd) {
                jumps++; // Increment the jump count.
                currEnd = farthest; // Set the end of the next jump to the farthest reachable point found so far.
                // If currEnd reaches or exceeds the last index, we can potentially break early,
                // but the loop condition i < nums.length - 1 handles this implicitly.
            }
        }

        return jumps; // Return the total number of jumps required.
    }
}
```

## Interview Tips
*   Explain the greedy approach clearly: Emphasize why always aiming for the farthest reach is optimal.
*   Walk through an example: Use a small array like `[2,3,1,1,4]` and trace the values of `jumps`, `currEnd`, and `farthest` at each step.
*   Discuss the BFS analogy: This can help illustrate the level-by-level exploration concept.
*   Be prepared to discuss why a DP approach might be less efficient here.

## Revision Checklist
- [ ] Understand the problem statement: minimum jumps to reach the end.
- [ ] Grasp the greedy strategy: maximize reach at each step.
- [ ] Trace the algorithm with examples.
- [ ] Analyze time and space complexity.
- [ ] Identify common pitfalls (loop bounds, `currEnd` update).
- [ ] Practice explaining the intuition and algorithm.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Minimum Number of Refueling Stops (LeetCode 871)
*   Gas Station (LeetCode 134)

## Tags
`Array` `Greedy` `Dynamic Programming`
