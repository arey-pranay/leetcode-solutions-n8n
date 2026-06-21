# Maximum Manhattan Distance After All Moves

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Math` `Greedy`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxDistance(String moves) {
       int us = 0;
       int ds = 0;
       int ls = 0;
       int rs = 0;
       int bs = 0;
       for(char c : moves.toCharArray()){
            if(c=='U') us++;
            else if(c=='D') ds++;
            else if(c=='L') ls++;
            else if(c=='R') rs++;
            else bs++;
       } 
       return Math.abs(us-ds)+Math.abs(ls-rs)+bs;
    }
}
```

---

---
## Quick Revision
This problem asks for the maximum Manhattan distance from the origin after a sequence of moves, where some moves are wildcards.
We solve this by maximizing the displacement in each axis independently using the wildcards.

## Intuition
The Manhattan distance is the sum of the absolute differences in x and y coordinates. We want to maximize this sum. Each 'U'/'D' move affects the y-coordinate, and each 'L'/'R' move affects the x-coordinate. The wildcard '_' can be used as either 'U' or 'D' to maximize the y-displacement, or as 'L' or 'R' to maximize the x-displacement.

Consider the net displacement along the y-axis. If we have `us` 'U' moves and `ds` 'D' moves, the net displacement is `us - ds`. To maximize the absolute displacement, we want to make this difference as large as possible. The `bs` (blank/wildcard) moves can be used to further increase this displacement. If `us > ds`, we can use all `bs` as 'U' moves to get `us + bs - ds`. If `ds > us`, we can use all `bs` as 'D' moves to get `us - (ds + bs)`. In both cases, the maximum absolute displacement is `abs(us - ds) + bs`. The same logic applies independently to the x-axis.

However, a crucial observation is that the wildcards can be used to maximize *either* the x-displacement *or* the y-displacement, but not both simultaneously with the same wildcard. If we use a wildcard as 'U', it contributes to the y-displacement. If we use it as 'L', it contributes to the x-displacement.

Let's re-evaluate. The problem states "after all moves". This implies we can assign each wildcard to *any* direction ('U', 'D', 'L', 'R') to maximize the final Manhattan distance.
The Manhattan distance is `|x| + |y|`.
The net vertical displacement from 'U' and 'D' moves is `us - ds`.
The net horizontal displacement from 'L' and 'R' moves is `ls - rs`.
We have `bs` wildcard moves.

To maximize `|x| + |y|`, we want to maximize `|net_x_displacement|` and `|net_y_displacement|`.
The `bs` moves can be used to increase the magnitude of either the x or y displacement.
If we use `k` wildcards for vertical movement and `bs - k` for horizontal movement.
The vertical displacement will be `us - ds + k` or `us - ds - k` (depending on whether we use them as 'U' or 'D'). To maximize the absolute value, we'd use them to push away from the origin. So, if `us > ds`, we'd use `k` as 'U's, resulting in `us + k - ds`. If `ds > us`, we'd use `k` as 'D's, resulting in `us - (ds + k)`. In general, the maximum absolute vertical displacement using `k` wildcards is `abs(us - ds) + k`.
Similarly, the maximum absolute horizontal displacement using `bs - k` wildcards is `abs(ls - rs) + (bs - k)`.

The total Manhattan distance would be `abs(us - ds) + k + abs(ls - rs) + (bs - k)`.
Notice that `k` cancels out: `abs(us - ds) + abs(ls - rs) + bs`.
This means we can distribute the `bs` moves arbitrarily between horizontal and vertical to maximize the total Manhattan distance. The optimal strategy is to use all `bs` moves to maximize the displacement in *one* direction (either horizontal or vertical) that already has a larger net displacement, or to balance them if they are equal. However, the formula `abs(us - ds) + abs(ls - rs) + bs` implicitly covers this by allowing the `bs` to contribute to the *total* absolute displacement.

Let's verify with an example: `moves = "UUDDL"`
`us = 2`, `ds = 2`, `ls = 1`, `rs = 0`, `bs = 0`.
Result: `abs(2-2) + abs(1-0) + 0 = 0 + 1 + 0 = 1`. Correct.

