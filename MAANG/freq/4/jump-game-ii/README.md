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
      int jumps=0, currMax = 0, nextMax =0;
      int n = nums.length;
      for(int i=0;i<n-1;i++){
          int canGo = i+nums[i];
          nextMax = Math.max(nextMax,canGo); //2,4
          if(i==currMax){//yes
              jumps++; // abhi tk ka humara best jo tha wahan hum aagye, now we need another jump for going forward //1
              currMax=nextMax; //2
          }
      }
      return jumps;
    }
}




```

---

---
## Quick Revision
Given an array of non-negative integers, find the minimum number of jumps to reach the last index.
This problem can be solved using a greedy approach by keeping track of the farthest reachable index.

## Intuition
The core idea is to be as greedy as possible at each step. When we are at a certain position `i`, we can jump to any index from `i+1` to `i + nums[i]`. To minimize jumps, we want to make a jump that allows us to reach the farthest possible index in the *next* step. This suggests a greedy strategy: at each jump, we explore all reachable positions from our current jump's range and pick the one that offers the maximum reach for the *subsequent* jump. We continue this until we reach the end.

## Algorithm
1. Initialize `jumps` to 0 (number of jumps taken).
2. Initialize `currMax` to 0 (the farthest index reachable with the current number of jumps).
3. Initialize `nextMax` to 0 (the farthest index reachable with one more jump).
4. Iterate through the array from index `i = 0` to `n-2` (we don't need to jump from the last element).
5. In each iteration, update `nextMax` to be the maximum of its current value and `i + nums[i]` (the farthest we can reach from the current position `i`).
6. If the current index `i` reaches `currMax`, it means we have exhausted all possibilities for the current jump. Therefore, we increment `jumps` and update `currMax` to `nextMax` (setting the new boundary for the next jump).
7. After the loop finishes, `jumps` will hold the minimum number of jumps required to reach the last index.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a globally optimal solution.
*   **Breadth-First Search (BFS) Analogy:** This problem can be viewed as a BFS where each "level" represents a jump. `currMax` marks the end of the current level, and `nextMax` finds the farthest point in the next level.
*   **Range Expansion:** At each jump, we are essentially expanding our reachable range.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the loop termination condition (e.g., iterating up to `n` instead of `n-1`).
*   **Not updating `currMax` correctly:** Failing to update `currMax` to `nextMax` when a jump is made, leading to an incorrect count.
*   **Confusing `currMax` and `nextMax`:** Misunderstanding their roles in tracking the current and next jump's reach.
*   **Trying to find the exact jump path:** The problem only asks for the minimum number of jumps, not the sequence of jumps.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the array once.
*   Space: O(1) - The algorithm uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public int jump(int[] nums) {
      // Initialize the number of jumps taken to 0.
      int jumps = 0;
      // Initialize currMax to 0, representing the farthest index reachable with the current number of jumps.
      int currMax = 0;
      // Initialize nextMax to 0, representing the farthest index reachable with one more jump.
      int nextMax = 0;
      // Get the length of the input array.
      int n = nums.length;

      // Iterate through the array from the first element up to the second-to-last element.
      // We don't need to consider jumping from the last element itself.
      for (int i = 0; i < n - 1; i++) {
          // Calculate the farthest index reachable from the current position 'i'.
          int canGo = i + nums[i];
          // Update nextMax to be the maximum of its current value and the farthest we can reach from 'i'.
          // This keeps track of the best possible reach for the *next* jump.
          nextMax = Math.max(nextMax, canGo); // Example: if nums = [2,3,1,1,4], at i=0, nextMax becomes max(0, 0+2)=2. At i=1, nextMax becomes max(2, 1+3)=4.

          // If the current index 'i' has reached the boundary of the current jump's maximum reach (currMax).
          if (i == currMax) {
              // It means we have explored all positions reachable within the current jump.
              // So, we must take another jump. Increment the jump count.
              jumps++; // Example: When i reaches 0 (currMax), jumps becomes 1.
              // Update currMax to nextMax. This sets the new boundary for the next jump.
              // The next jump will aim to reach at least up to this new currMax.
              currMax = nextMax; // Example: currMax becomes 2 after the first jump.
          }
      }
      // After the loop, 'jumps' will contain the minimum number of jumps required to reach the last index.
      return jumps;
    }
}
```

## Interview Tips
*   Explain the greedy choice: Clearly articulate why choosing the maximum reach at each step is optimal.
*   Walk through an example: Use a small array like `[2,3,1,1,4]` to trace the `currMax`, `nextMax`, and `jumps` variables.
*   Discuss the BFS analogy: Mentioning how this problem can be thought of as a level-order traversal can demonstrate a deeper understanding.
*   Handle edge cases: Briefly consider what happens with an array of size 1 (should return 0 jumps).

## Revision Checklist
- [ ] Understand the problem statement: minimum jumps to reach the end.
- [ ] Identify the greedy strategy: maximize reach at each jump.
- [ ] Implement the `currMax` and `nextMax` logic correctly.
- [ ] Ensure the loop terminates at `n-1`.
- [ ] Verify the jump increment condition (`i == currMax`).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Minimum Number of Refueling Stops (LeetCode 871)

## Tags
`Array` `Greedy` `Dynamic Programming`
