# Even Number Of Knight Moves

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Logic` `Chess`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean canReach(int[] s, int[] t) {
        return  ( (s[0] + s[1])%2 == (t[0] + t[1]) % 2 );
    }
    // if target and start are of the same color, then possible in even moves
}
// / even (x+y) sum wale black hai
```

---

---
## Quick Revision
A knight can reach a target square if and only if both squares have the same color. This is because each knight move always changes the color of the square.
## Intuition
The core idea is that a knight always moves from a square of one color to a square of the opposite color. This means that after an even number of moves, the knight will always end up on a square of the same color as its starting square. Conversely, after an odd number of moves, it will end up on a square of the opposite color. The problem statement implies we are looking for *any* path, and specifically, if a path of *even* length exists. If the start and target squares have the same color, it's possible to reach the target in an even number of moves. If they have different colors, it's impossible to reach the target in an even number of moves.

To determine the "color" of a square (x, y) on a chessboard, we can use the parity of the sum of its coordinates (x + y). If (x + y) is even, the square is one color (e.g., white). If (x + y) is odd, the square is the other color (e.g., black). Therefore, the condition for reaching the target in an even number of moves is that the parity of (s[0] + s[1]) must be the same as the parity of (t[0] + t[1]).

## Algorithm
1. Calculate the sum of the coordinates for the starting position: `startSum = s[0] + s[1]`.
2. Calculate the sum of the coordinates for the target position: `targetSum = t[0] + t[1]`.
3. Check if the parity of `startSum` is the same as the parity of `targetSum`. This can be done by checking if `startSum % 2 == targetSum % 2`.
4. Return `true` if the parities are the same, and `false` otherwise.

## Concept to Remember
*   **Chessboard Coloring:** The alternating color pattern of a chessboard.
*   **Parity:** The property of an integer being even or odd.
*   **Knight's Move Properties:** How a knight's move affects its position and the "color" of the square it lands on.
*   **Modular Arithmetic:** Using the modulo operator (%) to determine parity.

## Common Mistakes
*   **Overcomplicating the solution:** Trying to implement a full BFS or DFS when a simple parity check is sufficient.
*   **Misunderstanding the problem:** Assuming the problem requires finding the *shortest* path or *any* path, rather than specifically an *even* number of moves.
*   **Incorrect parity check:** Errors in calculating or comparing the parities of the coordinate sums.
*   **Ignoring edge cases:** While this problem has a very simple solution, in more complex variations, edge cases might be missed.

## Complexity Analysis
*   **Time:** O(1) - The solution involves a few arithmetic operations and comparisons, which take constant time regardless of the input values.
*   **Space:** O(1) - No extra data structures are used that grow with the input size.

## Commented Code
```java
class Solution {
    // This method determines if a knight can reach the target square from the start square
    // in an even number of moves.
    public boolean canReach(int[] s, int[] t) {
        // The core logic relies on the fact that a knight always moves between squares of opposite colors.
        // Therefore, to reach a square in an even number of moves, the start and target squares must be of the same color.
        // We can determine the "color" of a square (x, y) by the parity of (x + y).
        // If (x + y) is even, it's one color; if odd, it's the other.

        // Calculate the sum of coordinates for the starting position.
        int startSum = s[0] + s[1];
        // Calculate the sum of coordinates for the target position.
        int targetSum = t[0] + t[1];

        // Check if the parity of the start sum is the same as the parity of the target sum.
        // If both sums have the same remainder when divided by 2 (i.e., both even or both odd),
        // then they are of the same "color" and reachable in an even number of moves.
        return (startSum % 2 == targetSum % 2);
    }
    // The comment "// if target and start are of the same color, then possible in even moves"
    // and "// / even (x+y) sum wale black hai" are informal notes explaining the logic.
}
```

## Interview Tips
*   **Explain the parity concept clearly:** Articulate why the sum of coordinates determines the "color" and how knight moves preserve or change this parity.
*   **State the O(1) complexity upfront:** Recognize that this problem has a very efficient, constant-time solution.
*   **Ask clarifying questions:** If unsure about the problem constraints or if "even number of moves" is the *only* condition to check, ask. For this problem, it's implied.
*   **Discuss alternative (but less efficient) approaches:** Briefly mention how BFS/DFS *could* solve it but highlight why the parity check is superior.

## Revision Checklist
- [ ] Understand the knight's movement on a chessboard.
- [ ] Recognize the color-changing property of each knight move.
- [ ] Connect coordinate sum parity to square color.
- [ ] Implement the parity comparison correctly.
- [ ] Verify O(1) time and space complexity.

## Similar Problems
*   Knight Dialer
*   Minimum Knight Moves
*   Shortest Path in a Grid with Obstacles Elimination

## Tags
`Math` `Logic` `Chess`
