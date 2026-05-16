# Jump Game

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(2^n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        return canReach(nums,n-1);
    }
    public boolean canReach(int[] nums, int e){
        if(e==0) return true;
        for(int i = e-1; i>=0;i--){
            if(i+nums[i]>=e) return canReach(nums,i);
        }
        return false;
    }
}
```

---

---
## Quick Revision
Given an array of non-negative integers, determine if you can reach the last index starting from the first index.
This problem can be solved using a greedy approach by tracking the farthest reachable index.

## Intuition
The core idea is to always try to reach as far as possible. If at any point, the current position plus the jump value from that position allows us to reach or surpass the last index, we know it's possible. Conversely, if we iterate through all possible previous positions and none of them can reach the current position, then it's impossible. The recursive solution explores this by checking if the end can be reached from any preceding index that can jump to it.

## Algorithm
1. **Base Case:** If the target index `e` is 0, we are already at the start, so return `true`.
2. **Iterate Backwards:** Loop from `e-1` down to 0.
3. **Check Reachability:** For each index `i`, check if `i + nums[i]` is greater than or equal to `e`. This means index `i` can reach or surpass the target index `e`.
4. **Recursive Call:** If index `i` can reach `e`, recursively call `canReach` for index `i`. If this recursive call returns `true`, it means we can reach the start from index `i`, and thus we can reach `e`. Return `true`.
5. **Impossible:** If the loop finishes without finding any index `i` that can reach `e` and subsequently lead back to the start, return `false`.
6. **Initial Call:** The `canJump` function initiates the process by calling `canReach` with the last index (`n-1`) as the target.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Backtracking:** Exploring possibilities and reverting if a path doesn't lead to a solution.
*   **Greedy Approach (Implicit):** While the provided solution is recursive, the underlying principle of checking if *any* preceding step can reach the current goal hints at a greedy strategy of finding *a* valid path.

## Common Mistakes
*   **Infinite Recursion:** Not having a proper base case or not reducing the problem size in recursive calls.
*   **Off-by-One Errors:** Incorrectly handling array indices or loop boundaries.
*   **Inefficient Recursion:** The provided recursive solution can be very inefficient due to repeated calculations of the same subproblems (overlapping subproblems), leading to exponential time complexity without memoization.
*   **Not Considering All Paths:** Forgetting to check all possible preceding indices that could reach the current target.

## Complexity Analysis
*   Time: O(2^n) - In the worst case, the recursive function `canReach` might explore many overlapping subproblems, leading to an exponential time complexity. For example, if `nums = [1, 1, 1, ..., 1]`, each call might branch out.
*   Space: O(n) - Due to the recursion depth. In the worst case, the call stack can go as deep as the length of the array.

## Commented Code
```java
class Solution {
    // Main function to determine if the last index can be reached.
    public boolean canJump(int[] nums) {
        // Get the length of the array.
        int n = nums.length;
        // Call the helper function to check if the last index (n-1) can be reached from the start.
        return canReach(nums, n - 1);
    }

    // Recursive helper function to check if index 'e' can be reached from the start.
    public boolean canReach(int[] nums, int e) {
        // Base case: If the target index is 0, we are already at the start, so it's reachable.
        if (e == 0) return true;
        // Iterate backwards from the index before the target 'e' down to the start (index 0).
        for (int i = e - 1; i >= 0; i--) {
            // Check if the current index 'i' can jump to or beyond the target index 'e'.
            if (i + nums[i] >= e) {
                // If index 'i' can reach 'e', recursively check if 'e' can be reached from index 'i'.
                // If canReach(nums, i) returns true, it means we found a path from the start to 'i', and then from 'i' to 'e'.
                return canReach(nums, i);
            }
        }
        // If the loop completes without finding any index 'i' that can reach 'e' and lead back to the start, then 'e' is not reachable.
        return false;
    }
}
```

## Interview Tips
*   **Discuss the Recursive Solution First:** Explain the logic of the recursive approach, including the base case and the recursive step.
*   **Identify Inefficiency:** Point out the overlapping subproblems and the potential for exponential time complexity.
*   **Propose Optimization (Greedy):** Suggest a more efficient greedy approach. The optimal greedy solution iterates forward, keeping track of the farthest reachable index. If the current index ever exceeds this farthest reachable index, it's impossible.
*   **Clarify Constraints:** Ask about the constraints on the array size and values. This can help determine if the recursive solution is acceptable or if optimization is mandatory.

## Revision Checklist
- [ ] Understand the problem statement: can we reach the last index?
- [ ] Trace the recursive logic with a small example.
- [ ] Identify the base case and recursive step.
- [ ] Analyze the time and space complexity of the recursive solution.
- [ ] Consider alternative, more efficient approaches (like greedy).

## Similar Problems
Jump Game II
Jump Game III
Jump Game IV
Minimum Jumps to Reach Home

## Tags
`Array` `Dynamic Programming` `Recursion` `Greedy`
