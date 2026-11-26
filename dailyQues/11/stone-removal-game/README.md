# Stone Removal Game

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Simulation`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        boolean aliceWin = false;
        while(n >= take){ n -= take; take--; aliceWin = !aliceWin; }
        return aliceWin;
    }
    // int k = (int) ((21 - Math.sqrt(441 - 8 * n)) / 2);
    // return k % 2 == 1;
}
```

---

---
## Quick Revision
Alice and Bob take turns removing stones in a decreasing sequence (10, 9, 8...), with Alice going first, and the last player to make a move wins.
Simulate the subtraction process round by round, tracking the turn state until stones are insufficient, or use a closed-form math solution.

## Intuition
The game is completely deterministic with no hidden information and a fixed sequence of moves. Alice removes 10, Bob removes 9, Alice 8, Bob 7, and so on. Since the number of stones taken per turn is strictly decreasing ($10, 9, 8, \dots, 1$), the game has a very short and bounded maximum number of turns (at most 10). We simply simulate each round: subtract the current stone count from $n$ and toggle the turn flag. The last person who successfully makes a move wins, which corresponds to the state of the turn flag when the loop terminates.

## Algorithm
1. Initialize the number of stones to take (`take`) to 10 and a boolean flag (`aliceWin`) to `false`.
2. Enter a `while` loop that continues as long as the remaining stones `n` is greater than or equal to `take`.
3. Inside the loop, subtract `take` from `n` (`n -= take`).
4. Decrement `take` by 1 for the next turn (`take--`).
5. Toggle the `aliceWin` flag (`aliceWin = !aliceWin`) to switch the active player.
6. Return `aliceWin` once the loop terminates, representing whether Alice made the last valid move.

## Concept to Remember
- Game Theory Simulation: Simple combinatorial games with deterministic outcomes can be solved via direct simulation when constraints are extremely small.
- State Toggling: Using boolean negation (`!`) is an efficient way to alternate turns between two players.
- Monotonically Decreasing Sequences: Recognizing when a sequence bounds the maximum operations to a constant or very small number.

## Common Mistakes
- Misinterpreting the starting stone count for the first turn (starting with 10 instead of decreasing correctly).
- Forgetting to decrement the `take` variable after each round.
- Returning the wrong player's status when the loop condition fails.
- Overcomplicating the solution with dynamic programming or minimax when a simple greedy simulation suffices.

## Complexity Analysis
- Time: O(1) - The number of stones taken decreases by 1 each turn starting from 10, meaning the loop runs at most 10 times regardless of $n$.
- Space: O(1) - Only a few primitive variables are used, requiring constant extra space.

## Commented Code
```java
class Solution {
    public boolean canAliceWin(int n) {
        // Initialize the first stone removal amount to 10 as per the game rules
        int take = 10;
        // Flag to track whether it is currently Alice's win state (starts with false)
        boolean aliceWin = false;
        // Continue the game as long as there are enough stones to take
        while(n >= take){ 
            // Subtract the current round's stone requirement from the total
            n -= take; 
            // Decrease the amount of stones to take for the next turn
            take--; 
            // Toggle the winner flag to switch between Alice and Bob
            aliceWin = !aliceWin; 
        }
        // Return the final state; true if Alice made the last valid move, false otherwise
        return aliceWin;
    }
}
```

## Interview Tips
- Clarify the rules: Confirm whether the game stops immediately when a player cannot make a valid move.
- Mention optimization: State that since the maximum turns are capped at 10, the time complexity is strictly $O(1)$.
- Discuss alternatives: Briefly mention that an algebraic/math approach using quadratic roots is also possible, but simulation is cleaner and less error-prone for small bounds.

## Revision Checklist
- [ ] Understand game constraints and turn alternation
- [ ] Implement simulation loop with correct decrementing step
- [ ] Verify base cases like $n < 10$
- [ ] Confirm time and space complexities are $O(1)$

## Similar Problems
- Nim Game
- Predict the Winner
- Divisor Game
- Stone Game

## Tags
`Math` `Simulation` `Game Theory`
