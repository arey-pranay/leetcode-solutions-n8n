# Frog Jump

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(N*K)  
**Space:** O(N*K)

---

## Solution (java)

```java
class Solution {
  HashMap<String,Boolean> memo = new HashMap<>();
  HashSet<Integer> set = new HashSet<>();
  int last;
  public boolean canCross(int[] stones) {
    for(int stone : stones) set.add(stone);
    last=stones[stones.length-1];
    return func(1,1); // from i=0, we can only go k=1 as per constraints and practicality, so let's start from i=1 and k=1
  }
  public boolean func(int i, int k){
    if(i==last) return true; //reached
    if(!set.contains(i)) return false; //stone does not exist there
    
    String key = i+","+k; // for memoizing, because n+1 is out of integer bounds so could not use indexing for memoizing
    if(memo.containsKey(key)) return memo.get(key);
    
    boolean ans = k>1 ? func(i+k-1,k-1) : false; // if k=0 or k=1 then there is no point going back or staying at the same point, we skip it
    ans |= func(i+k,k) || func(i+k+1,k+1);
    memo.put(key,ans);
    return ans;
  }
}
// 0 -> 1
// 1 k=1

```

---

---
## Quick Revision
The problem asks if a frog can reach the last stone starting from the first stone, with jump lengths restricted by the previous jump.
This is solved using a recursive approach with memoization (dynamic programming) to avoid redundant calculations.

## Intuition
The core idea is to explore all possible valid jumps from the current stone. A jump is valid if it lands on an existing stone and its length is `k-1`, `k`, or `k+1`, where `k` was the length of the previous jump. Since the number of stones can be large and the jump lengths can also grow, a simple brute-force recursion would be too slow due to repeated computations for the same (current stone position, previous jump length) states. This suggests using memoization to store and reuse results for these states.

## Algorithm
1. Initialize a `HashSet` called `set` to store all stone positions for efficient lookups.
2. Store the position of the last stone in a variable `last`.
3. Define a recursive helper function `func(current_stone_pos, previous_jump_length)`:
    a. **Base Case 1:** If `current_stone_pos` is equal to `last`, return `true` (the frog has reached the end).
    b. **Base Case 2:** If `current_stone_pos` is not present in the `set` of stones, return `false` (the frog landed on an invalid position).
    c. **Memoization Check:** Create a unique key (e.g., a string "current_stone_pos,previous_jump_length") and check if it exists in a `HashMap` called `memo`. If it does, return the stored boolean value.
    d. **Recursive Steps:** Calculate the possible next jump lengths: `previous_jump_length - 1`, `previous_jump_length`, and `previous_jump_length + 1`.
    e. For each possible next jump length `next_k`:
        i. If `next_k > 0` (jump length must be positive):
            - Recursively call `func(current_stone_pos + next_k, next_k)`.
            - If any of these recursive calls return `true`, then the frog can reach the end.
    f. **Store Result:** Store the computed boolean result for the current state (key) in the `memo` `HashMap`.
    g. Return the computed result.
4. The initial call to the recursive function should be `func(1, 1)`, assuming the frog starts at stone 0 and the first jump must be of length 1 to reach stone 1 (as per problem constraints and common interpretation).

## Concept to Remember
*   **Recursion with Memoization (Top-Down Dynamic Programming):** Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **Hash Sets for Efficient Lookups:** Using `HashSet` for O(1) average time complexity to check for the existence of stone positions.
*   **State Representation for DP:** Identifying the crucial parameters that define a unique subproblem (here, current stone position and previous jump length).

## Common Mistakes
*   **Not handling the first jump correctly:** The problem implies the first jump from stone 0 must be of length 1 to reach stone 1. Forgetting this initial constraint can lead to incorrect results.
*   **Infinite Recursion:** Not having proper base cases or not checking for invalid stone positions can lead to infinite recursive calls.
*   **Inefficient State Representation for Memoization:** Using a 2D array for memoization might be problematic if stone positions or jump lengths become very large, exceeding array bounds. A `HashMap` with a string key is a more flexible approach.
*   **Incorrectly calculating next jump lengths:** Missing the `k-1`, `k`, `k+1` logic or not ensuring `next_k > 0`.

