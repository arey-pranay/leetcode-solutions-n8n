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
Given an array of students' ratings, distribute candies to make sure each student receives more candies than their adjacent peers if they have a higher rating. The goal is to find the minimum number of candies needed.

## Intuition
The problem can be solved by iterating through the ratings from left to right and then from right to left. When we encounter a student with a higher rating, we give them at least one more candy than their neighbor. We keep track of the maximum number of candies given to any student so far on the right side.

## Algorithm
1. Initialize an array `candies` of length `n` with all values as 1.
2. Iterate through the ratings from left to right, and for each rating at index `i`, if it is higher than the previous one (`ratings[i-1] < ratings[i]`), update `candies[i]` to be one more than `candies[i-1]`.
3. Iterate through the ratings from right to left, and for each rating at index `i`, if it is higher than the next one (`ratings[i+1] < ratings[i]`), update `candies[i]` to be the maximum of its current value and `candies[i+1] + 1`.
4. Initialize a variable `ans` to store the total number of candies needed.
5. Iterate through the `candies` array and add each value to `ans`.

## Concept to Remember
• **Dynamic Programming**: breaking down a problem into smaller subproblems, solving each one only once, and storing the results in an array or table for future reference.
• **Greedy Algorithm**: making locally optimal choices at each step with the hope that these local choices will lead to a global optimum solution.

## Common Mistakes
• Failing to initialize the `candies` array properly before iterating through it.
• Not accounting for cases where two students have equal ratings but are next to each other.
• Overcomplicating the algorithm by trying to use too many variables or complex data structures.

## Complexity Analysis
- Time: O(n) - iterating through the ratings twice from left to right and right to left.
- Space: O(n) - storing the `candies` array of length `n`.

## Commented Code

```java
class Solution {
    public int candy(int[] ratings) {
        // Initialize an array with all values as 1
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1); // Fill the array with ones.

        for (int i = 1; i < n; i++) { // Iterate through the ratings from left to right.
            if (ratings[i-1] < ratings[i]) {
                // Update candies[i] to be one more than candies[i-1].
                candies[i] = candies[i-1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) { // Iterate through the ratings from right to left.
            if (ratings[i+1] < ratings[i]) {
                // Update candies[i] to be the maximum of its current value and candies[i+1] + 1.
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
            }
        }

        int ans = 0; // Initialize a variable to store the total number of candies needed.
        for (int val : candies) { // Iterate through the candies array and add each value to ans.
            ans += val;
        }
        return ans;
    }
}
```

## Interview Tips
• Focus on breaking down complex problems into smaller subproblems that can be solved more easily.
• Practice using a greedy algorithm approach to solve problems where you have to make locally optimal choices.
• Be prepared to discuss the trade-offs between time and space complexity in your solution.

## Revision Checklist
- [ ] Double-check initialization of the `candies` array.
- [ ] Review logic for updating `candies[i]` when ratings increase from left to right.
- [ ] Verify that the greedy algorithm approach is indeed correct for this problem.

## Similar Problems
• `Kids With the Greatest Number of Candies`
• `Least Interval`

## Tags
`Array`, `Hash Map`, `Greedy Algorithm`, `Dynamic Programming`
