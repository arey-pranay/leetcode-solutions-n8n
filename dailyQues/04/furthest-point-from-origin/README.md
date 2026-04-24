# Furthest Point From Origin

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `String` `Counting`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int blanks = 0; int moved = 0;
        for(char c : moves.toCharArray()){
            if(c == 'L') moved++;
            else if (c == 'R') moved--;
            else blanks++;
        }
        return Math.abs(moved) + blanks;
    }
}
```

---

---
## Quick Revision
The problem asks for the maximum distance from the origin after a series of moves ('L', 'R', 'U', 'D').
We can solve this by tracking the net displacement along the x-axis and using any 'U' or 'D' moves to maximize this displacement.

## Intuition
The core idea is that 'L' and 'R' moves directly affect our position along the x-axis. 'U' and 'D' moves, however, don't change our x-position. This means we can use them strategically. To maximize our distance from the origin, we want to make our final x-coordinate as far from zero as possible. If we have a net displacement from 'L' and 'R' moves, say `x`, we can use all the 'U' and 'D' moves (which are represented by 'blanks' in the problem statement, though the provided solution uses 'blanks' for 'U'/'D' which is a bit confusing, let's assume 'blanks' means 'U' or 'D' for this analysis) to further increase this distance. For example, if our net 'L'/'R' movement is +5, and we have 3 'U'/'D' moves, we can treat each 'U'/'D' move as an additional 'R' move to reach a total displacement of 5 + 3 = 8. Conversely, if our net movement is -5, we can treat each 'U'/'D' move as an additional 'L' move to reach a total displacement of -5 - 3 = -8. The absolute value of this final displacement is our answer.

## Algorithm
1. Initialize two integer variables: `net_x_displacement` to 0 and `flexible_moves` to 0.
2. Iterate through each character in the input `moves` string.
3. If the character is 'L', increment `net_x_displacement`.
4. If the character is 'R', decrement `net_x_displacement`.
5. If the character is neither 'L' nor 'R' (which in this problem context implies 'U' or 'D'), increment `flexible_moves`.
6. After iterating through all characters, calculate the maximum possible distance by taking the absolute value of `net_x_displacement` and adding `flexible_moves` to it.
7. Return this calculated maximum distance.

## Concept to Remember
*   **Absolute Value:** Understanding how absolute value represents distance from zero.
*   **Greedy Approach:** Making locally optimal choices (using flexible moves to extend current displacement) to achieve a globally optimal solution.
*   **Coordinate Systems:** Implicit understanding of movement on a 2D plane, though only one axis is directly tracked.

## Common Mistakes
*   **Misinterpreting 'U'/'D' moves:** Assuming 'U' and 'D' moves cancel each other out or have no impact, rather than realizing they can be used to augment the primary directional movement.
*   **Forgetting Absolute Value:** Calculating `net_x_displacement + flexible_moves` without considering the sign of `net_x_displacement`, leading to incorrect results for negative displacements.
*   **Off-by-one errors:** Incorrectly counting or applying the flexible moves.
*   **Not handling all move types:** If the problem had more move types, failing to account for them.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the input string `moves` once, where N is the length of the string.
- Space: O(1) - reason: We only use a few constant extra variables (`blanks`, `moved`) regardless of the input size.

## Commented Code
```java
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        // Initialize a counter for moves that don't directly affect the x-axis (like 'U' or 'D').
        // In the provided solution, 'blanks' is used for these flexible moves.
        int blanks = 0;
        // Initialize a variable to track the net displacement along the x-axis.
        // 'L' increases the displacement (moves right), 'R' decreases it (moves left).
        int moved = 0;

        // Iterate over each character in the input string 'moves'.
        for(char c : moves.toCharArray()){
            // If the character is 'L', it means a move to the left.
            // We can think of this as increasing the magnitude of displacement from origin.
            // The provided solution uses 'moved++' for 'L', which implies 'L' is a positive contribution to distance.
            // Let's assume 'L' contributes positively to a 'moved' counter that will be absolute-valued later.
            if(c == 'L') moved++;
            // If the character is 'R', it means a move to the right.
            // This counteracts the 'L' moves.
            // The provided solution uses 'moved--' for 'R', meaning 'R' is a negative contribution.
            else if (c == 'R') moved--;
            // If the character is neither 'L' nor 'R', it's a flexible move ('U' or 'D').
            // These moves can be used to extend the distance from the origin.
            else blanks++;
        }
        // The maximum distance is achieved by taking the absolute net displacement from 'L'/'R' moves
        // and adding all the flexible moves ('blanks') to it.
        // For example, if moved is +5 and blanks is 3, we can treat blanks as 3 more 'L' moves, resulting in 8.
        // If moved is -5 and blanks is 3, we can treat blanks as 3 more 'R' moves, resulting in -8.
        // Math.abs(moved) gives the magnitude of the primary displacement.
        return Math.abs(moved) + blanks;
    }
}
```

## Interview Tips
*   **Clarify Ambiguities:** If the problem statement uses terms like "blanks" for 'U'/'D' moves, ask for clarification to ensure you understand their role.
*   **Explain the Greedy Choice:** Clearly articulate why using all flexible moves to augment the existing displacement is the optimal strategy.
*   **Walk Through Examples:** Use a simple example like "RLLU" to demonstrate how your logic works step-by-step.
*   **Consider Edge Cases:** Think about strings with only 'L's, only 'R's, only 'U'/'D's, or an empty string.

## Revision Checklist
- [ ] Understand the problem: Maximize distance from origin.
- [ ] Identify 'L'/'R' impact: Direct x-axis displacement.
- [ ] Identify 'U'/'D' impact: Flexible moves to augment displacement.
- [ ] Implement tracking of net 'L'/'R' displacement.
- [ ] Implement tracking of flexible moves.
- [ ] Combine displacements using absolute value.
- [ ] Test with examples: "RLLU", "UUUDDDLR", "LLRR".

## Similar Problems
*   [1647. Minimum Deletions to Make Character Frequencies Unique](https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/) (Conceptually related to maximizing/minimizing based on counts)
*   [1703. Minimum Adjacent Swaps for K Consecutive Ones](https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/) (Involves optimizing positions based on constraints)

## Tags
`String` `Math`
