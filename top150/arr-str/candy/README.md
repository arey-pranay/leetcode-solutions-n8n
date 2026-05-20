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
        for(int i=1;i<n;i++) if(ratings[i-1] < ratings[i]) candies[i] = candies[i-1] + 1;
        for(int i=n-2;i>=0;i--) if(ratings[i+1] < ratings[i]) candies[i] = Math.max(candies[i],candies[i+1] + 1);
        int ans=0;
        for(int val : candies) ans += val;
        return ans;
    }
}

// 1 2 4 0 9 1 2 10
// - 1 2 1 2 - - -1

// 1 0 2
// 2 1 2
```

---

---
## Quick Revision
Distribute candies to children based on ratings, ensuring adjacent children with higher ratings get more candies.
Solve by two passes: one from left to right, then another from right to left, taking the maximum.

## Intuition
The core constraint is that a child with a higher rating must receive more candies than their immediate neighbors. This suggests a dependency on adjacent elements. A single pass from left to right can satisfy the condition for children whose rating is higher than their left neighbor. However, it might violate the condition for children whose rating is higher than their right neighbor. To address this, a second pass from right to left is needed to ensure the condition is met for all neighbors. By taking the maximum of the candies assigned in both passes for each child, we guarantee that both conditions (higher than left, higher than right) are satisfied simultaneously.

## Algorithm
1. Initialize an array `candies` of the same size as `ratings`, and fill it with `1` for each child. This ensures every child gets at least one candy.
2. Perform a **left-to-right pass**: Iterate from the second child (`i = 1`) to the end of the `ratings` array. If the current child's rating (`ratings[i]`) is greater than the previous child's rating (`ratings[i-1]`), then the current child must receive one more candy than the previous child. So, update `candies[i] = candies[i-1] + 1`.
3. Perform a **right-to-left pass**: Iterate from the second-to-last child (`i = n-2`) down to the first child (`i = 0`). If the current child's rating (`ratings[i]`) is greater than the next child's rating (`ratings[i+1]`), then the current child must receive one more candy than the next child. However, we must also respect the candy count determined in the left-to-right pass. Therefore, update `candies[i] = Math.max(candies[i], candies[i+1] + 1)`.
4. Sum up all the values in the `candies` array to get the total minimum number of candies required.
5. Return the total sum.

## Concept to Remember
*   **Greedy Approach**: Making locally optimal choices at each step to achieve a global optimum.
*   **Two-Pointer/Two-Pass Iteration**: Using multiple passes or pointers to satisfy conditions that depend on both preceding and succeeding elements.
*   **Array Manipulation**: Efficiently updating and storing intermediate results in an auxiliary array.

## Common Mistakes
*   **Single Pass Only**: Attempting to solve the problem with just one pass (either left-to-right or right-to-left) will fail to satisfy all conditions.
*   **Incorrectly Applying `Math.max`**: Forgetting to use `Math.max` in the second pass can lead to assigning fewer candies than required if the left-to-right pass had already assigned a higher value.
*   **Off-by-One Errors**: Incorrect loop bounds or index access in either pass can lead to incorrect candy assignments.
*   **Not Initializing with 1**: Forgetting to give each child at least one candy initially will violate the problem's base condition.

## Complexity Analysis
- Time: O(n) - The algorithm involves two passes through the `ratings` array, each taking O(n) time. The final summation also takes O(n) time. Thus, the total time complexity is O(n) + O(n) + O(n) = O(n).
- Space: O(n) - An auxiliary array `candies` of size `n` is used to store the number of candies for each child.

## Commented Code
```java
class Solution {
    public int candy(int[] ratings) {
        // Get the number of children.
        int n = ratings.length;
        // Initialize an array to store the number of candies for each child.
        int[] candies = new int[n];
        // Fill the candies array with 1, ensuring each child gets at least one candy.
        Arrays.fill(candies,1);

        // First pass: Left to Right.
        // Iterate from the second child to the last child.
        for(int i = 1; i < n; i++) {
            // If the current child has a higher rating than the previous child...
            if(ratings[i-1] < ratings[i]) {
                // ...give the current child one more candy than the previous child.
                candies[i] = candies[i-1] + 1;
            }
        }

        // Second pass: Right to Left.
        // Iterate from the second-to-last child down to the first child.
        for(int i = n - 2; i >= 0; i--) {
            // If the current child has a higher rating than the next child...
            if(ratings[i+1] < ratings[i]) {
                // ...update the current child's candies to be the maximum of its current value
                // and one more candy than the next child. This ensures both left and right conditions are met.
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
            }
        }

        // Calculate the total number of candies.
        int ans = 0;
        // Iterate through the candies array.
        for(int val : candies) {
            // Add each child's candy count to the total.
            ans += val;
        }
        // Return the total minimum number of candies.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Two-Pass Logic**: Clearly articulate why a single pass is insufficient and how the second pass corrects for the right-neighbor condition.
*   **Edge Cases**: Discuss what happens with an empty input array (though constraints usually prevent this) or an array with only one element.
*   **Clarify Constraints**: Ask about the range of ratings and the number of children to understand potential overflow issues or performance considerations.
*   **Walk Through Examples**: Use the provided examples or create your own to demonstrate how your algorithm works step-by-step.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core condition: higher rating means more candy.
- [ ] Realize the need for considering both left and right neighbors.
- [ ] Develop the two-pass greedy strategy.
- [ ] Implement the left-to-right pass correctly.
- [ ] Implement the right-to-left pass correctly, using `Math.max`.
- [ ] Calculate the final sum.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Trapping Rain Water
*   Gas Station
*   Jump Game II

## Tags
`Array` `Dynamic Programming` `Greedy`
