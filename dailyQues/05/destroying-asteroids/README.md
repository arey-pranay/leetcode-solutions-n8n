# Destroying Asteroids

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(N log N)  
**Space:** O(log N)

---

## Solution (java)

```java
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long total = mass;
        for(int m : asteroids) if(m > total) return false; else total+=m;
        return true;
    }
}
```

---

---
## Quick Revision
You are given a spaceship's initial mass and an array of asteroid masses.
Sort asteroids and iteratively destroy them if your mass is greater, accumulating mass.

## Intuition
The core idea is that to destroy an asteroid, your current mass must be strictly greater than the asteroid's mass. If you can destroy an asteroid, your mass increases, potentially allowing you to destroy larger asteroids later. This suggests a greedy approach. To maximize our chances of destroying as many asteroids as possible, we should always try to destroy the smallest asteroids first. This is because destroying smaller asteroids increases our mass with minimal risk, making us stronger for subsequent, larger asteroids. If at any point our mass is not strictly greater than the next smallest asteroid, we cannot proceed further and fail.

## Algorithm
1. Sort the `asteroids` array in ascending order. This ensures we consider asteroids from smallest to largest.
2. Initialize a variable `totalMass` (or `currentMass`) with the spaceship's initial `mass`. Use a `long` to prevent potential integer overflow as the mass can grow significantly.
3. Iterate through the sorted `asteroids` array.
4. For each `asteroidMass` in the array:
    a. Check if `totalMass` is strictly greater than `asteroidMass`.
    b. If `totalMass <= asteroidMass`, the spaceship cannot destroy this asteroid. Return `false`.
    c. If `totalMass > asteroidMass`, the spaceship destroys the asteroid. Add `asteroidMass` to `totalMass`.
5. If the loop completes without returning `false`, it means the spaceship successfully destroyed all asteroids. Return `true`.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a globally optimal solution.
*   **Sorting:** Essential for processing elements in a specific order (smallest to largest in this case) to enable the greedy strategy.
*   **Integer Overflow:** Be mindful of potential data type limitations when accumulating large sums.

## Common Mistakes
*   **Not Sorting:** Failing to sort the asteroids means the greedy strategy won't work, as you might encounter a large asteroid early that you could have destroyed after destroying smaller ones.
*   **Using `int` for Mass Accumulation:** The total mass can exceed the maximum value of an `int`, leading to incorrect results due to overflow.
*   **Incorrect Comparison:** Using `>=` instead of `>` for checking if the spaceship can destroy an asteroid. The problem states "strictly greater".
*   **Not Handling Edge Cases:** Forgetting to consider an empty `asteroids` array (though the constraints might prevent this, it's good practice).

## Complexity Analysis
- Time: O(N log N) - reason: Dominated by the sorting of the `asteroids` array, where N is the number of asteroids. The subsequent iteration is O(N).
- Space: O(log N) or O(N) - reason: Depends on the sorting algorithm used by `Arrays.sort()`. In Java, it's typically O(log N) for primitive types due to quicksort/mergesort variations, or O(N) if a copy is made for certain sorting implementations.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Sort the asteroids array in ascending order.
        // This is crucial for the greedy approach: destroy smaller asteroids first to increase mass.
        Arrays.sort(asteroids);

        // Initialize totalMass with the spaceship's initial mass.
        // Use 'long' to prevent potential integer overflow as mass can accumulate.
        long totalMass = mass;

        // Iterate through each asteroid in the sorted array.
        for (int asteroidMass : asteroids) {
            // Check if the spaceship's current total mass is strictly greater than the asteroid's mass.
            if (totalMass > asteroidMass) {
                // If the spaceship can destroy the asteroid, add its mass to the total mass.
                totalMass += asteroidMass;
            } else {
                // If the spaceship's mass is not strictly greater, it cannot destroy this asteroid.
                // Therefore, it cannot destroy all subsequent (larger) asteroids either.
                return false; // Return false immediately.
            }
        }

        // If the loop completes, it means the spaceship successfully destroyed all asteroids.
        return true; // Return true.
    }
}
```

## Interview Tips
1.  **Explain the Greedy Choice:** Clearly articulate *why* sorting and destroying the smallest asteroids first is the optimal strategy. Emphasize that increasing mass early makes subsequent, larger asteroids more manageable.
2.  **Address Overflow:** Proactively mention the potential for integer overflow and explain why using `long` for `totalMass` is necessary. This shows attention to detail and robustness.
3.  **Walk Through an Example:** Use a small example (e.g., `mass = 10`, `asteroids = [5, 2, 19]`) to demonstrate the algorithm step-by-step, showing how sorting and accumulation work.
4.  **Clarify "Strictly Greater":** Ensure you understand and correctly implement the condition `totalMass > asteroidMass`. Misinterpreting this is a common pitfall.

## Revision Checklist
- [ ] Understand the problem statement: spaceship mass vs. asteroid masses.
- [ ] Recognize the greedy nature: destroy smallest first.
- [ ] Implement sorting for asteroids.
- [ ] Use `long` for accumulating mass to avoid overflow.
- [ ] Correctly implement the `totalMass > asteroidMass` condition.
- [ ] Handle the case where destruction is impossible.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [Gas Station](https://leetcode.com/problems/gas-station/) (Greedy, Array)
*   [Merge Intervals](https://leetcode.com/problems/merge-intervals/) (Sorting, Greedy)
*   [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) (Sorting, Greedy)

## Tags
`Array` `Sorting` `Greedy` `Math`
