# Candy

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Greedy`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies,1);
        for(int i=1;i<n;i++) if(ratings[i]>ratings[i-1]) candies[i] = candies[i-1] + 1;
        int ans = candies[n-1];
        for(int i=n-2;i>=0;i--){
            if(ratings[i] > ratings[i+1]) candies[i] = Math.max(candies[i], candies[i+1]+1);
            ans += candies[i];
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Distribute candies to children based on ratings, ensuring adjacent children with higher ratings get more candies.
Solve by performing two passes: one from left to right and another from right to left, combining the results.

## Intuition
The core constraint is that a child with a higher rating must receive more candies than their immediate neighbors. This suggests a dependency on adjacent elements. A single pass from left to right can satisfy the condition for children compared to their left neighbor. However, this doesn't account for the right neighbor. Similarly, a pass from right to left handles the right neighbor but not the left. The "aha moment" is realizing that we can combine the results of these two independent passes. For each child, their candy count must satisfy *both* conditions (higher than left, higher than right). Therefore, we take the maximum of the candies assigned in each pass. The total sum of candies is the final answer.

## Algorithm
1. Initialize an array `candies` of the same size as `ratings`, and fill it with 1s. Each child initially gets at least one candy.
2. **First Pass (Left to Right):** Iterate through the `ratings` array from the second element (index 1) to the end.
   - If the current child's rating (`ratings[i]`) is greater than their left neighbor's rating (`ratings[i-1]`), then the current child must receive one more candy than their left neighbor. So, set `candies[i] = candies[i-1] + 1`.
3. **Second Pass (Right to Left):** Initialize a variable `totalCandies` with the candy count of the last child (`candies[n-1]`). Iterate through the `ratings` array from the second-to-last element (index `n-2`) down to the beginning (index 0).
   - If the current child's rating (`ratings[i]`) is greater than their right neighbor's rating (`ratings[i+1]`), then the current child must receive at least one more candy than their right neighbor. We need to ensure this condition is met *without violating* the condition from the left-to-right pass. So, update `candies[i]` to be the maximum of its current value (from the left-to-right pass) and `candies[i+1] + 1`.
   - Add the updated `candies[i]` to `totalCandies`.
4. Return `totalCandies`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Two-Pointer/Two-Pass Technique:** Solving a problem by iterating through the data structure multiple times, often in different directions, to gather information or satisfy constraints.
*   **Array Manipulation:** Efficiently updating and using array elements to store intermediate results.

## Common Mistakes
*   **Single Pass Only:** Attempting to solve the problem with just one pass (either left-to-right or right-to-left) and forgetting to consider the other neighbor.
*   **Incorrectly Combining Results:** Not taking the `Math.max` when combining results from the two passes, leading to violations of one of the conditions.
*   **Off-by-One Errors:** Incorrectly handling array indices, especially in the loops or when accessing neighbors.
*   **Not Initializing with 1:** Forgetting to give each child at least one candy initially.

## Complexity Analysis
- Time: O(n) - The algorithm involves two separate passes through the `ratings` array, each taking linear time.
- Space: O(n) - An auxiliary array `candies` of size `n` is used to store the candy distribution.

## Commented Code
```java
class Solution {
    public int candy(int[] ratings) {
        // Get the number of children.
        int n = ratings.length;
        // Initialize an array to store the number of candies for each child.
        // Each child starts with at least 1 candy.
        int[] candies = new int[n];
        // Fill the candies array with 1s.
        Arrays.fill(candies, 1);

        // First pass: Iterate from left to right to satisfy the condition for the left neighbor.
        // Start from the second child (index 1).
        for (int i = 1; i < n; i++) {
            // If the current child has a higher rating than the child to their left...
            if (ratings[i] > ratings[i - 1]) {
                // ...then the current child must have one more candy than their left neighbor.
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Initialize the total number of candies with the candies of the last child.
        // We will add candies from right to left in the next loop.
        int totalCandies = candies[n - 1];

        // Second pass: Iterate from right to left to satisfy the condition for the right neighbor.
        // Start from the second-to-last child (index n-2).
        for (int i = n - 2; i >= 0; i--) {
            // If the current child has a higher rating than the child to their right...
            if (ratings[i] > ratings[i + 1]) {
                // ...then the current child must have at least one more candy than their right neighbor.
                // We take the maximum of the current candy count (from the left-to-right pass)
                // and the required count based on the right neighbor. This ensures both conditions are met.
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            // Add the final candy count for the current child to the total.
            totalCandies += candies[i];
        }

        // Return the total number of candies distributed.
        return totalCandies;
    }
}
```

## Interview Tips
*   **Explain the Two-Pass Logic:** Clearly articulate why a single pass is insufficient and how the two passes address the problem's constraints from both sides.
*   **Walk Through an Example:** Use a small example array (e.g., `[1, 0, 2]`) to demonstrate how the `candies` array is updated in each pass and how the final sum is calculated.
*   **Discuss Edge Cases:** Mention what happens with an empty array (though constraints usually prevent this) or an array with one element.
*   **Clarify the `Math.max`:** Emphasize that `Math.max` is crucial for ensuring that a child receives enough candies to satisfy *both* their left and right neighbors' rating comparisons.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the need for considering both left and right neighbors.
- [ ] Implement the left-to-right pass correctly.
- [ ] Implement the right-to-left pass correctly, using `Math.max`.
- [ ] Sum up the candies correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Trapping Rain Water
*   Gas Station
*   Product of Array Except Self

## Tags
`Array` `Dynamic Programming` `Greedy`