Example: `moves = "UU_D"`
`us = 2`, `ds = 1`, `ls = 0`, `rs = 0`, `bs = 1`.
Formula: `abs(2-1) + abs(0-0) + 1 = 1 + 0 + 1 = 2`.
Let's check:
If `_` is 'U': `UUUD`. Net: `3U, 1D`. Final pos: (0, 2). Distance: `|0| + |2| = 2`.
If `_` is 'D': `UUDD`. Net: `2U, 2D`. Final pos: (0, 0). Distance: `|0| + |0| = 0`.
If `_` is 'L': `UUDL`. Net: `2U, 1D, 1L`. Final pos: (-1, 1). Distance: `|-1| + |1| = 2`.
If `_` is 'R': `UUDR`. Net: `2U, 1D, 1R`. Final pos: (1, 1). Distance: `|1| + |1| = 2`.
Maximum is 2. The formula works.

Example: `moves = "____"`
`us = 0`, `ds = 0`, `ls = 0`, `rs = 0`, `bs = 4`.
Formula: `abs(0-0) + abs(0-0) + 4 = 0 + 0 + 4 = 4`.
To maximize distance, we can make all 4 moves in the same direction, e.g., 'U'. Final pos: (0, 4). Distance: 4.
Or 'L'. Final pos: (-4, 0). Distance: 4.
The formula `abs(us-ds) + abs(ls-rs) + bs` correctly captures the maximum possible displacement by allowing the wildcards to contribute to the net displacement in either axis.

## Algorithm
1. Initialize counters for 'U' moves (`us`), 'D' moves (`ds`), 'L' moves (`ls`), 'R' moves (`rs`), and wildcard moves (`bs`) to zero.
2. Iterate through each character in the input `moves` string.
3. For each character:
    - If it's 'U', increment `us`.
    - If it's 'D', increment `ds`.
    - If it's 'L', increment `ls`.
    - If it's 'R', increment `rs`.
    - If it's '_', increment `bs`.
4. Calculate the maximum vertical displacement: `abs(us - ds)`.
5. Calculate the maximum horizontal displacement: `abs(ls - rs)`.
6. The total maximum Manhattan distance is the sum of the maximum vertical displacement, the maximum horizontal displacement, and the number of wildcard moves (`bs`). This is because each wildcard can be used to increase the magnitude of displacement along either the x or y axis.
7. Return `abs(us - ds) + abs(ls - rs) + bs`.

## Concept to Remember
*   **Manhattan Distance:** Defined as `|x1 - x2| + |y1 - y2|`. In this problem, we are calculating the distance from the origin (0,0) to the final position, so it's `|final_x| + |final_y|`.
*   **Independent Axis Maximization:** The key insight is that the movement along the x-axis and y-axis are independent. Wildcard moves can be strategically assigned to maximize displacement in either axis.
*   **Greedy Approach:** The problem can be solved greedily by maximizing the displacement in each axis independently. The wildcards are used to augment the existing net displacement in a way that maximizes the absolute value.

## Common Mistakes
*   **Incorrectly distributing wildcards:** Assuming wildcards must be split evenly or in a fixed ratio between horizontal and vertical movement. The optimal strategy is to use them to maximize the *total* Manhattan distance, which means they can all contribute to augmenting the displacement in one axis if that's more beneficial.
*   **Not considering the absolute value:** Forgetting to take the absolute difference between 'U' and 'D' counts, or 'L' and 'R' counts, when calculating net displacement.
*   **Overlapping wildcard usage:** Thinking that a single wildcard can contribute to both horizontal and vertical displacement simultaneously. Each wildcard is a single move and can only be assigned to one direction.
*   **Off-by-one errors in counting:** Simple mistakes in incrementing counters for each character type.

## Complexity Analysis
*   Time: O(N) - reason: We iterate through the input string `moves` once to count the occurrences of each character. N is the length of the `moves` string.
*   Space: O(1) - reason: We only use a fixed number of integer variables to store the counts, regardless of the input string's length.

