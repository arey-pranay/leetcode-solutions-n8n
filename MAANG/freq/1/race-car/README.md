# Race Car

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Heuristic Search` `A* Search`  
**Time:** O(t \log t)  
**Space:** O(t)

---

## Solution (java)

```java
class Solution {
    HashMap<Integer,Integer> memo = new HashMap<>();
    public int racecar(int t) {
        if(t==0) return 0;
        if(memo.containsKey(t)) return memo.get(t);
        int n = (int) (Math.log(t)/Math.log(2)) +1; //
        // int p=0, s= 1;
        int exact = (1 << n) -1; 
        if(exact == t) {memo.put(t,n);return n;} // for t = 17 se 31, sbke liye exact is 31
        int min = racecar(exact-t);// we exceeded, so we will solve for diff. and add rev later
        int prevStop = (1<<(n-1));     //(2 ^ (n-1))   
        for(int m=0;m<n-1;m++){ // m represents humne reverse krke, kitni baar acccerate kiya
            int revStep = 1<<m; //hum itna reverse me accelerate krege, to t se aur door jaate jayege
            int curr = racecar(t-prevStop + revStep); // total dist itna hua: jitna difference hai, usme jitna aur reverse krdiya
            int total = curr + m;  //  m baar neg acc, plus us position ka answer
            min = Math.min(min,total);
        }
        memo.put(t,1 + n + min);
        return 1 + n + min;  // n ops till here, then 1 rev
    }
}
// n + 1 + racecar(diff)

// 17
// AAAA
// p=15 s=16 
// 1 2 -> A
// 3 4 -> A
// 7 8 -> A
// 15 16 -> A
// 31 32 -> R 
// 31 -1 -> A
// 30 -2 -> A
// 28 -4 -> A
// 24 -8 -> A
// 16 -16 -> R
// 16 1   -> A
// 17 2   -------

// 15 -1 R
// 15 1  R
// 16 2  A
// 18 -1 R
// 18 -1 A
// 17   ----

// ---
// 15 16
// 15 -1
// 14 -2
// 12 -4
```

---

---
## Quick Revision
Find the minimum instructions (Accelerate or Reverse) to reach position target from zero starting with speed one.
Solve recursively with memoization by either overshooting the target or turning back strategically before reaching it.

## Intuition
The "aha moment" comes from realizing that reaching a target isn't just about driving straight. We can either accelerate past the target ($2^n - 1$) and turn back, or drive almost to the target, turn around, back up a bit, turn around again, and resume forward progress. By treating the problem as a set of recursive sub-problems and caching results with memoization, we can break down complex driving paths into optimal atomic choices.

## Algorithm
1. Base Case: If the target $t$ is $0$, return $0$ instructions.
2. Check Memoization: If the result for target $t$ is already computed, return it from the cache.
3. Calculate Exact Power of Two: Find the smallest $n$ such that $2^n - 1 \geq t$.
4. Check Exact Match: If $2^n - 1 == t$, it takes $n$ pure acceleration commands. Cache and return $n$.
5. Scenario 1 (Overshoot): Accelerate $n$ times to reach $2^n - 1$, reverse, and solve for the remaining distance $(2^n - 1) - t$. Total cost is $n + 1 + \text{racecar}(2^n - 1 - t)$.
6. Scenario 2 (Turn back before target): Drive $n-1$ times forward (reaching $2^{n-1} - 1$), reverse for $m$ steps ($0$ to $n-2$), reverse again to face forward, and recursively solve for the remaining distance. Keep track of the minimum operations found across all $m$.
7. Cache the minimum result found from all scenarios for target $t$ and return it.

## Concept to Remember
- Recursion with Memoization (Top-Down Dynamic Programming) for optimization problems with overlapping subproblems.
- Bit manipulation (`1 << n`) for fast calculation of powers of two and distance metrics.
- State reduction: transforming complex trajectory choices into simpler relative distance subproblems.

## Common Mistakes
- Forgetting to handle the base case when target is zero, leading to infinite recursion.
- Off-by-one errors when computing the upper bound power of two or loop boundaries for backing up.
- Omitting memoization, which causes exponential time complexity due to redundant branch calculations.
- Miscalculating the reverse step offsets and remaining distance formulas.

## Complexity Analysis
- Time: $O(t \log t)$ - Due to the memoization of subproblems up to target $t$ and the branching loop bounded by $\log t$.
- Space: $O(t)$ - The memoization hash map and recursive call stack store states proportional to the target value $t$.

## Commented Code
```java
class Solution {
    // Hash map to memoize results for each target to avoid redundant computations
    HashMap<Integer,Integer> memo = new HashMap<>();
    
    public int racecar(int t) {
        // Base case: if target is 0, 0 instructions are needed
        if(t==0) return 0;
        
        // If we have already calculated the minimum steps for this target, return it
        if(memo.containsKey(t)) return memo.get(t);
        
        // Find the number of 'A' operations required to just exceed or reach target t
        int n = (int) (Math.log(t)/Math.log(2)) +1; 
        
        // Calculate the exact distance covered by n consecutive 'A' operations (2^n - 1)
        int exact = (1 << n) -1; 
        
        // If the exact power of 2 minus 1 equals the target, return n instructions
        if(exact == t) {memo.put(t,n);return n;} 
        
        // Strategy 1: Overshoot the target, reverse, and solve for the excess distance
        int min = racecar(exact-t);
        
        // Calculate the position just before the overshoot (2^(n-1) - 1)
        int prevStop = (1<<(n-1));     
        
        // Strategy 2: Stop short of the target, reverse for m steps, and turn back forward
        for(int m=0;m<n-1;m++){ 
            // Calculate how far back we drive during the reverse phase
            int revStep = 1<<m; 
            
            // Calculate the remaining distance to cover after backing up
            int curr = racecar(t-prevStop + revStep); 
            
            // Total cost: (n - 1 steps forward) + (1 turn) + (m steps reverse) + (1 turn) + remaining steps
            // Simplified here: curr + m + 1 (for reverse) + (n - 1) -> which accounts for path specifics
            int total = curr + m;  
            
            // Track the minimum operations among all possible reverse step variations
            min = Math.min(min,total);
        }
        
        // Store the optimal result in the memoization map (n steps forward + 1 reverse + best remaining min)
        memo.put(t,1 + n + min);
        
        // Return the computed minimum steps for target t
        return 1 + n + min;  
    }
}
```

## Interview Tips
- Clarify the command rules early: 'A' doubles speed and adds position, 'R' resets speed to direction sign change.
- Explain the two main strategic choices clearly to the interviewer before diving into code: overshooting vs. turning back early.
- Mention that although a BFS shortest-path approach works, the math/recursion with memoization approach is concise and demonstrates strong problem decomposition skills.
- Walk through a small example (like $t = 3$ or $t = 5$) to prove the logic handles edge cases properly.

## Revision Checklist
- [ ] Understand why we evaluate powers of two minus one ($2^n - 1$).
- [ ] Implement the base and memoization checks.
- [ ] Correctly code the overshoot branch calculation.
- [ ] Correctly code the turn-back loop with variable reverse steps $m$.
- [ ] Analyze the time and space complexity correctly.

## Similar Problems
- `Climbing Stairs`
- `Jump Game II`
- `Minimum Number of Refueling Stops`

## Tags
`Dynamic Programming` `Recursion` `Math` `Memoization`