## Complexity Analysis
*   **Time:** O(N*K), where N is the number of stones and K is the maximum possible jump length. In the worst case, K can be proportional to N. Each state `(stone_pos, jump_len)` is computed at most once due to memoization. The number of possible stone positions is N, and the maximum jump length can grow up to N.
*   **Space:** O(N*K) for the memoization `HashMap` and O(N) for the `HashSet`. In the worst case, the space complexity is dominated by the memoization table.

## Commented Code
```java
import java.util.HashMap;
import java.util.HashSet;

class Solution {
  // HashMap to store results of subproblems (memoization).
  // Key: "current_stone_pos,previous_jump_length", Value: boolean indicating if the end can be reached.
  HashMap<String, Boolean> memo = new HashMap<>();
  // HashSet to store all stone positions for O(1) average time lookup.
  HashSet<Integer> set = new HashSet<>();
  // Stores the position of the last stone.
  int last;

  public boolean canCross(int[] stones) {
    // Populate the HashSet with all stone positions.
    for (int stone : stones) {
      set.add(stone);
    }
    // Store the position of the last stone for the base case.
    last = stones[stones.length - 1];

    // Start the recursive process.
    // The frog is at stone 0, and the first jump must be of length 1 to reach stone 1.
    // So, we consider the state as if we just landed on stone 1 with a previous jump of 1.
    return func(1, 1);
  }

  // Recursive helper function to determine if the frog can reach the last stone.
  // i: current position of the frog.
  // k: the length of the previous jump.
  public boolean func(int i, int k) {
    // Base Case 1: If the frog has reached the last stone, return true.
    if (i == last) {
      return true;
    }
    // Base Case 2: If the current position is not a valid stone, return false.
    if (!set.contains(i)) {
      return false;
    }

    // Create a unique key for memoization based on current position and previous jump length.
    String key = i + "," + k;
    // Check if the result for this state has already been computed and stored.
    if (memo.containsKey(key)) {
      // If yes, return the stored result to avoid recomputation.
      return memo.get(key);
    }

    // Calculate possible next jump lengths: k-1, k, k+1.
    // The frog can jump k-1, k, or k+1 steps from the current stone.
    // We need to check if these jumps are valid (i.e., land on an existing stone).

    // Initialize the answer to false.
    boolean ans = false;

    // Check jump of k-1 steps. This is only possible if k > 1 (to ensure positive jump length).
    // If k=1, k-1=0, which is not a valid jump.
    if (k > 1) {
      // Recursively call func for the next position (i + k - 1) with the new jump length (k - 1).
      ans |= func(i + k - 1, k - 1);
    }

    // Check jump of k steps.
    // Recursively call func for the next position (i + k) with the new jump length (k).
    ans |= func(i + k, k);

    // Check jump of k+1 steps.
    // Recursively call func for the next position (i + k + 1) with the new jump length (k + 1).
    ans |= func(i + k + 1, k + 1);

    // Store the computed result for the current state in the memoization map.
    memo.put(key, ans);
    // Return the computed result.
    return ans;
  }
}
```

## Interview Tips
*   Clearly explain the recursive structure and the need for memoization.
*   Walk through a small example (e.g., `stones = [0, 1, 3, 5, 6, 8, 12, 17]`) to illustrate the jumps and how memoization helps.
*   Discuss the state definition for DP and why `(current_position, previous_jump_length)` is sufficient.
*   Be prepared to discuss the time and space complexity trade-offs.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the recursive structure of the problem.
- [ ] Recognize overlapping subproblems and the need for memoization.
- [ ] Implement the base cases for recursion.
- [ ] Correctly define the state for memoization.
- [ ] Implement the transitions for possible jumps (k-1, k, k+1).
- [ ] Handle the initial jump condition (from 0 to 1).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Jump Game
*   Jump Game II
*   Minimum Path Sum
*   Unique Paths

## Tags
`Array` `Dynamic Programming` `Depth-First Search` `Recursion` `Memoization`

## My Notes
memoized not possible iwth array due to out of bound, and function call stack overflow check nicely needed