## Commented Code
```java
class Solution {
    public int maxDistance(String moves) {
       // Initialize counters for each type of move.
       int us = 0; // Count of 'U' (Up) moves
       int ds = 0; // Count of 'D' (Down) moves
       int ls = 0; // Count of 'L' (Left) moves
       int rs = 0; // Count of 'R' (Right) moves
       int bs = 0; // Count of '_' (blank/wildcard) moves

       // Iterate through each character in the moves string.
       for(char c : moves.toCharArray()){
            // Increment the corresponding counter based on the character.
            if(c=='U') us++; // If it's 'U', increment up count
            else if(c=='D') ds++; // If it's 'D', increment down count
            else if(c=='L') ls++; // If it's 'L', increment left count
            else if(c=='R') rs++; // If it's 'R', increment right count
            else bs++; // If it's '_', increment blank count
       }

       // Calculate the maximum possible displacement along the y-axis.
       // This is the absolute difference between 'U' and 'D' moves,
       // plus all the wildcard moves, as they can be used to augment
       // the vertical displacement.
       // For example, if us > ds, we can use all bs as 'U's to get us + bs - ds.
       // If ds > us, we can use all bs as 'D's to get us - (ds + bs).
       // In both cases, the maximum absolute displacement is abs(us - ds) + bs.
       // However, the problem asks for the sum of absolute displacements in x and y.
       // The key is that wildcards can be used to maximize EITHER x OR y displacement.
       // The total Manhattan distance is maximized by summing the maximum possible
       // displacement in each axis independently.
       // The net vertical displacement is us - ds. The maximum absolute vertical
       // displacement we can achieve is abs(us - ds) by using wildcards to push
       // further in the direction of the larger count.
       // Similarly, the maximum absolute horizontal displacement is abs(ls - rs).
       // The total Manhattan distance is the sum of these maximum independent
       // displacements. The wildcards `bs` can be distributed to maximize
       // the sum of these two absolute values. The formula `abs(us-ds) + abs(ls-rs) + bs`
       // correctly accounts for this. The `bs` can be used to increase either
       // `abs(us-ds)` or `abs(ls-rs)`. The total increase from `bs` is `bs`.
       // So, the total maximum distance is `abs(us-ds) + abs(ls-rs) + bs`.

       // Return the sum of the absolute net vertical displacement,
       // the absolute net horizontal displacement, and the number of wildcards.
       return Math.abs(us - ds) + Math.abs(ls - rs) + bs;
    }
}
```

## Interview Tips
*   **Clarify the Goal:** Ensure you understand that the goal is to maximize the Manhattan distance from the origin *after* all moves are made. This means we can strategically assign wildcards.
*   **Break Down the Problem:** Think about the x and y components of the Manhattan distance separately. How do 'U'/'D' affect y, and 'L'/'R' affect x?
*   **Explain Wildcard Strategy:** Clearly articulate how the wildcard moves ('_') can be used to maximize the displacement. Emphasize that they can be assigned to any direction ('U', 'D', 'L', 'R') to achieve the maximum possible distance. The key is that they can augment the displacement in either axis.
*   **Walk Through an Example:** Use a simple example like "U_D" or "R__L" to demonstrate your logic and how the formula is derived.

## Revision Checklist
- [ ] Understand the definition of Manhattan distance.
- [ ] Identify how each move type affects the coordinates.
- [ ] Recognize that x and y movements are independent.
- [ ] Determine the optimal strategy for using wildcard moves.
- [ ] Implement the counting and calculation logic correctly.
- [ ] Verify the formula `abs(us-ds) + abs(ls-rs) + bs`.

## Similar Problems
*   [LeetCode 1646. Get Maximum in Generated Array](https://leetcode.com/problems/get-maximum-in-generated-array/) (Different problem, but involves array generation and finding max)
*   [LeetCode 1703. Minimum Adjacent Swaps for K Consecutive Ones](https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/) (Involves movement and optimization, but different metric)
*   [LeetCode 1846. Maximum Element After Decrementing and Rearranging](https://leetcode.com/problems/maximum-element-after-decrementing-and-rearranging/) (Different problem, but involves array manipulation and maximization)
*   [LeetCode 1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/) (Graph traversal, but related to movement)

(Note: Direct "Maximum Manhattan Distance" problems with wildcards are less common. The core concept here is maximizing displacement with flexible moves, which appears in various forms.)

## Tags
`String` `Math` `Greedy`
