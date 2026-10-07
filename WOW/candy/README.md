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
Solve by performing two passes: one from left to right and another from right to left, updating candy counts based on relative ratings.

## Intuition
The core constraint is that a child with a higher rating must receive more candies than their immediate neighbors. This suggests a local dependency. If we only consider the left neighbor, we can satisfy one part of the condition. However, a child might have a higher rating than both their left and right neighbors. To satisfy both conditions simultaneously, we need to consider both directions. The "aha moment" is realizing that we can satisfy the left-to-right condition in one pass and the right-to-left condition in a second pass, and then take the maximum of the two requirements for each child.

## Algorithm
1. Initialize an array `candies` of the same size as `ratings`, and fill it with 1s. Each child initially gets at least one candy.
2. Perform a left-to-right pass:
    - Iterate from the second child (index 1) to the end of the `ratings` array.
    - If the current child's rating (`ratings[i]`) is greater than their left neighbor's rating (`ratings[i-1]`), then the current child must receive one more candy than their left neighbor. Update `candies[i]` to `candies[i-1] + 1`.
3. Perform a right-to-left pass:
    - Initialize a variable `totalCandies` to 0.
    - Iterate from the second-to-last child (index `n-2`) down to the first child (index 0).
    - If the current child's rating (`ratings[i]`) is greater than their right neighbor's rating (`ratings[i+1]`), then the current child must receive one more candy than their right neighbor. However, they might have already received more candies from the left-to-right pass. So, update `candies[i]` to be the maximum of its current value and `candies[i+1] + 1`.
    - Add the `candies[i]` to `totalCandies`.
4. After the right-to-left pass, the last child's candy count (`candies[n-1]`) hasn't been added to `totalCandies` yet. Add `candies[n-1]` to `totalCandies`.
5. Return `totalCandies`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices at each step to achieve a global optimum.
*   **Two-Pass Algorithm:** Solving a problem by iterating through the data structure multiple times, often in different directions, to satisfy all constraints.
*   **Dynamic Programming (Implicit):** The solution builds upon previously computed values (candy counts of neighbors), though it's not a typical DP table approach.

## Common Mistakes
*   **Single Pass Only:** Attempting to solve the problem with just one pass (either left-to-right or right-to-left) will fail to satisfy all conditions.
*   **Incorrectly Updating Candies:** Not using `Math.max` in the second pass can lead to assigning fewer candies than required when a child needs more based on the right neighbor.
*   **Forgetting the Last Element:** In the provided solution, the sum is accumulated during the second pass, but the last element's candy count needs to be added separately.
*   **Off-by-One Errors:** Incorrect loop bounds or index access when comparing neighbors.

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
        // Each child initially receives at least one candy.
        int[] candies = new int[n];
        // Fill the candies array with 1s.
        Arrays.fill(candies,1);

        // First pass: Left to Right.
        // Iterate from the second child to the last child.
        for(int i=1;i<n;i++) {
            // If the current child has a higher rating than the previous child...
            if(ratings[i]>ratings[i-1]) {
                // ...they must receive one more candy than the previous child.
                candies[i] = candies[i-1] + 1;
            }
        }

        // Initialize the total number of candies. We start by adding the candies for the last child.
        // The last child's candy count is finalized after the first pass and will be added in the second pass's summation logic.
        // The provided solution sums up during the second pass, so we initialize ans with the last child's candies.
        int ans = candies[n-1];

        // Second pass: Right to Left.
        // Iterate from the second-to-last child down to the first child.
        for(int i=n-2;i>=0;i--){
            // If the current child has a higher rating than the next child...
            if(ratings[i] > ratings[i+1]) {
                // ...they must receive one more candy than the next child.
                // We take the maximum of the current candy count (from the left-to-right pass)
                // and the requirement from the right neighbor to ensure both conditions are met.
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
            // Add the current child's finalized candy count to the total.
            ans += candies[i];
        }
        // Return the total number of candies distributed.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Two-Pass Strategy:** Clearly articulate why a single pass is insufficient and why two passes (left-to-right and right-to-left) are necessary to satisfy both neighbor conditions.
*   **Walk Through an Example:** Use a small example array (e.g., `[1, 0, 2]`) to demonstrate how the `candies` array is updated in each pass.
*   **Discuss Edge Cases:** Consider cases like an empty `ratings` array, an array with one element, or an array where all ratings are the same or strictly increasing/decreasing.
*   **Clarify Space Optimization (if asked):** While the O(n) space solution is standard, be prepared to discuss if and how space could be optimized (though it's generally not straightforward for this problem without losing clarity).

## Revision Checklist
- [ ] Understand the problem constraints: higher rating means more candy than immediate neighbors.
- [ ] Recognize the need for a greedy approach.
- [ ] Implement the left-to-right pass correctly.
- [ ] Implement the right-to-left pass correctly, using `Math.max`.
- [ ] Ensure the total sum is calculated accurately.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Trapping Rain Water
*   Gas Station
*   Jump Game

## Tags
`Array` `Dynamic Programming` `Greedy`
